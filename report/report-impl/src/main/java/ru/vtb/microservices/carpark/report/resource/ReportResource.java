/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.resource;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.report.repository.CarEventSpecisications.carEventFrom;
import static ru.vtb.microservices.carpark.report.repository.CarEventSpecisications.carEventTo;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.report.model.CarCommand;
import ru.vtb.microservices.carpark.report.model.CarEvent;
import ru.vtb.microservices.carpark.report.reciver.Sender;
import ru.vtb.microservices.carpark.report.repository.CarEventRepository;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Report controoller.
 *
 * @author Roman_Morenko
 */
@RestController
@Slf4j
public class ReportResource {

    private static final String RENTAL_MANAGER = "rental_manager";
    private static final String DEFAULT_REPORT = "/car.jrxml";
    private static final String RENTAL_REPORT = "/car_rental.jrxml";

    private Sender sender;

    private CarEventRepository carEventRepository;

    private DataSource dataSource;

    @Value("${kafka.topic.car}")
    String carTopic;

    @Autowired
     public ReportResource(Sender sender, CarEventRepository carEventRepository,
                               DataSource dataSource ) {
        this.carEventRepository = carEventRepository;
        this.sender = sender;
        this.dataSource = dataSource;
    }

        /**
         * use only for testing.
         */
    @GetMapping(value = "/sendCar", produces = MediaType.APPLICATION_JSON_VALUE)
    public void senCar() {
        Car car = new Car();
        car.setId(1L);
        car.setCurrentStatusDate(new Date());
        car.setState(States.READY);
        car.setMark("reno");
        car.setMileage(100.23);
        car.setLocationId(1L);
        car.setNextMaintenanceDate(new Date());
        car.setYear(1983);
        car.setNextStatus(States.IN_SERVICE.toString());
        car.setNumber("123AO6");
        car.setCurrentStatus(States.IN_USE.toString());
        car.setPrevMaintenanceDate(new Date());
        CarCommand command = new CarCommand();
        command.setCommand(Command.ADD);
        command.setEntity(car);
        UserInfo info = new UserInfo();
        info.setName("test");
        info.setLocationId(1L);
        command.setUserInfo(info);
        sender.send(carTopic,command);
    }

    @GetMapping (value = "/history", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CarEvent> getHistory(UserInfo userInfo, @RequestParam (value = "date", required = false) Long date) {
        Date reportDate = getDateReport(date);
        Calendar calendar = trimDate(reportDate);
        Date dateTo = calendar.getTime();
        Date dateFrom = shiftDay(calendar);

        if (isRentalRole(userInfo)) {
            return  carEventRepository.findByMessageDateAndLocationId(reportDate, userInfo.getLocationId());
        }
        return carEventRepository.findAll(where(carEventFrom(dateFrom)).and(carEventTo(dateTo)));
    }

    private Date getDateReport(@RequestParam(value = "date", required = false) Long date) {
        Date reportDate = new Date();
        if (date != null && date > 0) {
            reportDate = new Date(date);
        }
        return reportDate;
    }

        /**
         * generation report.
         * @param userInfo info about user
         * @param date date of report
         * @return report
         */
    @GetMapping(value = "/report", produces = "application/xlsx")
     public byte[] report(UserInfo userInfo, @RequestParam(value = "date", required = false) Long date) {

        Date reportDate = getDateReport(date);

        Calendar calendar = trimDate(reportDate);
        Date dateTo = calendar.getTime();
        Date dateFrom = shiftDay(calendar);
        Map<String, Object> parameters = fillParameters(userInfo, dateFrom, dateTo);
        try {
            InputStream employeeReportStream
                        = getClass().getResourceAsStream(getResourceAsStream(userInfo));
            JasperReport jasperReport
                                = JasperCompileManager.compileReport(employeeReportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                                jasperReport, parameters, dataSource.getConnection());
            return getReportBytes(jasperPrint);
        } catch (JRException e) {
            log.error(e.getMessage());
            log.trace("Jasper error", e);
            return e.getMessage().getBytes();
        } catch (SQLException e) {
            log.trace("SQL error", e);
            log.error(e.getMessage());
            return e.getMessage().getBytes();
        }
    }

    private Date shiftDay(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        return calendar.getTime();
    }

    private Calendar trimDate(Date reportDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(reportDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    private String getResourceAsStream( UserInfo userInfo ) {
        boolean isRentalRole = isRentalRole(userInfo);
        return !isRentalRole ? DEFAULT_REPORT : RENTAL_REPORT;
    }

    private boolean isRentalRole(UserInfo userInfo) {
        return RENTAL_MANAGER.equals(userInfo.getRole());
    }

    private byte[] getReportBytes(JasperPrint jasperPrint) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exporter.setExporterOutput(
                     new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        SimpleXlsxReportConfiguration xlsReportConfiguration
                        = new SimpleXlsxReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
        xlsReportConfiguration.setDetectCellType(Boolean.FALSE);
        xlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);
        exporter.exportReport();
        return byteArrayOutputStream.toByteArray();
    }

    private Map<String, Object> fillParameters(UserInfo userInfo, Date dateFrom, Date dateTo) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("dateFrom", dateFrom );
        parameters.put("dateTo", dateTo );
        if (isRentalRole(userInfo)) {
            parameters.put("locationId", userInfo.getLocationId());
        }
        return parameters;
    }

}
