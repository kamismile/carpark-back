package ru.neoflex.microservices.carpark.employees.model;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.io.Serializable;

/**
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
}
