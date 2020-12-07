package com.itau.desafio.service;

import com.itau.desafio.model.TimeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TimeService {

    // Construtor de logs
    private static Logger logger = LoggerFactory.getLogger(TimeService.class);

    private List<TimeModel> timeModelList = new ArrayList<>(Arrays.asList(new TimeModel(60)));


    public List<TimeModel> getSeconds() {
        return timeModelList;
    }

    // Método para criar um tempo de estatística
    public TimeModel addSeconds(TimeModel timeModel) {
        logger.info("Verificando se o valor é zero ou negativo...");
        if (isTransactionUnderZero(timeModel) == false) {
            logger.info("Criando a inclusão do tempo...");
            timeModelList.clear();
            timeModelList.add(timeModel);
            logger.info("O tempo foi incluido  com sucesso - Status: 201");
        }
        return timeModel;
    }

    // Método para verificar se o tempo é igual ou abaixo de zero
    public boolean isTransactionUnderZero (TimeModel timeModel) throws ResponseStatusException {
        if (timeModel.getSeconds() < 0) {
            logger.info("O tempo não pode ser menor a zero - Error: 422");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return false;
        }
    }
}
