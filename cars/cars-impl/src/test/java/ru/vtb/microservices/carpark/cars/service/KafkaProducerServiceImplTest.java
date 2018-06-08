package ru.vtb.microservices.carpark.cars.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.cars.model.CarCommand;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class KafkaProducerServiceImplTest {

    private String kafkaTopic;
    private MockMvc mockMvc;
    @Mock
    private KafkaTemplate<String, CarCommand> kafkaTemplate;
    @Mock
    private ListenableFuture<SendResult<String, CarCommand>> listenableFuture;
    @InjectMocks
    private KafkaProducerServiceImpl kafkaProducerService;

    private CarCommand carCommand;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(kafkaProducerService)
                .build();
        carCommand = new CarCommand();
        reset(kafkaTemplate);
        reset(listenableFuture);
    }

    @Test
    public void testSendMessage() {
        when(kafkaTemplate.send(null, "carCommand", carCommand)).thenReturn(listenableFuture);
        kafkaProducerService.sendMessage(carCommand);
        verify(kafkaTemplate, atLeastOnce()).send(eq(null), eq("carCommand"), eq(carCommand));
    }
}
