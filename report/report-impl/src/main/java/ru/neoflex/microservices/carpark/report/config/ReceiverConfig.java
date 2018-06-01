package ru.neoflex.microservices.carpark.report.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.reciver.CarsMessageReceiver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rmorenko
 */
@Configuration
@EnableKafka
public class ReceiverConfig {

        @Value("${kafka.bootstrap-servers}")
        private String bootstrapServers;

        @Bean
        public Map<String, Object> consumerConfigs() {
                Map<String, Object> props = new HashMap<>();
                props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
                props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
                props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

                return props;
        }

        @Bean
        public ConsumerFactory<String, CarCommand> consumerFactory() {
                return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                        new JsonDeserializer<>(CarCommand.class));
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, CarCommand> kafkaListenerContainerFactory() {
                ConcurrentKafkaListenerContainerFactory<String, CarCommand> factory =
                        new ConcurrentKafkaListenerContainerFactory<>();
                factory.setConsumerFactory(consumerFactory());

                return factory;
        }

        @Bean
        public CarsMessageReceiver receiver() {
                return new CarsMessageReceiver();
        }
}
