package ru.neoflex.microservices.carpark.dict.report.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import ru.neoflex.microservices.carpark.dict.report.reciver.Receiver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vanosov
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
                props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

                return props;
        }

        @Bean
        public ConsumerFactory<String, String> consumerFactory() {
                return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                        new StringDeserializer());
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
                ConcurrentKafkaListenerContainerFactory<String, String> factory =
                        new ConcurrentKafkaListenerContainerFactory<>();
                factory.setConsumerFactory(consumerFactory());
                factory.setMessageConverter(new StringJsonMessageConverter());
                return factory;
        }

        @Bean
        public Receiver receiver() {
                return new Receiver();
        }
}
