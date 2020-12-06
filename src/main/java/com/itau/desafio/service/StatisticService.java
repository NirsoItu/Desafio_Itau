package com.itau.desafio.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio.model.StatisticModel;
import com.itau.desafio.model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StatisticService extends TimeService{

    // Construtor de logs
    private static Logger logger = LoggerFactory.getLogger(StatisticService.class);

    public StatisticModel create(List<TransactionModel> transactions) throws JsonProcessingException {

        // Construtor de tempo
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/tempo";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Mapeando objeto tempo, pegando o valor e convertendo para string
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String tempo = root.get(0).toString();

        // Limpando a String
        String dadoLimpo = tempo.replace("{","")
                .replace("}","")
                .replaceAll("\"seconds\":","");

        // Convertendo para Integer
        Integer tempoConvertido = Integer.parseInt(dadoLimpo);

        // *** Tipo de Data padrão utilizada para teste 2020-12-06T15:01:00-03:00 ****

        // Retorna a data e horario do momento no padrão ISO 8601
        OffsetDateTime hoje = OffsetDateTime.now();

        // Instacia a entidade de estatistica
        StatisticModel statistics = new StatisticModel();

        // Retorna a data e horario do momento menos a quantidade de segundos,
        // por padrão o objeto inicia com 60 na classe TimeService
        OffsetDateTime hojeMenosUm = hoje.minusSeconds(tempoConvertido);

        // Criando as estatísticas
        logger.info("Criando as estatísticas");

        // Retorna a quantidade de transações nos últimos 60 segundos
        statistics.setCount(transactions.stream()
                .filter(data -> data.getDataHora().isAfter(hojeMenosUm))
                .filter(data -> data.getDataHora().isBefore(hoje)).count());

        // Retorna a média dos valores das transações nos últimos 60 segundos
        statistics.setAvg(BigDecimal.valueOf(transactions.stream()
                .filter(data -> data.getDataHora().isAfter(hojeMenosUm))
                .filter(data -> data.getDataHora().isBefore(hoje))
                .mapToDouble(t -> t.getValor().doubleValue()).average().orElse(0.0))
                .setScale(2, RoundingMode.HALF_UP));

        // Retorna o valor mínimo das transações nos últimos 60 segundos
        statistics.setMin(BigDecimal.valueOf(transactions.stream()
                .filter(data -> data.getDataHora().isAfter(hojeMenosUm))
                .filter(data -> data.getDataHora().isBefore(hoje))
                .mapToDouble(t -> t.getValor().doubleValue()).min().orElse(0.0))
                .setScale(2, RoundingMode.HALF_UP));

        // Retorna o valor máximo das transações nos últimos 60 segundos
        statistics.setMax(BigDecimal.valueOf(transactions.stream()
                .filter(data -> data.getDataHora().isAfter(hojeMenosUm))
                .filter(data -> data.getDataHora().isBefore(hoje))
                .mapToDouble(t -> t.getValor().doubleValue()).max().orElse(0.0))
                .setScale(2, RoundingMode.HALF_UP));

        // Retorna a soma das transações nos últimos 60 segundos
        statistics.setSum(BigDecimal.valueOf(transactions.stream()
                .filter(data -> data.getDataHora().isAfter(hojeMenosUm))
                .filter(data -> data.getDataHora().isBefore(hoje))
                .mapToDouble(t -> t.getValor().doubleValue()).sum())
                .setScale(2, RoundingMode.HALF_UP));

        logger.info("Lista das estatísticas: "+statistics);

        return statistics;
    }
}
