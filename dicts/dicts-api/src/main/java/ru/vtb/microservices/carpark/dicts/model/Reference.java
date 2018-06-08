package ru.vtb.microservices.carpark.dicts.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mirzoevnik
 */
@Entity
@Table(name = "DICT_REFERENCE")
@Data
public class Reference implements Serializable {

    @Id
    private String code;

    private String title;

    private boolean system;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "RUBRIC_CODE")
    private Rubric rubric;
}
