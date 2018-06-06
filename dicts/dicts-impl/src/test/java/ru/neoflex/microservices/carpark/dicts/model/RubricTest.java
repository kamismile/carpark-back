package ru.neoflex.microservices.carpark.dicts.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RubricTest {

    private Rubric rubric;

    @BeforeMethod
    public void setUp() throws Exception {
        rubric = new Rubric();
    }

    @Test
    public void defaultRubricTest() {
        Assert.assertNull(rubric.getCode(), "This code is not null.");
        Assert.assertNull(rubric.getTitle(), "This title is not null.");
    }

    @Test
    public void rubricTest() {
        rubric = getDefaultRubric();
        Assert.assertEquals("1", rubric.getCode(), "This code is incorrect.");
        Assert.assertEquals("Title", rubric.getTitle(), "This title is incorrect.");
    }

    private Rubric getDefaultRubric() {
        Rubric rubric = new Rubric();
        rubric.setCode("1");
        rubric.setTitle("Title");
        rubric.setSystem(true);
        return rubric;
    }
}