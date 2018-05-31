package ru.neoflex.microservices.carpark.commons.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author rmorenko
 */
@Data
@EqualsAndHashCode
@ToString
public class KafkaCommand<T> implements Serializable{
     private Command command;
     private T entity;


}
