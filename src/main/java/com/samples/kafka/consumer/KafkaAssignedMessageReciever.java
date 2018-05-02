package com.samples.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaAssignedMessageReciever implements MessageReciever {

	@Override
	@KafkaListener(
			  topicPartitions = @TopicPartition(topic = "${app.topic.sample}",
			  partitionOffsets = {
			    @PartitionOffset(partition = "0", initialOffset = "0"), 
			}))
	public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		System.out.println("received assigned message='" + message + " from partition " + partition);
	}

}
