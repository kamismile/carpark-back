package ru.neoflex.microservices.carpark.commons.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rmorenko
 */
@Data
@EqualsAndHashCode
@ToString
public class KafkaCommand<T> implements Serializable{
     private Command command;
     private UserInfo userInfo;
     private Date messageDate;
     private T entity;
}
