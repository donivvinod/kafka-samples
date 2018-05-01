package com.samples.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.samples.kafka.producer.MessageProducer;

@SpringBootApplication
public class KafkaSamplesApplication implements CommandLineRunner {

	@Autowired
    private MessageProducer producer;

	
	public static void main(String[] args) {
		SpringApplication.run(KafkaSamplesApplication.class, args);
	}
	

    @Override
    public void run(String... strings) throws Exception {
    	producer.sendARandomMessage();
    }
}
