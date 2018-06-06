package ru.neoflex.microservices.carpark.employees.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Location entity.
 *
 * @author mirzoevnik
 */
@Data
@Entity
@Table
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS", length = 300)
    private String address;

    @Column(name = "LOCATION_TYPE")
    private String locationType;

    private boolean active;

}
