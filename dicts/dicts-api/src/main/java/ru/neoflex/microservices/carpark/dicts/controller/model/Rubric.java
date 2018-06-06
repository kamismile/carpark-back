package ru.neoflex.microservices.carpark.dicts.controller.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mirzoevnik
 */
@Entity
@Table(name = "DICT_RUBRIC")
@Data
public class Rubric implements Serializable {

    @Id
    private String code;

    private String title;

    private boolean system;
}
