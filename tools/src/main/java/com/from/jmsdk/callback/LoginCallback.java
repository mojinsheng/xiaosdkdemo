package com.from.jmsdk.callback;

import com.from.jmsdk.bean.UserInfo;
import com.from.jmsdk.proxy.ErrorMessage;

//TODO DOC
//TODO OP2 此类可能无法被外部引用，而此类需要被外部引用
// JM SDK Login callback, export to CP to get callback
public interface LoginCallback {

    void onSuccess(UserInfo userInfo);

    void onFailure(ErrorMessage message);

    void onCancel();

}
