/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.dto;

import lombok.Data;

/**
 *  AuthRequestParams dto.
 *
 *  @author Roman_Morenko
 */
@Data
public class AuthRequestParams {

    protected String grantType;
    protected String login;
    private String redirectUri;
    private String password;
}
