package com.samples.kafka.producer;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.samples.kafka.utils.SampleUtil;

@Service
public class KafkaMessageProducer implements MessageProducer {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.sample}")
    private String topic;
    
	@Override
	public void sendARandomMessage() throws InterruptedException, ExecutionException {
		//Topic Name ==> topic  .. our topic has 2 partitions
		//Which Partition ==> 1
		//Time Stamp ==> Current time in millis
		//Key ==> Sample Message Key 
		//Value ==>Random String
		ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic,1,System.currentTimeMillis(), "Sample Message Key", SampleUtil.randomAlphaNumeric(10));
		while(!result.isDone()) {
			SendResult<String, String> sendResult = result.get();
			System.out.println(sendResult.getRecordMetadata().partition());
		}	
		
		//In this case, messages are assigned to multiple partitions and loose the order.
		//Dont expect messages to be recieved in sequence at the client as the topic has 2 partitions.
		int i = 0;
		while(i<100) {
			kafkaTemplate.send(topic,"Message " +i);
			i++;
		}
	}
	

}
