package com.itau.desafio.exceptions;

public class ErrorMessage {
    private String message;
    private Integer status;

    public ErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
