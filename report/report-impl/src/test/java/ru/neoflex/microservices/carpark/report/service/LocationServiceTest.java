package ru.neoflex.microservices.carpark.report.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.report.model.LocationCommand;
import ru.neoflex.microservices.carpark.report.repository.LocationRepository;

public class LocationServiceTest {

    private MockMvc mockMvc;
    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService locationService;

    private Long LOCATION_ID = 111111l;
    private String LOCATION_ADDRESS = "Saratov";
    private Location location;
    private LocationCommand locationCommand;

    @BeforeMethod
    public void setupMock() {
        location = new Location();
        location.setId(LOCATION_ID);
        location.setAddress(LOCATION_ADDRESS);
        locationCommand = new LocationCommand();
        locationCommand.setEntity(location);
        locationCommand.setOldEntity(location);
        locationCommand.setCommand(Command.ADD);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(locationService).build();
        reset(locationRepository);
    }

    @Test
    public void testSave() {
        when(locationRepository.save(location)).thenReturn(location);
        when(locationRepository.findByAddress(anyString())).thenReturn(location);
        doNothing().when(locationRepository).delete(isA(Location.class));

        locationService.save(locationCommand);

        verify(locationRepository, atLeastOnce()).save(eq(location));
    }

}