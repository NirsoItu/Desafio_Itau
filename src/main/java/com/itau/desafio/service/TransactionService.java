package com.itau.desafio.service;

import com.itau.desafio.model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    // Construtor de logs
    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private List<TransactionModel> transactionModelList = new ArrayList<>(Arrays.asList());

    // Método para listar todas as transações
    public List<TransactionModel> getAllTransactionsList() {
        logger.info("Lista com todas as transações - Status: 200: "+transactionModelList);
        return transactionModelList;
    }

    // Método para criar uma transação
    public TransactionModel addTransaction(TransactionModel transactionModel) {
        logger.info("Verificando as regras de negócio...");
        if (isTransactionInFuture(transactionModel) == false &&
                isTransactionUnderZero(transactionModel) == false) {
            logger.info("Criando a transação...");
            transactionModelList.add(transactionModel);
            logger.info("Transação criada com sucesso - Status: 201");
        }
        return transactionModel;
    }

    // Método para deletar todas as transações
    public void deleteAllTransactions(TransactionModel transactionModel){
        transactionModelList.removeAll(transactionModelList);
        logger.info("Todas as transações foram apagadas com sucesso - Status: 200");
    }

    // Método para verificar uma das regras do negócio: Se transação será criada no futuro
    public boolean isTransactionInFuture (TransactionModel transactionModel) {
        if(transactionModel.getDataHora().isAfter(OffsetDateTime.now())) {
            logger.info("A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram\n" +
                    "atendidos - Error: 422");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }else{
            return false;
        }
    }

    // Método para verificar uma das regras do negócio: Se o valor da transação é igual ou abaixo de zero
    public boolean isTransactionUnderZero (TransactionModel transactionModel) throws ResponseStatusException {
        if (transactionModel.getValor() < 0) {
            logger.info("A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram\n" +
                    "atendidos - Error: 422");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return false;
        }
    }


}
