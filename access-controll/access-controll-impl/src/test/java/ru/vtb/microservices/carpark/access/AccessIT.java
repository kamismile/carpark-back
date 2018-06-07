package ru.vtb.microservices.carpark.access;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = {AccessApplication.class})
public class AccessIT extends AbstractTestNGSpringContextTests {

    @Test
    public void injectionTest() {

    }


}
