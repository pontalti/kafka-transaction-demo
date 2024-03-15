package com.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class kafkaProducerConfig {
	
	public kafkaProducerConfig() {
		super();
	}
	
	@Bean
    public NewTopic createTopic(){
        return new NewTopic("my-topic", 3, (short) 1);
    }

}
