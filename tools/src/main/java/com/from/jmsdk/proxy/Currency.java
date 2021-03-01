package com.from.jmsdk.proxy;


import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enum like interface, do not place any method here.
 * Avoid enum since enum cost more place and compile & running time in Android for just a consent
 */
public interface Currency {
    int CNY = 1; //人民币
    int HKD = 2; //港币
    int USD = 3; //美元
    int VND = 4; //越南盾
    int NTW = 5; //新台币
    int UNSET = 999; //未设置，视情况处理

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @IntDef({Currency.CNY, Currency.HKD, Currency.USD, Currency.VND, Currency.NTW, Currency.UNSET})
    @interface Currencies{}
}
