package ru.vtb.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.employeeInLocationTypes;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.locationIsActive;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.locationLikeAddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.repository.LocationRepository;
import ru.vtb.microservices.carpark.employees.dto.LocationCommand;
import ru.vtb.microservices.carpark.employees.sender.Sender;
import ru.vtb.microservices.carpark.employees.dto.LocationCommand;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.repository.LocationRepository;
import ru.vtb.microservices.carpark.employees.repository.LocationSpecifications;
import ru.vtb.microservices.carpark.employees.sender.Sender;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class LocationServiceImplTest {

    private static Long LOCATION_ID = 111l;
    private static final String LOCATION_ADDRESS = "address";
    private static final String LOCATION_TYPE = "locationType";
    private static final String locationTopic = null;

    @Autowired
    private LocationRepository locationRepository = mock(LocationRepository.class);
    @Autowired
    private Sender sender = mock(Sender.class);
    @Autowired
    private LocationService locationService;

    private Location location;
    private List<Location> locationList;
    private LocationFilter filter;

    @BeforeMethod
    public void setupMock() {
        reset(locationRepository);
        reset(sender);
        locationService = new LocationServiceImpl(locationRepository, sender);
        location = new Location();
        location.setAddress(LOCATION_ADDRESS);
        location.setLocationType(LOCATION_TYPE);
        locationList = new ArrayList<Location>();
        locationList.add(location);
    }

    @Test
    public void testGetById() {
        when(locationRepository.findOne(anyLong())).thenReturn(location);
        when(locationRepository.findOne((Long)null)).thenReturn(null);
        when(locationService.getById(anyLong())).thenReturn(location);
        when(locationService.getById(null)).thenReturn(null);

        Location locationAny = locationService.getById(LOCATION_ID);
        Location locationNull = locationService.getById(null);

        assertNotNull(locationAny);
        assertNull(locationNull);
        assertEquals(locationAny.getAddress(), LOCATION_ADDRESS);
        verify(locationRepository, atLeastOnce()).findOne(eq(LOCATION_ID));
        verify(locationRepository, atMost(1)).findOne((Long) eq(null));
    }

    @Test
    public void testDeactivate() {
        when(locationRepository.findOne(anyLong())).thenReturn(location);
        when(locationRepository.save(location)).thenReturn(location);

        locationService.deactivate(LOCATION_ID);

        verify(locationRepository, atLeastOnce()).findOne(eq(LOCATION_ID));
        verify(locationRepository, atLeastOnce()).save(eq(location));
    }

    @Test
    public void testAdd() {
        when(locationRepository.save(location)).thenReturn(location);
        doNothing().when(sender).send(isA(String.class), isA(LocationCommand.class));

        locationService.add(location);

        verify(locationRepository, atLeastOnce()).save(eq(location));
    }

    @Test
    public void testUpdate() {
        when(locationRepository.findOne(anyLong())).thenReturn(location);
        when(locationRepository.save(location)).thenReturn(location);

        locationService.update(location);

        verify(locationRepository, atLeastOnce()).save(eq(location));
    }

    @Test
    public void testGetAll() {
        when(locationRepository.findAll(where(LocationSpecifications.locationLikeAddresses(filter))
                .and(LocationSpecifications.locationIsActive(filter))
                .and(LocationSpecifications.employeeInLocationTypes(filter)))).thenReturn(locationList);

        assertNotNull(locationService.getAll(filter));
    }
}