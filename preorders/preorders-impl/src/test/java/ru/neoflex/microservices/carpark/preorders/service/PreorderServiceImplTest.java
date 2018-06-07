package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.cars.model.NextStatus;
import ru.neoflex.microservices.carpark.cars.model.PreorderType;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

public class PreorderServiceImplTest {

    @Autowired
    private final PreorderRepository preorderRepository = mock(PreorderRepository.class);
    @Autowired
    private final KafkaProducerService kafkaService = mock(KafkaProducerService.class);
    @Autowired
    PreorderService preorderService;

    private UserInfo userInfo;
    private Preorder preorder;
    private List<Preorder> preorderList;
    private Long preorder_id;
    private Long preorder_car_id;
    private Date futureDate;
    private Date presentDate;
    private NextStatus nextStatus;

    @BeforeMethod
    public void setupMock() {
        preorder_id = 1111l;
        preorder_car_id = 2222l;
        String futureString = "01.01.9999";
        String presentString = "01.01.2022";
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
        try {
            futureDate = format.parse(futureString);
            presentDate = format.parse(presentString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reset(preorderRepository);
        reset(kafkaService);
        preorderService = new PreorderServiceImpl(preorderRepository, kafkaService);
        userInfo = new UserInfo();
        preorder = getDefaultPreorder();
        preorderList = new ArrayList<Preorder>();
        preorderList.add(preorder);
        nextStatus = getDefaultNextStatus();
    }

    @Test
    public void testFindAll() {
        when(preorderRepository.findAll()).thenReturn(preorderList);
        List<Preorder> preorderListTest = preorderService.findAll();
        assertNotNull(preorderListTest);
        verify(preorderRepository, atLeastOnce()).findAll();
        assertEquals(preorderListTest, preorderList);
    }

    @Test
    public void testGetPreorder() {
        when(preorderRepository.getOne(anyLong())).thenReturn(preorder);
        Preorder preorderTest = preorderService.getPreorder(preorder_id);
        assertEquals(preorderTest, preorder);
    }

    @Test
    public void testAddPreorder() {
        when(preorderRepository.save(preorder)).thenReturn(preorder);
        when(preorderRepository.findByCarId(anyLong())).thenReturn(preorderList);
        Preorder preorderTest = preorderService.addPreorder(preorder, userInfo);
        verify(preorderRepository, atLeastOnce()).save(eq(preorder));
        assertEquals(preorderTest, preorder);
    }

    @Test
    public void testGetNextStatusForCar() {
        when(preorderRepository.findByCarId(anyLong())).thenReturn(preorderList);
        NextStatus nextStatusTest = preorderService.getNextStatusForCar(preorder_car_id);
        assertNotNull(nextStatusTest);
        assertEquals(nextStatusTest, nextStatus);
    }

    private Preorder getDefaultPreorder() {
        Preorder preorder = new Preorder();
        preorder.setId(preorder_id);
        preorder.setCarId(preorder_car_id);
        preorder.setLeaseStartDate(futureDate);
        preorder.setLeaseEndDate(presentDate);
        preorder.setType(PreorderType.BOOKING);
        return preorder;
    }

    private NextStatus getDefaultNextStatus() {
        NextStatus nextStatus = new NextStatus(preorder_car_id, "in_use", futureDate, PreorderType.BOOKING);
        return nextStatus;
    }
}