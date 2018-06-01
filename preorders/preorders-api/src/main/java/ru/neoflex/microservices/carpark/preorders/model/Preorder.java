package ru.neoflex.microservices.carpark.preorders.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table
public class Preorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "CLIENT_SURNAME")
    private String clientSurname;

    @Column(name = "CLIENT_PATRONYMIC")
    private String clientPatronymic;

    private String phone;

    private String email;

    @Column(name = "LEASE_START_DATE")
    private Date leaseStartDate;

    @Column(name = "LEASE_END_DATE")
    private Date leaseEndDate;

    @Column(name = "START_LOCATION_ID")
    private Long startLocationId;

    @Column(name = "END_LOCATION_ID")
    private Long endLocationId;

    @Column(name = "CAR_ID")
    private Long carId;

    @Column (name = "CREATED_BY_USER")
    private String createdByUser;

    public boolean overlaps (Preorder other) {
        //TODO overlaps implementation
        return false;
    }


}
