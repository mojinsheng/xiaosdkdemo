package com.from.jmsdk.annotation;

import android.support.annotation.IntDef;
import android.util.Log;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, Log.ERROR, Log.ASSERT})
@Retention(RetentionPolicy.CLASS)
public @interface LogLevel {
}
