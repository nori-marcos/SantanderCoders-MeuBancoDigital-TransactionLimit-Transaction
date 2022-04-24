package com.letscode.transaction.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.letscode.transaction.model.Transaction;
import com.letscode.transaction.payload.JMS.TransactionMessage;
import com.letscode.transaction.payload.JMS.producer.TransactionProducer;
import com.letscode.transaction.payload.TransactionRequest;
import com.letscode.transaction.payload.TransactionResponse;
import com.letscode.transaction.payload.transactionLimits.TransactionLimit;
import com.letscode.transaction.repository.TransactionRepository;
import com.letscode.transaction.transactionLimits.GetTransactionLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    GetTransactionLimit getTransactionLimit;

    @Autowired
    TransactionProducer transactionProducer;

    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {

//        get the transaction limit to validate the creation of a new transaction
//        if the transaction value exceeds the limit, the method returns null
        TransactionLimit transactionLimit = getTransactionLimit.execute(transactionRequest.getTransactionType().toString());

        if (transactionRequest.getTransactionValue().intValue() <= transactionLimit.getTransactionValueLimit().intValue()) {
            Transaction transaction = new Transaction(transactionRequest);

            transaction = transactionRepository.save(transaction);

//            kafka: create a mesage asking to update the balance in bank account

            TransactionMessage transactionMessage = new TransactionMessage(transaction.getTransactionType() + " de " + transaction.getTransactionValue() + " na conta " + transaction.getAccountNumber() + " da agência "+ transaction.getAgency());

            transactionProducer.send(transactionMessage);

            return new TransactionResponse(transaction);
        }

        return null;
    }

    @Override
    public List<TransactionResponse> getAll() {

        List<Transaction> transactions = transactionRepository.findAll();

        return TransactionResponse.toResponse(transactions);
    }
}
