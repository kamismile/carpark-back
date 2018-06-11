package ru.vtb.microservices.carpark.preorders.service;

import ru.vtb.microservices.carpark.cars.model.NextStatus;
import ru.vtb.microservices.carpark.cars.model.PreorderType;
import ru.vtb.microservices.carpark.preorders.model.Preorder;
import ru.vtb.microservices.carpark.preorders.repository.PreorderRepository;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
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
    private Long preorder_id = 1111l;
    private Long preorder_car_id = 2222l;
    private Date futureDate;
    private Date presentDate;
    private NextStatus nextStatus;
    private String PREORDER_TYPE_IN_USE = "in_use";
    private String futureString = "01.01.9999";
    private String presentString = "01.01.2022";
    private DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);

    @BeforeMethod
    public void setupMock() {
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
        when(preorderRepository.findOne(anyLong())).thenReturn(preorder);
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

    @Test
    public void testGetEarliestPreorder() {
        when(preorderRepository.findByCarId(anyLong())).thenReturn(new ArrayList<Preorder>());
        when(preorderRepository.findByCarId(preorder_car_id)).thenReturn(preorderList);
        Preorder preorderTest = preorderService.getEarliestPreorder(preorder_car_id);
        assertNotNull(preorderTest);
        Preorder preorderTest2 = preorderService.getEarliestPreorder(5555555l);
        assertNull(preorderTest2);
    }

    private Preorder getDefaultPreorder() {
        Preorder preorder = new Preorder();
        preorder.setId(preorder_id);
        preorder.setCarId(preorder_car_id);
        preorder.setLeaseStartDate(presentDate);
        preorder.setLeaseEndDate(futureDate);
        preorder.setType(PreorderType.BOOKING);
        return preorder;
    }

    private NextStatus getDefaultNextStatus() {
        NextStatus nextStatus = new NextStatus(preorder_car_id, PREORDER_TYPE_IN_USE, presentDate, PreorderType.BOOKING);
        return nextStatus;
    }
}