package com.itau.desafio.controller;

import com.itau.desafio.model.TransactionModel;
import com.itau.desafio.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "API Rest Transações e Estatísticas")
@CrossOrigin(origins = "*")
public class TransactionController {

    // Construtor de logs
    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    // Endpoint para buscar todas as transações
    @RequestMapping(method = RequestMethod.GET, value = "/transacao")
    @ApiOperation(value = "Retorna uma lista de transações")
    public List<TransactionModel> allTransactions() {
        logger.info("Chamando o método para listar todas as transações...");
        transactionService.getAllTransactionsList();
        return transactionService.getAllTransactionsList();
    }

    // Endpoint para incluir uma transação
    @RequestMapping(method = RequestMethod.POST, value = "/transacao")
    @ApiOperation(value = "Cria uma transação")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void post(@RequestBody TransactionModel transactionModel) throws Exception {
        logger.info("Chamando o método para criar a transação...");
        transactionService.addTransaction(transactionModel);
    }

    // Endpoint para deletar todas as transações
    @RequestMapping(method = RequestMethod.DELETE, value = "/transacao")
    @ApiOperation(value = "Deleta a lista de transações")
    public void deleteTransaction(TransactionModel transactionModel) {
        logger.info("Chamando o método para apagar todas as transações...");
        transactionService.deleteAllTransactions(transactionModel);
    }

}
