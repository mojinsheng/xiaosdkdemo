package com.from.jmsdk.annotation;


import android.support.annotation.RestrictTo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public @interface JsonResponse {
    String[] value(); // Json data position
    String[] replacement() default "";
}
