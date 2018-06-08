/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.model;

import lombok.Data;
import ru.vtb.microservices.carpark.cars.model.PreorderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * Сущность предзаказа.
 *
 * @author Denis_Begun
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

    @Column(name = "CREATED_BY_USER")
    private String createdByUser;

    @Column(name = "PREORDER_TYPE")
    @Enumerated(EnumType.STRING)
    private PreorderType type;

    public boolean overlaps(Preorder other) {
        Date start1 = this.getLeaseStartDate();
        Date end1 = this.getLeaseEndDate();
        Date start2 = other.getLeaseStartDate();
        Date end2 = other.getLeaseEndDate();
        return datesOverlap(start1, end1, start2, end2);
    }

    private boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
    }

    public boolean isInFuture() {
        return this.getLeaseStartDate().getTime() > new Date().getTime();
    }

    public boolean isDurationOk() {
        return this.getLeaseStartDate().getTime() < this.getLeaseEndDate().getTime();
    }


}
