package com.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CustomerDTO;
import com.service.KafkaConsumerService;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private static Logger LOG = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
	
	public KafkaConsumerServiceImpl() {
		super();
	}

	@Override
	@Transactional("kafkaTransactionManager")
	@KafkaListener(id="transactions", topics = "my-topic", groupId = "my-group")
	public void listen(CustomerDTO message, ConsumerRecord<String, String> record) {
		LOG.info("received message=[{}], key=[{}] from topic=[{}], partition=[{}], offset=[{}], TS=[{}]", 
				message, record.key(), record.topic(), record.partition(), record.offset(), record.timestamp());
	}

}
