package com.letscode.transaction.payload.JMS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionMessage {
    private String message;
}
