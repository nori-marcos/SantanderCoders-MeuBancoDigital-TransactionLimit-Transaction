package com.letscode.transaction.service;


import com.letscode.transaction.model.Transaction;
import com.letscode.transaction.payload.TransactionRequest;
import com.letscode.transaction.payload.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create (TransactionRequest transactionRequest);
    List<TransactionResponse> getAll ();
}
