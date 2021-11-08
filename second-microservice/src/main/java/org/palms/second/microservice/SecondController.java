package org.palms.second.microservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.palms.second.microservice.dto.UserInfo;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second")
@EnableKafka
public class SecondController {

    @GetMapping
    public String secondService() {
        return "It's second micro-service";
    }

    @KafkaListener(topics = "msgFromFirst", containerFactory = "stringKafkaListenerFactory")
    public void listenMessage(String message) {
        System.out.println("Message is: " + message);
    }

    @KafkaListener(topics = "userInfo", containerFactory = "jsonKafkaListenerFactory")
    private void getUserInfo(@Payload ConsumerRecord<Long, UserInfo> record) {
        UserInfo userInfo = record.value();
        System.out.println("Name: " + userInfo.getName());
        System.out.println("Message: " + userInfo.getMessage());
    }
}
