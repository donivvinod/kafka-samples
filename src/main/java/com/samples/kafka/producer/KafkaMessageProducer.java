package com.samples.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.samples.kafka.utils.SampleUtil;

@Service
public class KafkaMessageProducer implements MessageProducer {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.sample}")
    private String topic;
    
	@Override
	public void sendARandomMessage() {
		kafkaTemplate.send(topic, SampleUtil.randomAlphaNumeric(100));
	}
	

}
