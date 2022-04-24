package com.letscode.transaction.payload.JMS.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.transaction.payload.JMS.TransactionMessage;
import com.letscode.transaction.payload.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

@Slf4j
public class TransactionSerializer implements Serializer<TransactionMessage> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TransactionMessage transactionMessage) {
        try {
            if (Objects.nonNull(transactionMessage)) {

                return mapper.writeValueAsBytes(transactionMessage);

            }
        } catch (JsonProcessingException e) {
            log.error("Json processing error transaction serializer", e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
