package com.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import com.dto.CustomerDTO;
import com.service.KafkaConsumerService;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private static Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
	
	public KafkaConsumerServiceImpl() {
		super();
	}
	
	@Override
	@RetryableTopic(attempts = "4")
	@KafkaListener(id="transactions", topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void listen(CustomerDTO customer, ConsumerRecord<String, String> record) {
		log.info("received message=[{}], key=[{}] from topic=[{}], partition=[{}], offset=[{}], TS=[{}]", 
				customer, record.key(), record.topic(), record.partition(), record.offset(), record.timestamp());
		if( customer.getId() == 0 ) {
			throw new RuntimeException("Invalid ID !");
		}
	}
	
	@Override
    @DltHandler
    public void listenDLT(CustomerDTO customer, ConsumerRecord<String, String> record) {
		log.info("DLT received message=[{}], key=[{}] from topic=[{}], partition=[{}], offset=[{}], TS=[{}]", 
				customer, record.key(), record.topic(), record.partition(), record.offset(), record.timestamp());
    }
	
}
