package com.itau.desafio.controller;

import com.itau.desafio.model.StatisticModel;
import com.itau.desafio.model.TransactionModel;
import com.itau.desafio.service.StatisticService;
import com.itau.desafio.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    private static Logger logger = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatisticService statisticsService;

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<StatisticModel> getStatistics() {

        List<TransactionModel> transactions = transactionService.getAllTransactionsList();
        logger.info("Chamando o método para listar todas as transações...");
        StatisticModel statistics = statisticsService.create(transactions);
        return ResponseEntity.ok(statistics);
    }
}
