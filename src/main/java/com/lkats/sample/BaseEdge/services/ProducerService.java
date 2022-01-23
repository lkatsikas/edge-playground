package com.lkats.sample.BaseEdge.services;

import com.lkats.sample.BaseEdge.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private static final String EXCHANGE_NAME = "vehicle_exchange";

    private final RabbitTemplate rabbitTemplate;

    private static String[] routeKeys = new String[]{
            "nissan.cars.japan",
            "nissan.cars",
            "toyota.cars.japan.manufactured",
            "japan.toyota.cars",
            "import.nissan.cars.from.japan",
            "toyota.cars.manufatured",
            "no.latest.cars.toyota"
    };
    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce() {
        for (String routingKey : routeKeys) {
            logger.info(" sending the message with routing key {}", routingKey);

            Car car = new Car(routingKey);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, car);
        }
    }
}
