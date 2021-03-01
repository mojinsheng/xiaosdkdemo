package com.from.jmsdk.proxy;

import android.app.Activity;
import android.os.Build;


import com.from.jmsdk.annotation.RequestParam;
import com.from.jmsdk.tools.DeviceUtils;
import com.from.jmsdk.tools.IntenetUtil;
import com.from.jmsdk.tools.ScreenUtils;
import com.from.jmsdk.tools.Util;
import com.from.jmsdk.tools.consent.ReYunConstants;

//TODO DOC
// All the variable in this class should be public final
public abstract class BaseParam extends ParamTools {

    //Hardware base info
    @RequestParam
    public final String deviceid;
    @RequestParam
    public final String mac;

    @RequestParam
    public final String androidid;

    @RequestParam
    public final String ver_sdk;

    @RequestParam
    public final String phoneType;

    //Key info
    @RequestParam
    public final String imei;


    @RequestParam
    public final String phone_type;

    @RequestParam
    public final String dpi;

    @RequestParam
    public final String system;

    @RequestParam
    public final String flag;

    @RequestParam
    public final String wifi_type;
    @RequestParam
    public final String ipc_service;

    @RequestParam
    public final String pay_time;

    @RequestParam
    public final String app_key;

    public BaseParam(Activity activity){
        this.app_key = ReYunConstants.APPKEY;

        this.ver_sdk = Util.getVersionName(activity);
        this.deviceid = DeviceUtils.getDeviceId(activity);
        this.mac = DeviceUtils.getMac(activity);
        this.phoneType = Build.BRAND;
        this.androidid = DeviceUtils.getAndroidId(activity);
        this.imei = DeviceUtils.getIMEI(activity);
        this.phone_type = android.os.Build.BRAND + "_" + android.os.Build.MODEL;
        this.dpi = ScreenUtils.getWidthPixels(activity)+"*"+ScreenUtils.getHeightPixels(activity);
        this.system = ReYunConstants.OS_ANDROID+"_" + android.os.Build.VERSION.RELEASE;
        this.flag = ReYunConstants.FLAY;
        this.pay_time = System.currentTimeMillis()+"";
        int networkType=IntenetUtil.getNetworkState(activity);
        String wifi_type="";
        if(networkType==1||networkType==7){
            wifi_type = "1";

        }else{
            wifi_type = "2";

        }
        this.wifi_type = wifi_type;
        this.ipc_service = ReYunConstants.MOIDLE_PAY;

    }

    public abstract String toPostParam();

}
