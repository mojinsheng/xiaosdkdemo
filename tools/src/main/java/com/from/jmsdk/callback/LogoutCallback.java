package com.from.jmsdk.callback;

import com.from.jmsdk.proxy.ErrorMessage;

public interface LogoutCallback {

    void onSuccess();

    void onFailure(ErrorMessage message);
}
