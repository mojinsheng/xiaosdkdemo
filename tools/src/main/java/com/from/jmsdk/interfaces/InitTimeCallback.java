package com.from.jmsdk.interfaces;

/**
 * 初始化获取服务器时间的回调
 */
public interface InitTimeCallback {

    void onSuccess();

    void onFailure(int code, String message);
}
