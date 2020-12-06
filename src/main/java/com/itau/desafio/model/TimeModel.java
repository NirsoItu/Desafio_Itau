package com.itau.desafio.model;

public class TimeModel {

    private Integer seconds;

    public TimeModel(Integer seconds) {
        this.seconds = seconds;
    }

    public TimeModel() {
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
