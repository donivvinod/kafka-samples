package com.samples.kafka.consumer;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface MessageReciever {
	
	public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition);

}
