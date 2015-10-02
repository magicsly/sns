package com.sns.controller;

import com.sns.exception.MyException;
import com.sns.model.MyError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ElNino on 15/9/22.
 */
public class baseController {
    @ExceptionHandler
    public @ResponseBody MyError exp(HttpServletRequest request, Exception ex) {
        if(ex instanceof MyException){
            MyException myException = (MyException) ex;
            return myException.getError();
        }
        return new MyError(-9,ex.getMessage());
    }
}
