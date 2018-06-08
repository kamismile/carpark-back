package ru.vtb.microservices.carpark.dict.report.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dict.report.model.ReferenceCommand;
import ru.vtb.microservices.carpark.dict.report.repository.ReferenceRepository;

public class ReferenceServiceTest {

    private MockMvc mockMvc;
    @Mock
    private ReferenceRepository referenceRepository;
    @InjectMocks
    private ReferenceService referenceService;

    private Reference reference;
    private ReferenceCommand referenceCommand;

    @BeforeMethod
    public void setupMock() {
        reference = new Reference();
        referenceCommand = new ReferenceCommand();
        referenceCommand.setEntity(reference);
        referenceCommand.setOldEntity(reference);
        referenceCommand.setCommand(Command.ADD);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(referenceService).build();
        reset(referenceRepository);
    }

    @Test
    public void testSave() {
        when(referenceRepository.save(reference)).thenReturn(reference);
        referenceService.save(referenceCommand);
        verify(referenceRepository, atLeastOnce()).save(eq(reference));
    }

}