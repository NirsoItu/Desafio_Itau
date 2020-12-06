package com.itau.desafio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.desafio.model.StatisticModel;
import com.itau.desafio.model.TransactionModel;
import com.itau.desafio.service.StatisticService;
import com.itau.desafio.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "API Rest Transações e Estatísticas")
@CrossOrigin(origins = "*")
public class StatisticController {

    private static Logger logger = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatisticService statisticsService;

    @RequestMapping(method = RequestMethod.GET, value = "/estatistica")
    @ApiOperation(value = "Retorna uma lista de estatísticas")
    public ResponseEntity<StatisticModel> getStatistics() throws JsonProcessingException {
        logger.info("Chamando o método para listar todas as transações...");
        List<TransactionModel> transactions = transactionService.getAllTransactionsList();
        StatisticModel statistics = statisticsService.create(transactions);
        return ResponseEntity.ok(statistics);
    }
}
