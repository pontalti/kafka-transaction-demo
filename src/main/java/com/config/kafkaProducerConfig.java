package com.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class kafkaProducerConfig {
	
    @Value("${spring.kafka.topic.name}")
    private String topic;

	public kafkaProducerConfig() {
		super();
	}
	
	@Bean
    public NewTopic createTopic(){
        return new NewTopic(topic, 3, (short) 1);
    }

}
