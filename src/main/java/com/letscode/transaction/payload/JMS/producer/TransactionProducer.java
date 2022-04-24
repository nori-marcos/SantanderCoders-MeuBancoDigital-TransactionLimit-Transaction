package com.letscode.transaction.payload.JMS.producer;

import com.letscode.transaction.payload.JMS.TransactionMessage;
import com.letscode.transaction.payload.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

    @Value("${kafka.topic.name}")
    private String topicName;
    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;

    public void send(TransactionMessage transactionMessage) {
        kafkaTemplate.send(topicName, transactionMessage);
    }

}
