package com.letscode.transaction.payload.transactionLimits;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.transaction.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionLimit {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("transactionType")
    private TransactionType transactionType;

    @JsonProperty("transactionValueLimit")
    private BigDecimal transactionValueLimit;

    @JsonProperty("creationDate")
    private LocalDateTime creationDate;

    @JsonProperty("updateDate")
    private LocalDateTime updateDate;
}
