package com.sns.exception;

import com.sns.model.MyError;

/**
 * Created by ElNino on 15/9/8.
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private MyError error ;

    public MyException(String message){
        this(-9,message);
    }

    public MyException(int code ,String message){
        error = new MyError(code,message);
    }

    public MyError getError(){
        return error;
    }
}
