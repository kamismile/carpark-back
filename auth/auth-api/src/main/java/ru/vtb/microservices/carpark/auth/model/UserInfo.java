package ru.vtb.microservices.carpark.auth.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @Column(name = "PASSWORD_HASH", length = 300)
    private String password;

    private String role;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column
    private Boolean active;
}
