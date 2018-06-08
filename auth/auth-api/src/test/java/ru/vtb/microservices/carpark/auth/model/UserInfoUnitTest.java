package ru.vtb.microservices.carpark.auth.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserInfoUnitTest {

    private UserInfo userInfo = new UserInfo();
    private static final Long ID = 12L;
    private static final String LOGIN = "LOGIN";
    private static final String PASS = "PASSWORDS";
    private static final String ROLE = "USER";
    private static final Long LOCATION_ID = 10L;

    @Test
    public void testSetGetId() {
        userInfo.setId(ID);
        assertEquals(ID, userInfo.getId());
    }

    @Test
    public void testSetGetLogin() {
        userInfo.setLogin(LOGIN);
        assertEquals(LOGIN, userInfo.getLogin());
    }

    @Test
    public void testSetGetPassword() {
        userInfo.setPassword(PASS);
        assertEquals(PASS,  userInfo.getPassword());
    }

    @Test
    public void testSetGetRole() {
        userInfo.setRole(ROLE);
        assertEquals(ROLE, userInfo.getRole());
    }

    @Test
    public void testSetLocationId() {
        userInfo.setLocationId(LOCATION_ID);
        assertEquals(LOCATION_ID, userInfo.getLocationId());
    }
}