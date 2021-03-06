package com.form.xysdk.plafrom;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.form.xysdk.ui.activity.ContainerActivity;
import com.from.jmsdk.tools.SPUtils;

public class XYPlafrom {
    private static XYPlafrom xyPlafrom;

    private XYPlafrom(){

    }
    public static XYPlafrom getInstance(){
        if(xyPlafrom==null){
            return xyPlafrom=new XYPlafrom();
        }else {
            return xyPlafrom;
        }

    }

    /**
     * 登录接口
     * @param activity
     */
    public void wtLogin(Activity activity){


            Intent intent=new Intent(activity, ContainerActivity.class);
            activity.startActivity(intent);

    }
}
