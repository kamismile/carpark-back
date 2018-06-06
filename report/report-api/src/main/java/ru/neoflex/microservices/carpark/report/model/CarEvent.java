package ru.neoflex.microservices.carpark.report.model;

import lombok.Data;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.Location;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author rmorenko
 */
@Data
@Entity
@Table(name = "car_event")
public class CarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;

    private Integer year;

    private Double mileage;

    @Column(name = "PREV_MAINTENANCE_DATE")
    private Date prevMaintenanceDate;

    @Column(name = "NEXT_MAINTENANCE_DATE")
    private Date nextMaintenanceDate;

    @Column(name = "CURRENT_STATUS")
    private String currentStatus;

    @Column(name = "CURRENT_STATUS_DATE")
    private Date currentStatusDate;

    @Column(name = "NEXT_STATUS")
    private String nextStatus;

    @Column(name = "NEXT_STATUS_DATE")
    private Date nextStatusDate;

    private String state;

    @Column (name = "LOCATION_ID")
    private Long locationId;

    @Column (name = "CURRENT_LOCATION_ID")
    private Long currentLocationId;

    @Column (name = "REG_NUMBER")
    private String number;

    @Column(name="MESSAGE_DATE")
    private Date messageDate;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="CAR_ID")
    private Long carId;

    @Column(name="MESSAGE_TYPE")
    private String messageType;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @Transient
    private List<Events> availableEvents;

    public States getState() {
        return States.valueOf(state);
    }

    public void setState(States stateEnumVal) {
        state = stateEnumVal.name();
    }

}
