package com.from.jmsdk.callback;


import com.from.jmsdk.bean.PayInfo;
import com.from.jmsdk.bean.PayResult;
import com.from.jmsdk.proxy.ErrorMessage;

public interface WTPurchaseCallback {


    void onSuccess(PayInfo payInfo);

    void onFailure(ErrorMessage message);

    void onCancel();

}
