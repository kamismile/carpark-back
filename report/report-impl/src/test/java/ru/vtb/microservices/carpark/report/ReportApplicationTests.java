package ru.vtb.microservices.carpark.report;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vtb.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.vtb.microservices.carpark.report.reciver.Receiver;
import ru.vtb.microservices.carpark.report.reciver.Sender;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ReportApplicationTests {

    private static final String HELLOWORLD_TOPIC = "car.t";

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);



    @MockBean AccessExpressionFeign feign;

    @Before
    public void setUp() throws Exception {
          for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    embeddedKafka.getPartitionsPerTopic());
        }
    }

    @Test
    public void testReceive() throws Exception {
        /*Car car = new Car();
        car.setId(1L);
        car.setMark("Рыдван");
        car.setCurrentStatusDate(new Date());
        CarCommand command = new CarCommand();
        command.setCommand(Command.ADD);
        sender.send(command);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);*/
    }

}
