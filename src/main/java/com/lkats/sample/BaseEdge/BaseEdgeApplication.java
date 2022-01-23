package com.lkats.sample.BaseEdge;

import com.lkats.sample.BaseEdge.services.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseEdgeApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(BaseEdgeApplication.class);

	@Autowired
	private ProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(BaseEdgeApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info(" producer application is up and running");
		producerService.produce();
	}
}
