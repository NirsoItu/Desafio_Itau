package com.itau.desafio.service;

import com.itau.desafio.model.TransactionModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    private List<TransactionModel> transactionModelList = new ArrayList<>(Arrays.asList());

    // Método para listar todas as transações
    public List<TransactionModel> getAllTransactionsList() {
        return transactionModelList;
    }

    // Método para criar uma transação
    public TransactionModel addTransaction(TransactionModel transactionModel) {
            transactionModelList.add(transactionModel);
        return transactionModel;
    }

    // Método para deletar todas as transações
    public void deleteAllTransactions(TransactionModel transactionModel){
        transactionModelList.removeAll(transactionModelList);
    }


}
