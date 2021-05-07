/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo.dataaccess;

/**
 *
 * @author Cash Carlson
 */
public class JobDataException extends Exception {
    
    public JobDataException() {
    }
    
    public JobDataException(String msg) {
        super(msg);
    }
 
    public JobDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
 
    public JobDataException(Throwable cause) {
        super(cause);
    }
 
    public JobDataException(String msg, Throwable cause
            , boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
    
}
