package com.example.springbootrelationshipmanytomanythymeleaf.model;

import java.util.Date;

public class ResponseDataDto {
    private Date date;
    private String status;
    private String code;

    public ResponseDataDto(){}

    public ResponseDataDto(Date date, String status, String code) {
        this.date = date;
        this.status = status;
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
