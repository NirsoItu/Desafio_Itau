package com.itau.desafio.controller;

import com.itau.desafio.model.TransactionModel;
import com.itau.desafio.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TransactionController {

    // Construtor de logs
    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    // Endpoint para buscar todas as transações
    @RequestMapping("/transacao")
    public List<TransactionModel> allTransactions() {
        logger.info("Chamando o método para listar todas as transações...");
        transactionService.getAllTransactionsList();
        return transactionService.getAllTransactionsList();
    }

    // Endpoint para incluir uma transação
    @RequestMapping(value = "/transacao", method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody TransactionModel transactionModel) throws Exception {
        logger.info("Chamando o método para criar a transação...");
        transactionService.addTransaction(transactionModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("A transação foi aceita ");
    }

    // Endpoint para deletar todas as transações
    @RequestMapping(method = RequestMethod.DELETE, value = "/transacao")
    public ResponseEntity<?> deleteTransaction(TransactionModel transactionModel) {
        logger.info("Chamando o método para apagar todas as transações...");
        transactionService.deleteAllTransactions(transactionModel);
        return ResponseEntity.status(HttpStatus.OK).body("Todas as transações foram apagadas com sucesso ");
    }

}
