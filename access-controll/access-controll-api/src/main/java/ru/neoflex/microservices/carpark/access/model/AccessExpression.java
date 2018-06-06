package ru.neoflex.microservices.carpark.access.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Expressions for access controll
 * @author rmorenko.
 */
@Data
@Entity
@Table(name = "ACCESS_EXPRESSIONS")
@EqualsAndHashCode
public class AccessExpression {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "OPERATION_NAME")
        private String operation;

        private String expression;

}
