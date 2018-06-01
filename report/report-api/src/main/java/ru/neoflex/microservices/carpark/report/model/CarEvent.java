package ru.neoflex.microservices.carpark.report.model;

import lombok.Data;
import ru.neoflex.microservices.carpark.cars.model.Car;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rmorenko
 */
@Data
@Entity
@Table(name = "car_event")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CarEvent extends Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MARK_DESC")
    private String markDesc;

    @Column(name = "CURENT_STATUS_DESC")
    private String curentStatusDesc;

    @Column (name= "NEXT_STATUS_DESC")
    private String nextStatusDesc;

    @Column(name="MESSAGE_DATE")
    private Date messageDate;

    @Column(name="USER_NAME")
    private String userName;

}
