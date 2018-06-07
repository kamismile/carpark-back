/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.access.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

/**
 * Expressions for access control.
 *
 * @author Roman_Morenko
 */
@Data
@Entity
@Table(name = "ACCESS_EXPRESSIONS")
@EqualsAndHashCode
@NoArgsConstructor
public class AccessExpression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPERATION_NAME")
    private String operation;

    private String expression;

    public AccessExpression(String operation, String expression) {
        this.operation = operation;
        this.expression = expression;
    }
}
