package ru.vtb.microservices.carpark.auth.dto;

import lombok.Data;

/**
 *  @author rmorenko
 */
@Data
public class AuthRequestParams {

     protected String grantType;
     protected String login;
     private String redirectUri;
     private String password;
}
