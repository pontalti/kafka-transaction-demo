package com.service.impl;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dto.CustomerDTO;
import com.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService{
	
	private static Logger LOG = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	private final KafkaTemplate<String, Object> template;

	@Value("${spring.kafka.topic.name}")
    private String topic;

	public KafkaProducerServiceImpl(KafkaTemplate<String, Object> template) {
		super();
		this.template = template;
	}

	@Async
	@Override
	public void sendEventsToTopic(CustomerDTO customer) {
		try {
			CompletableFuture<SendResult<String, Object>> future = this.template.send(topic, UUID.randomUUID().toString(), customer);
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					LOG.info("Sent message=[{}] to partition [{}] with offset=[{}]", customer.toString(), 
																						result.getRecordMetadata().partition(), 
																						result.getRecordMetadata().offset());
				} else {
					LOG.info("Unable to send message=[{}] due to : {}", customer.toString(), ex.getMessage());
				}
			});
		} catch (Exception ex) {
			LOG.error("Exception : {}", ex);
		}
	}

}