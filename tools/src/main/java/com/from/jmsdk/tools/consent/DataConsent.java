package com.from.jmsdk.tools.consent;


import android.support.annotation.RestrictTo;

/**
 * {@link DataConsent} storage all the json name used in FSDK
 * Please always use Strings provide by this class to parse Json
 *
 * The Naming rule for static variables in this class is:
 * FEATURE_NAME_WHATEVER_JSON_FirstVariable_SecondVariable_.....
 */
public abstract class DataConsent {

    public final static String USER_MAP_TOKEN = "token";
    public final static String USER_MAP_SESSION_ID = "sessionId";
    public final static String USER_MAP_UID = "uid";


}
