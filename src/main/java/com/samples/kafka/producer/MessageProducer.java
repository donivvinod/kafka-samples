package com.samples.kafka.producer;

import java.util.concurrent.ExecutionException;

public interface MessageProducer {

	public void sendARandomMessage() throws InterruptedException, ExecutionException;
}
