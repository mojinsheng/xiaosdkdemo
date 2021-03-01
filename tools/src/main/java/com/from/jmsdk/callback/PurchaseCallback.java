package com.from.jmsdk.callback;



public interface PurchaseCallback {

    void onSuccess();

    void onFailure();

    void onCancel();
}
