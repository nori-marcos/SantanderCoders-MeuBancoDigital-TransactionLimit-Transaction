package com.letscode.transaction.payload;

import com.letscode.transaction.model.Transaction;
import com.letscode.transaction.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransactionResponse {
    private Integer id;
    private BigDecimal transactionValue;
    private TransactionType transactionType;
    private Integer accountNumber;
    private Integer agency;
    private LocalDateTime creationDate;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.transactionValue = transaction.getTransactionValue();
        this.transactionType = transaction.getTransactionType();
        this.accountNumber = transaction.getAccountNumber();
        this.agency = transaction.getAgency();
        this.creationDate = transaction.getCreationDate();
    }

    public static List<TransactionResponse> toResponse (List<Transaction> transactions){
        return transactions.stream().map(TransactionResponse::new).collect(Collectors.toList());
    }
}
