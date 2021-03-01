package com.from.jmsdk.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;


public abstract class SPUtils {
    private static final String SPFILENAME = "sp_jm_repair";

    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key,defValue);
    }
    /**
     * 保存可序列化对象
     *
     * @param key
     * @param value
     */
    public static void save(Context context,String key, Serializable value) {
        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(value);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = getSp(context).edit();
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error(Logger.TOOLS_TAG," sharedPreferences commit fial:", e);
        } finally {
            try {
                try {
                    if (baos != null) {
                        baos.close();
                    }
                } catch (IOException e) {
                    Logger.error(Logger.TOOLS_TAG," baos close_exception_is fial:", e);
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Logger.error(Logger.TOOLS_TAG, " out close_exception_is fial ", e);
            }
        }
    }

    /**
     * 取出可序列化对象
     *
     * @param key
     * @return
     */
    public static <T extends Serializable> T getSerializable(Context context,String key) {
        if (getSp(context).contains(key)) {
            String objectVal = getSp(context).getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
                Logger.error(Logger.TOOLS_TAG,"sp get streamCorruptedException fial:", e);
            } catch (IOException e) {
                e.printStackTrace();
                Logger.error(Logger.TOOLS_TAG,"sp get IOException fial:", e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Logger.error(Logger.TOOLS_TAG,"sp get classNotFoundException fial:", e);
            } finally {
                try {
                    try {
                        if (bais != null) {
                            bais.close();
                        }
                    } catch (IOException e) {
                        Logger.error(Logger.TOOLS_TAG,"get bais close_exception_is fial:", e);
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Logger.error(Logger.TOOLS_TAG,"get ois close_exception_is fial:", e);

                }
            }
        }
        return null;
    }

    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences sp = getSp(context);
        //添加保存数据
        sp.edit().putBoolean(key,value).commit();
    }

    public static String getString(Context context, String key){
        SharedPreferences sp = getSp(context);
        return sp.getString(key,null);
    }

    public static void putString(Context context, String key, String value){
        SharedPreferences sp = getSp(context);
        //添加保存数据
        sp.edit().putString(key,value).commit();
    }

    public static  void putInt(Context context, String key, int value){
        SharedPreferences sp = getSp(context);
        sp.edit().putInt(key,value).commit();
    }

    public static  void putLong(Context context, String key, long value){
        SharedPreferences sp = getSp(context);
        sp.edit().putLong(key,value).commit();
    }

    public static long getLong(Context context, String key){
        SharedPreferences sp = getSp(context);
        return sp.getLong(key,0);
    }

    public static int getInt(Context context, String key){
        SharedPreferences sp = getSp(context);
        return sp.getInt(key,0);
    }

    private static SharedPreferences getSp(Context context){
        return context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
    }
}
