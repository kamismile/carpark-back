/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.access;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Roman_Mmorenko
 */
@SpringBootTest(classes = {AccessApplication.class})
public class AccessIT extends AbstractTestNGSpringContextTests {

    @Test
    public void injectionTest() {

    }


}
