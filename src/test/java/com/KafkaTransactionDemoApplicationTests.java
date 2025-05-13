package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.dto.CustomerDTO;
import com.service.KafkaConsumerService;
import com.service.KafkaProducerService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaTransactionDemoApplicationTests {

    @Autowired
    private KafkaProducerService producer;

    @Autowired
    private KafkaConsumerService consumer;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived() {
        CustomerDTO customer = CustomerDTO.builder().id(1).name("Gustavo").build();
        producer.sendEventsToTopic(customer);
    }

}