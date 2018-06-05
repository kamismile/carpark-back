package ru.neoflex.microservices.carpark.access;


import org.omg.PortableServer.POAManagerPackage.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest(classes = {AccessApplication.class})
public class AccessIT extends AbstractTestNGSpringContextTests {

    @Test
    public void injectionTest() {

    }


}
