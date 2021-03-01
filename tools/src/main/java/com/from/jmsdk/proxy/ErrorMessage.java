package com.from.jmsdk.proxy;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;


//TODO DOC
//TODO OP2 此类可能无法被外部引用，而此类需要被外部引用
public class ErrorMessage {

    public int code;

    public String message;

    public Exception exception;

    public String data;


    public ErrorMessage(){
        //Do nothing
    }

    public ErrorMessage(int code, @Nullable String message){
        this.code = code;
        this.message = message;
    }

    public ErrorMessage(int code, @Nullable String message, @Nullable Exception e){
        this.code = code;
        this.message = message;
        this.exception = e;
    }

    public void storeError(int code, int storeCode){
        storeError(code, storeCode, "");
    }

    public void storeError(int code, int storeCode, @Nullable String message){
        storeError(code, storeCode, message, null);
    }

    public void storeError(int code, int storeCode, @Nullable String message, @Nullable Exception e){
        this.code = code;
        this.exception = e;
        this.message = String.format(Locale.US, "store error code: %d, store error message: %s", storeCode, message);
    }

    @Override @NonNull
    public String toString() {
        if(exception == null) {
            return String.format(Locale.US, "error code: %d, error message: %s, data: %s", code, message, data);
        }else{
            return String.format(Locale.US, "error code: %d, error message: %s, data: %s, exception: %s", code, message, data, exception.toString());
        }
    }
}
