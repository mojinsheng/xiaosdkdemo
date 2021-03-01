package com.from.jmsdk.tools.consent;


/**
 * All Error code should define here and with prefix 20, basically there are three types of Error:
 * 1. Client Error {@link ErrorConsent#CLIENT_ERROR}:
 *      from 600 to 699, include all the client exception, or http implement error.
 *      A Client Error usually means code implement is not correct, and should not happen in release environment
 *      All Client Error should covered by client side when implement features.
 *      A good practices to throw client error is to let developer know it happens on which line
 * 2. Store Error {@link ErrorConsent#STORE_ERROR}:
 *      from 500 to 599, it's the error store give to us, usually we put store error code in
 *      {@link com.from.jmsdk.proxy.ErrorMessage#message} with format "store error code: %d， message: %s"
 *      code should be set as what we defined.
 *      You can call {@link com.from.jmsdk.proxy.ErrorMessage#storeError(int, int)} to construct store error
 * 3. Server Error:
 *      usually it's 1000 and above and without prefix, depends on how JM backend return.
 *      to deal with this kind of error, we set server error code to {@link com.from.jmsdk.proxy.ErrorMessage#code}
 *      and server data to {@link com.from.jmsdk.proxy.ErrorMessage#message}
 */
public abstract class ErrorConsent {

    //No Error
    public static final int SUCCESS = 200;
    public static final int CLIENT_ERROR_UNDEFINE = 20911;// This error happen if error code in UserInfo haven't init
    public static final int UN_CATCH_EXCEPTION = 20119;

    //Integration Error
    public static final int INIT_MISS = 20101;
    public static final int KEY_INVALIDATE = 20102;
    public static final int LOGIN_MISS = 20103;

    //Client Error
    public static final int CLIENT_ERROR = 20600;
    public static final int URL_BUILD_EXCEPTION = 20602;
    public static final int URL_CONNECTION_OPEN_EXCEPTION = 20610;
    public static final int URL_CONNECTION_CONNECT_EXCEPTION = 20611;
    public static final int RESPONSE_READ_EXCEPTION = 20612;
    public static final int RESPONSE_EMPTY = 20613;
    public static final int HTTP_METHOD_SET_EXCEPTION = 20615;
    public static final int EMPTY_DOMAIN = 20620;
    public static final int PARSE_JSON_EXCEPTION = 20631;
    public static final int PERMISSION_MISS = 20641;
    public static final int PASSED_ILLEGAL_PAY_INFO = 20666;
    public static final int NOT_IN_UI_THREAD = 20676;
    public static final int MULTI_CALLS = 20677;
    public static final int INIT_FAILURE = 20678;
    public static final int LOGOUT_FAILURE = 20679;


    //Store Error
    public static final int STORE_ERROR = 20500; //use this error if store didn't return a error code
    public static final int STORE_LOGIN_ERROR = 20510; //store gives success response but code is not success code
    public static final int STORE_LOGIN_EXCEPTION = 20511; // exception happen inside store
    public static final int STORE_LOGIN_FAILURE = 20512; //store gives failure response
    public static final int STORE_LOGIN_CANCEL = 20513;
    public static final int STORE_PAY_FAILURE = 20530;
    public static final int STORE_PAY_CANCEL = 20533;
    public static final int STORE_EXIT_CANCEL = 20563;

    //Server Error usually could get directly from JM service


}
