package ru.neoflex.microservices.carpark.employees.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
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

    @Column(name = "USER_ID")
    private String userId;
}
