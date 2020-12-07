package com.itau.desafio.units_tests;

import com.itau.desafio.model.TimeModel;
import com.itau.desafio.model.TransactionModel;
import com.itau.desafio.service.TimeService;
import com.itau.desafio.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class UnitsTests extends AbstractTest{
    @Override

    @Before

    public void setUp() {
        super.setUp();
    }

    // Teste para verificar a requisição da lista de transações
    @Test
    public void getTransactionListTest() throws Exception {
        String uri = "/transacao";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    // Teste para verificar a requisição de criação de uma transação
    @Test
    public void createTransactionTest() throws Exception {
        TransactionService transactionService = new TransactionService();
        TransactionModel transacao = new TransactionModel();

        String uri = "/transacao";

        transacao.setValor(55.0f);
        transacao.setDataHora(OffsetDateTime.parse("2020-12-01T15:01:00-03:00"));
        transactionService.addTransaction(transacao);

        String inputJson = super.mapToJson(transacao);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        //assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

    // Teste para verificar a requisição remoção das transações
    @Test
    public void deleteTransactionTest() throws Exception {
        String uri = "/transacao";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

    // Teste para verificar a validação da regra de negócio (Igual ou menor a zero)
    @Test
    public void isTransactionUnderZeroTest() throws Exception {
        TransactionService transactionService = new TransactionService();
        TransactionModel transactionModel = new TransactionModel();

        // Coloque qualquer valor negativo ou zero que retornara erro
        transactionModel.setValor(1.0f);
        transactionModel.setDataHora(OffsetDateTime.parse("2020-12-01T15:01:00-03:00"));

        boolean expResult = false;
        boolean result = transactionService.isTransactionUnderZero(transactionModel);
        assertEquals(expResult, result);
    }

    @Test
    public void isTransactionInFutureTest() throws Exception {
        TransactionService transactionService = new TransactionService();
        TransactionModel transactionModel = new TransactionModel();

        transactionModel.setValor(1.0f);
        // Coloque qualquer data ou horario acima do atual que retornara erro
        transactionModel.setDataHora(OffsetDateTime.parse("2020-12-05T15:01:00-03:00"));

        boolean expResult = false;
        boolean result = transactionService.isTransactionInFuture(transactionModel);
        assertEquals(expResult, result);
    }


    // Teste para verificar a requisição da lista de transações
    @Test
    public void getTimeTest() throws Exception {
        String uri = "/tempo";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    // Teste para verificar a requisição de criação de uma transação
    @Test
    public void createTimeTest() throws Exception {
        TimeService timeService = new TimeService();
        TimeModel tempo = new TimeModel();

        String uri = "/tempo";

        tempo.setSeconds(120);
        timeService.addSeconds(tempo);

        String inputJson = super.mapToJson(tempo);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
        System.out.println(content);
    }

    // Teste para verificar a validação da regra de negócio (Igual ou menor a zero)
    @Test
    public void isTimeUnderZeroTest() throws Exception {
        TimeService timeService = new TimeService();
        TimeModel timeModel = new TimeModel();

        // Coloque qualquer valor negativo ou zero que retornara erro
        timeModel.setSeconds(120);

        boolean expResult = false;
        boolean result = timeService.isTransactionUnderZero(timeModel);
        assertEquals(expResult, result);
    }

}
