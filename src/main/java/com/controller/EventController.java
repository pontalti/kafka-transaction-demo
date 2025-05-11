package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CustomerDTO;
import com.service.KafkaProducerService;

@RestController
public class EventController {

	private final KafkaProducerService producer;

	public EventController(KafkaProducerService producer) {
		super();
		this.producer = producer;
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> sendEvents(@RequestBody CustomerDTO customer) {
		this.producer.sendEventsToTopic(customer);
		ResponseEntity<?> response = new ResponseEntity<String>(HttpStatus.ACCEPTED);
        return response;
	}

}
