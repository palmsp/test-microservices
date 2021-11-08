package org.palms.first.microservice;

import org.palms.first.microservice.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/first")
public class FirstController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, UserInfo> jsonMsgKafkaTemplate;

    @GetMapping
    public String firstService() {
        return "It's first micro-service";
    }

    @GetMapping("/more")
    public String firstServiceMore() {
        return "It's one more from first micro-service";
    }

    @PostMapping
    public void sendMessage(String msgId, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("msgFromFirst", msgId, message);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    @PostMapping("/userInfo")
    public void sendUserInfo(@RequestBody UserInfo userInfo) {
        ListenableFuture<SendResult<Long, UserInfo>> future = jsonMsgKafkaTemplate.send("userInfo",
                userInfo.getAge(), userInfo);
        future.addCallback(System.out::println, System.err::println);
        jsonMsgKafkaTemplate.flush();
    }
}
