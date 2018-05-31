package ru.neoflex.microservices.carpark.preorders.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
}
