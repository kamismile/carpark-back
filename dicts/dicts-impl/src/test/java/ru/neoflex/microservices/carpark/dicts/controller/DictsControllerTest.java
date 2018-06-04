package ru.neoflex.microservices.carpark.dicts.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.DictsApplication;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.service.RubricService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DictsApplication.class)
public class DictsControllerTest {

    @Mock
    private RubricService rubricService;

    @InjectMocks
    private DictsController dictsController;

    private MockMvc mockMvc;
    private Rubric rubric;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dictsController)
                .build();
    }

    @Test
    public void getRubricsTest() throws Exception {
        rubric = getDefaultRuberic();
        when(rubricService.findAll()).thenReturn(Arrays.asList(rubric));

        mockMvc.perform(get("/rubrics")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("1")))
                .andExpect(jsonPath("$[0].title", is(rubric.getTitle())))
                .andExpect(jsonPath("$[0].system", is(true)));
    }

    private Rubric getDefaultRuberic() {
        Rubric rubric = new Rubric();
        rubric.setCode("1");
        rubric.setTitle("Title");
        rubric.setSystem(true);
        return rubric;
    }
}