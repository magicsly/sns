package com.sns.model;

/**
 * Created by ElNino on 15/9/8.
 */
public class MyError {
    private int code = 0;
    private String message = "";

    public MyError(){

    }

    public MyError(String message) {
        this.message = message;
    }

    public MyError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int state) {
        this.code = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
