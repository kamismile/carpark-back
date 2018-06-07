package ru.vtb.microservices.carpark.employees.model;

import lombok.Data;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Employee entity
 * @author mirzoevnik
 */
@Data
@Entity
@Table
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    @Column(name = "PASSPORT_SERIES")
    private String passportSeries;

    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    private Date birthday;

    private String position;

    @Column(name = "APPOINTMENT_DATE")
    private Date appointmentDate;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToOne
    @JoinColumn (name = "USER_ID")
    private UserInfo user;

    private boolean active;
}
