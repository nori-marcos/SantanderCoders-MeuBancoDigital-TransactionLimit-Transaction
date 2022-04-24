package com.letscode.transaction.transactionLimits;

import com.letscode.transaction.payload.transactionLimits.TransactionLimit;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTransactionLimit {

    public TransactionLimit execute(String transactionType) {
        String url = "http://localhost:8080/transactionLimit/{transactionType}";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<TransactionLimit> transactionLimitResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, TransactionLimit.class, transactionType);

        if (transactionLimitResponseEntity.getStatusCode().isError()) {
            return null;
        }

        return transactionLimitResponseEntity.getBody();
    }
}
