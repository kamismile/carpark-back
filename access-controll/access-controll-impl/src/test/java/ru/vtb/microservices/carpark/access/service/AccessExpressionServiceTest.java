package ru.vtb.microservices.carpark.access.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.vtb.microservices.carpark.access.model.AccessExpression;
import ru.vtb.microservices.carpark.access.repository.AccessExpressionRepository;

public class AccessExpressionServiceTest {

    private MockMvc mockMvc;
    @Mock
    private AccessExpressionRepository accessExpressionRepository;
    @InjectMocks
    private AccessExpressionService accessExpressionService;

    private AccessExpression accessExpression;
    private String OPERATION = "operation";
    private String EXPRESSION = "expression";
    private List<AccessExpression> accessExpressionList;
    private Long ACCESS_EXPRESSION_ID = 11111l;

    @BeforeMethod
    public void setupMock() {
        accessExpression = new AccessExpression(OPERATION, EXPRESSION);
        accessExpressionList = new ArrayList<AccessExpression>();
        accessExpressionList.add(accessExpression);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accessExpressionService).build();
        reset(accessExpressionRepository);
    }

    @Test
    public void testGetByOperation() {
        when(accessExpressionRepository.findByOperation(anyString())).thenReturn(accessExpressionList);
        accessExpressionService.getByOperation(OPERATION);
        verify(accessExpressionRepository, atLeastOnce()).findByOperation(eq(OPERATION));
    }

    @Test
    public void testGetAll() {
        when(accessExpressionRepository.findAll()).thenReturn(accessExpressionList);
        List<AccessExpression> accessExpressionListTest = accessExpressionService.getAll();
        verify(accessExpressionRepository, atLeastOnce()).findAll();
        assertEquals(accessExpressionListTest, accessExpressionList);
    }

    @Test
    public void testAdd() {
        when(accessExpressionRepository.save(accessExpression)).thenReturn(accessExpression);
        AccessExpression accessExpressionTest = accessExpressionService.add(accessExpression);
        verify(accessExpressionRepository, atLeastOnce()).save(eq(accessExpression));
        assertEquals(accessExpressionTest, accessExpression);
    }

    @Test
    public void testDelete() {
        doNothing().when(accessExpressionRepository).delete(isA(AccessExpression.class));
        accessExpressionService.delete(ACCESS_EXPRESSION_ID);
        verify(accessExpressionRepository, atLeastOnce()).delete(eq(ACCESS_EXPRESSION_ID));
    }

    @Test
    public void testUpdate() {
        when(accessExpressionRepository.save(accessExpression)).thenReturn(accessExpression);
        accessExpressionService.update(accessExpression);
        verify(accessExpressionRepository, atLeastOnce()).save(eq(accessExpression));
    }

    @Test
    public void testGet() {
        when(accessExpressionRepository.findOne(anyLong())).thenReturn(accessExpression);
        AccessExpression accessExpressionTest = accessExpressionService.get(ACCESS_EXPRESSION_ID);
        verify(accessExpressionRepository, atLeastOnce()).findOne(eq(ACCESS_EXPRESSION_ID));
        assertEquals(accessExpressionTest, accessExpression);
    }
}