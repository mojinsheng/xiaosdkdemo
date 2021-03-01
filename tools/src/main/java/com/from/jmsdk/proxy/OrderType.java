package com.from.jmsdk.proxy;


import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface OrderType {

    String UNKNOWN = "unknown";

    String ALIPAY = "alipay";

    String WEPAY = "wechat_pay";

    String UNIPAY = "unionpay";

    String APPLE_PAY = "ap_pay";

    String GOOGLE_PAY = "google_pay";

    String WEB_PAY = "web_pay";


    String CARD = "mycard";

    String VISA = "visa";

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
    @StringDef({OrderType.UNKNOWN, OrderType.ALIPAY, OrderType.WEPAY, OrderType.UNIPAY, OrderType.APPLE_PAY, OrderType.GOOGLE_PAY, OrderType.CARD, OrderType.VISA, OrderType.WEB_PAY,""})
    @interface PayType{}
}
