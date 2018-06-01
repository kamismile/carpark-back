package ru.neoflex.microservices.carpark.report.resource;

import javassist.bytecode.ByteArray;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.reciver.CarsMessageReceiver;
import ru.neoflex.microservices.carpark.report.reciver.Sender;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * @author rmorenko
 */
@RestController
@AllArgsConstructor
@Slf4j
public class ReportRexource {

        public static final String RENTAL_MANAGER = "rental_manager";
        public static final String DEFAULT_REPORT = "/car.jrxml";
        public static final String RENTAL_REPORT = "/car_rental.jrxml";
        @Autowired
        private Sender sender;

        @Autowired
        private DataSource dataSource;

        @Autowired
        private CarsMessageReceiver receiver;

        @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
        public void test() {
                Car car = new Car();
                car.setId(1L);
                car.setMark("Рыдван");
                car.setCurrentStatusDate(new Date());
                car.setState(States.READY);
                CarCommand command = new CarCommand();
                command.setCommand(Command.ADD);
                command.setEntity(car);
                UserInfo info = new UserInfo();
                info.setName("test");
                info.setLocationId(1L);
                command.setUserInfo(info);
                command.setMessageDate(new Date());
                car.setMark("reno");
                car.setMileage(100.23);
                car.setCurrentStatusDate(new Date());
                car.setLocationId(1L);
                car.setNextMaintenanceDate(new Date());
                car.setYear(1983);
                car.setNextStatus(States.IN_SERVICE.toString());
                car.setNumber("123AO6");
                car.setCurrentStatus(States.IN_USE.toString());
                car.setPrevMaintenanceDate(new Date());
                sender.send(command);

        }

        @GetMapping(value = "/report", produces = "application/xlsx")
        public byte[] report(UserInfo userInfo, @RequestParam(value = "date", required = false) Long date) {

                Date reportDate = new Date();
                if (date != null && date > 0){
                   reportDate = new Date();

                }
                boolean isRentalRole = RENTAL_MANAGER.equals(userInfo.getRole())?true:false;
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(reportDate);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                Map<String, Object> parameters = fillParameters(userInfo, isRentalRole, calendar);
                try {
                InputStream employeeReportStream
                        = getClass().getResourceAsStream(!isRentalRole? DEFAULT_REPORT : RENTAL_REPORT);
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

        private byte[] getReportBytes(JasperPrint jasperPrint) throws JRException {
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                exporter.setExporterOutput(
                        new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                SimpleXlsxReportConfiguration xlsReportConfiguration
                        = new SimpleXlsxReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(false);
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
                xlsReportConfiguration.setDetectCellType(true);
                xlsReportConfiguration.setWhitePageBackground(false);
                exporter.exportReport();
                return byteArrayOutputStream.toByteArray();
        }

        private Map<String, Object> fillParameters(UserInfo userInfo, boolean isRentalRole, Calendar calendar) {
                Map<String,Object> parameters = new HashMap<>();
                parameters.put("report_date", calendar.getTime());
                if (isRentalRole){
                  parameters.put("locationId", userInfo.getLocationId());
                }
                return parameters;
        }

}
