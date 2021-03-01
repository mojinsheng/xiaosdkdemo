package com.from.jmsdk.proxy;

import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.from.jmsdk.annotation.RequestParam;
import com.from.jmsdk.tools.MD5decode;
import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.consent.NetworkConsent;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class ParamTools {

    protected final HashMap<String, String> generateMapByFields(@Nullable HashMap<String, String> transparent) {
        HashMap<String, String> map = new HashMap<>();
        if (transparent != null && !transparent.isEmpty()) {
            map.putAll(transparent);
        }
        // read all fields to List, include super classes public fields and interface
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
        fields.addAll(Arrays.asList(this.getClass().getFields()));
        fields.addAll(Arrays.asList(this.getClass().getInterfaces().getClass().getDeclaredFields()));

        //put fields name to HashMap -> key, and fields value to HashMap -> value
        for (Field field : fields) {
            RequestParam isRequest = field.getAnnotation(RequestParam.class);
            if(isRequest != null) {
                String type = field.getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    field.setAccessible(true);
                    try {
                        Object o = field.get(this);
                        if (o != null) map.put(field.getName(), o.toString());
                    } catch (IllegalAccessException e) {
                        Logger.warning(Logger.GLOBAL_TAG, "Generate HashMap from %s fields failed with exception", this.getClass().toString(), e);
                    }
                }
            }
        }
        Logger.debug(Logger.GLOBAL_TAG, "Request Params size exclude sign: %d", map.size());
        return map;
    }

    protected final String toH5JsResult(HashMap<String, String> map){
        ArrayList<String> sortedKeys = sortMap(map);
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        result.append("({");
        for(String mapKey: sortedKeys){
            String value = map.get(mapKey);
            if(!TextUtils.isEmpty(value)){
                sb.append(value);
                String item = mapKey + ":" + "'" + value + "',";
                result.append(item);
            }else{
                Logger.warning(Logger.GLOBAL_TAG, "Key %s will not use to generate H5 sign since it's empty", mapKey);
            }
        }
        return result.toString().substring(0, result.length() - 1) + "})";
    }



    protected final String toPostParam(HashMap<String, String> map){
        ArrayList<String> sortedKeys = sortMap(map);
        StringBuilder sb = new StringBuilder();
        JSONObject json = new JSONObject();
        for(String key: sortedKeys){
            try {
                String value = map.get(key);
                if(!TextUtils.isEmpty(value)) {
                    json.put(key, map.get(key));
                    sb.append(linkParam(key, map.get(key)));
                }else{
                    Logger.debug(Logger.GLOBAL_TAG, "Key %s will not append in request param since the value is null", key);
                }
            }catch (JSONException e){
                Logger.warning(Logger.GLOBAL_TAG, "Put params to json exception", e);
            }
        }
        //remove the '&' in last characters
        if(sb.length() > 0 && sb.lastIndexOf(NetworkConsent.FLAG_PARAM_LINK) == sb.length() -1){
            sb.deleteCharAt(sb.length() - 1);
        }
       Logger.debug(Logger.GLOBAL_TAG, "Request from: %s, \nParams: %s", this.getClass().getName(), sb.toString());
//        sb.append(NetworkConsent.PARAM_KEY);
//        String sign = MD5decode.MD5(sb.toString());
//        try {
//            json.put(NetworkConsent.PARAM_SIGN, sign);
//        }catch (JSONException e){
//            Logger.error(Logger.GLOBAL_TAG, "Put sign to request params has json exception", e);
//        }
        return sb.toString();
    }

    protected final String tosPliceParamUrl(HashMap<String, String> map){
        ArrayList<String> sortedKeys = sortMap(map);
        StringBuilder sb = new StringBuilder();
        JSONObject json = new JSONObject();
        for(String key: sortedKeys){
            try {
                String value = map.get(key);
                if(!TextUtils.isEmpty(value)) {
                    json.put(key, map.get(key));
                    sb.append(linkParam(key, map.get(key)));
                }else{
                    Logger.debug(Logger.GLOBAL_TAG, "Key %s will not append in request param since the value is null", key);
                }
            }catch (JSONException e){
                Logger.warning(Logger.GLOBAL_TAG, "Put params to json exception", e);
            }
        }
        //remove the '&' in last characters
        if(sb.length() > 0 && sb.lastIndexOf(NetworkConsent.FLAG_PARAM_LINK) == sb.length() -1){
            sb.deleteCharAt(sb.length() - 1);
        }
        Logger.debug(Logger.GLOBAL_TAG, "Request from: %s, \nParams: %s", this.getClass().getName(), sb.toString());
        //sb.append(NetworkConsent.PARAM_KEY);
        String sign = MD5decode.MD5(sb.toString());
        try {
            json.put(NetworkConsent.PARAM_SIGN, sign);
            sb.append(NetworkConsent.PARAM_SIGN+"="+ sign);
        }catch (JSONException e){
            Logger.error(Logger.GLOBAL_TAG, "Put sign to request params has json exception", e);
        }
        Logger.info(Logger.GLOBAL_TAG, "Sign: %s", sign);
        return sb.toString();
    }

    private ArrayList<String> sortMap(HashMap<String, String> map){
        ArrayList<String> sortedList = new ArrayList<>(map.keySet());
        Collections.sort(sortedList);
        return sortedList;
    }

    private String linkParam(String variable, String value){
        if(!TextUtils.isEmpty(value)){
            return variable + "=" + value + NetworkConsent.FLAG_PARAM_LINK;
        }else{
            return "";
        }
    }

}
