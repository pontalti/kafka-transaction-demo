package com.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dto.CustomerDTO;

public interface KafkaConsumerService {
	public void listen(CustomerDTO customer, ConsumerRecord<String, String> record);
	
    public void listenDLT(CustomerDTO customer, ConsumerRecord<String, String> record);
}
