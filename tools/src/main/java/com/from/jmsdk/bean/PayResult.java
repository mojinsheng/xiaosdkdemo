package com.from.jmsdk.bean;



import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.from.jmsdk.proxy.OrderType;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class PayResult {

    public String transparent;

    public String orderType;

    public String jmOrderId;

    public PayResult(String transparent, String jmOrderId, @OrderType.PayType String orderType){
        this.transparent = transparent;
        this.jmOrderId = jmOrderId;
        this.orderType = orderType;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Transparent: %s", transparent);
    }
}
