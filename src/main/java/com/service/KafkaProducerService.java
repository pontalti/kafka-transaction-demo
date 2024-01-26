package com.service;

import com.dto.CustomerDTO;

public interface KafkaProducerService {

	public void sendEventsToTopic(CustomerDTO customer);
}
