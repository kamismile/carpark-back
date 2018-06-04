package ru.neoflex.microservices.carpark.dicts.model;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.DictsApplication;

import static org.testng.Assert.*;

@SpringBootTest(classes = DictsApplication.class)
public class ReferenceTest {

    private Reference reference;

    @BeforeMethod
    public void setUp() throws Exception {
        reference = new Reference();
    }

    @Test
    public void defaultRubricTest() {
        Assert.assertNull(reference.getCode(), "This code is not null.");
        Assert.assertNull(reference.getTitle(), "This title is not null.");
        Assert.assertNull(reference.getRubric(), "This rubric is not null.");
    }

    @Test
    public void referenceTest() {
        reference = getDefaultReference();
        Assert.assertEquals("1", reference.getCode(), "This code is incorrect.");
        Assert.assertEquals("Title", reference.getTitle(), "This title is incorrect.");
        Assert.assertEquals(new Rubric(), reference.getRubric(), "This rubric is incorrect.");
    }

    private Reference getDefaultReference() {
        Reference reference = new Reference();
        reference.setCode("1");
        reference.setTitle("Title");
        reference.setActive(true);
        reference.setSystem(true);
        reference.setRubric(new Rubric());
        return reference;
    }
}