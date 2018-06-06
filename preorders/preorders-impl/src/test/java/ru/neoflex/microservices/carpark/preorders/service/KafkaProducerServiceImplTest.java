package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.preorders.model.NextStatusEvent;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class KafkaProducerServiceImplTest {

    private String kafkaTopic;
    @Autowired
    private KafkaTemplate<String, NextStatusEvent> kafkaTemplate = mock(KafkaTemplate.class);
    @Autowired
    private KafkaProducerService kafkaProducerService;

    private NextStatusEvent nextStatusEvent;
    private ListenableFuture<SendResult<String, NextStatusEvent>> listenableFuture = mock(ListenableFuture.class);

    @BeforeMethod
    public void setupMock() {
        kafkaTopic = "next.status";
        nextStatusEvent = new NextStatusEvent();
        reset(kafkaTemplate);
        reset(listenableFuture);
        kafkaProducerService = new KafkaProducerServiceImpl();
    }

    @Test
    public void testSendMessage() {
        when(kafkaTemplate.send(null, nextStatusEvent)).thenReturn(listenableFuture);
        when(kafkaTemplate.send(kafkaTopic, nextStatusEvent)).thenReturn(listenableFuture);
        kafkaTemplate.send(kafkaTopic, nextStatusEvent);
        kafkaTemplate.send(null, nextStatusEvent);
        verify(kafkaTemplate, atLeastOnce()).send(eq(kafkaTopic), eq(nextStatusEvent));
    }

}