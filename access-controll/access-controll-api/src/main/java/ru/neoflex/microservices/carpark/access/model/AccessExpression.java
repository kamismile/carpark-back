package ru.neoflex.microservices.carpark.access.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Expressions for access control
 * @author rmorenko.
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
