package com.itau.desafio.model;

import java.time.OffsetDateTime;

public class TransactionModel {

    private Float valor;
    private OffsetDateTime dataHora;

    public TransactionModel() {

    }

    public TransactionModel(Float valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

}
