package ru.neoflex.microservices.carpark.access.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Expresions for access controll
 * @autor rmorenko.
 */
@Data
@Entity
@Table(name="ACCESS_EXPRESSIONS")
@EqualsAndHashCode
public class AccessExpresion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "OPERATION_NAME")
        private String operation;

        private String expression;

}
