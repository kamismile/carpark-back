package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.model.PreorderType;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Date;
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

    @BeforeMethod
    public void setupMock() {
        preorder_id = 1111l;
        preorder_car_id = 2222l;
        reset(preorderRepository);
        reset(kafkaService);
        preorderService = new PreorderServiceImpl(preorderRepository, kafkaService);
        userInfo = new UserInfo();
        preorder = getDefaultPreorder();
        preorderList = new ArrayList<Preorder>();
        preorderList.add(preorder);
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

    private Preorder getDefaultPreorder() {
        Preorder preorder = new Preorder();
        preorder.setId(preorder_id);
        preorder.setCarId(preorder_car_id);
        Date future = new Date();
        future.setYear(99999);
        preorder.setLeaseStartDate(future);
        preorder.setLeaseEndDate(new Date());
        preorder.setType(PreorderType.BOOKING);
        return preorder;
    }
}