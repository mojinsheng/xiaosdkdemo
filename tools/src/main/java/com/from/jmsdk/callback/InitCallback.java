package com.from.jmsdk.callback;

import com.from.jmsdk.proxy.ErrorMessage;

public interface InitCallback {

    void initSuccess();

    void initFailure(ErrorMessage message);
}
