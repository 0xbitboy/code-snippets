package com.github.liaojiacan.mq.http;

import com.github.liaojiacan.mq.kafka.KafkaUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiacan
 * @date 2019/8/20
 * @desc
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaUsageController {

    @Autowired
    private KafkaUsage kafkaUsage;

    @GetMapping(value = "/send")
    public ResponseEntity<String> sendMessage(String message){
        kafkaUsage.sendMessage(message);
        return ResponseEntity.ok("success");
    }
}
