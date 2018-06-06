package ru.neoflex.microservices.carpark.dicts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.DictsApplication;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;
import ru.neoflex.microservices.carpark.dicts.service.RubricService;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(classes = DictsApplication.class)
public class DictsControllerTest {

    private static final String CODE = "1";
    private static final String TITLE = "Title";
    private static final boolean SYSTEM = true;
    private static final boolean ACTIVE = true;
    private  ObjectMapper mapper;
    private MockMvc mockMvc;

    @Mock
    private RubricService rubricService;
    @Mock
    private ReferenceService referenceService;

    @InjectMocks
    private DictsController dictsController;

    @BeforeMethod
    public void setUp() {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dictsController)
                .build();
    }

//    @Test
//    public void getReferencesByRubricTest() throws Exception {
//        Rubric rubric = getDefaultRubric();
//        Reference reference = getDefaultReference();
//
//        when(rubricService.findByCode(rubric.getCode())).thenReturn(rubric);
//        when(referenceService.findByRubric(reference.getRubric())).thenReturn(Arrays.asList(reference));
//
//        mockMvc.perform(get("/references/{rubricCode}", reference.getRubric().getCode())
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].code", is(reference.getCode())))
//                .andExpect(jsonPath("$[0].title", is(reference.getTitle())))
//                .andExpect(jsonPath("$[0].rubric.code", is(reference.getRubric().getCode())))
//                .andExpect(jsonPath("$[0].rubric.title", is(reference.getRubric().getTitle())))
//                .andExpect(jsonPath("$[0].rubric.system", is(SYSTEM)))
//                .andExpect(jsonPath("$[0].system", is(SYSTEM)))
//                .andExpect(jsonPath("$[0].active", is(ACTIVE)));
//
//        verify(rubricService, times(1)).findByCode(rubric.getCode());
//        verify(referenceService, times(1)).findByRubric(reference.getRubric());
//        verifyNoMoreInteractions(rubricService);
//        verifyNoMoreInteractions(referenceService);
//    }
//
//    @Test
//    public void getRubricsTest() throws Exception {
//        Rubric rubric = getDefaultRubric();
//        when(rubricService.findAll()).thenReturn(Arrays.asList(rubric, rubric));
//
//        mockMvc.perform(get("/rubrics")
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].code", is(rubric.getCode())))
//                .andExpect(jsonPath("$[0].title", is(rubric.getTitle())))
//                .andExpect(jsonPath("$[0].system", is(SYSTEM)))
//                .andExpect(jsonPath("$[1].code", is(rubric.getCode())))
//                .andExpect(jsonPath("$[1].title", is(rubric.getTitle())))
//                .andExpect(jsonPath("$[1].system", is(SYSTEM)));
//
//        verify(rubricService, times(1)).findAll();
//        verifyNoMoreInteractions(rubricService);
//    }

    @Test
    public void createRubricTest() throws Exception {
        Reference reference = new Reference();
        doNothing().when(referenceService).createReference(reference);

        mockMvc.perform(post("/references")
                .content(mapper.writeValueAsString(reference))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

//        verify(rubricService, times(1)).createRubric(rubric);
//        verifyNoMoreInteractions(rubricService);
    }

    private Rubric getDefaultRubric() {
        Rubric rubric = new Rubric();
        rubric.setCode(CODE);
        rubric.setTitle(TITLE);
        rubric.setSystem(SYSTEM);
        return rubric;
    }

    private Reference getDefaultReference() {
        Reference reference = new Reference();
        reference.setCode(CODE);
        reference.setTitle(TITLE);
        reference.setSystem(SYSTEM);
        reference.setActive(SYSTEM);
        reference.setRubric(getDefaultRubric());
        return reference;
    }
}