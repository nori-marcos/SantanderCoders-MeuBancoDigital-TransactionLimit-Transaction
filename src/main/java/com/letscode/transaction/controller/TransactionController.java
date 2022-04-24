package com.letscode.transaction.controller;

import com.letscode.transaction.payload.TransactionRequest;
import com.letscode.transaction.payload.TransactionResponse;
import com.letscode.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(
            @RequestBody TransactionRequest transactionRequest
    ) {
        return transactionService.create(transactionRequest);
    }

    @GetMapping
    public List<TransactionResponse> getAll() {
        return transactionService.getAll();
    }

}
