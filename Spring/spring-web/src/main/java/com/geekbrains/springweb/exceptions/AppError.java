package com.geekbrains.springweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
@AllArgsConstructor
@Data

public class AppError {
    private int statusCode;
    private String message;
//
//    public int getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(int statusCode) {
//        this.statusCode = statusCode;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public AppError() {
//    }
//
//    public AppError(int statusCode, String message) {
//        this.statusCode = statusCode;
//        this.message = message;
//    }
}
