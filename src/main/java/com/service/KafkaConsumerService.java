package com.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dto.CustomerDTO;

public interface KafkaConsumerService {
	public void listen(CustomerDTO message, ConsumerRecord<String, String> record);
}
