package ru.vtb.microservices.carpark.preorders.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.vtb.microservices.carpark.cars.model.NextStatusEvent;

public class KafkaProducerServiceImplTest {

    private String kafkaTopic;
    private MockMvc mockMvc;
    @Mock
    private KafkaTemplate<String, NextStatusEvent> kafkaTemplate;
    @Mock
    private ListenableFuture<SendResult<String, NextStatusEvent>> listenableFuture;
    @InjectMocks
    private KafkaProducerServiceImpl kafkaProducerService;

    private NextStatusEvent nextStatusEvent;

    @BeforeMethod
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(kafkaProducerService)
                .build();
        kafkaTopic = "next.status";
        nextStatusEvent = new NextStatusEvent();
        reset(kafkaTemplate);
        reset(listenableFuture);
    }

    @Test
    public void testSendMessage() {
        when(kafkaTemplate.send(null, nextStatusEvent)).thenReturn(listenableFuture);
        when(kafkaTemplate.send(kafkaTopic, nextStatusEvent)).thenReturn(listenableFuture);
        kafkaProducerService.sendMessage(nextStatusEvent);
        verify(kafkaTemplate, atLeastOnce()).send(eq(null), eq(nextStatusEvent));
    }

}