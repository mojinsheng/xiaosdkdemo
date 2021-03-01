package com.from.jmsdk.proxy;

import android.text.TextUtils;


import com.from.jmsdk.annotation.JsonResponse;
import com.from.jmsdk.tools.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO DOC
public abstract class BaseResponse {

    protected void initSelfByString(String data){
        try{
            JSONObject jsonData = new JSONObject(data);
            initSelfByJson(jsonData);
        }catch (JSONException e){
            Logger.warning(Logger.GLOBAL_TAG, "Passed String can not convert to Json in class: %s", this.getClass().getName(), e);
        }
    }

    @SuppressWarnings("ConstantConditions")
    protected void initSelfByJson(JSONObject json){
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
        fields.addAll(Arrays.asList(this.getClass().getFields()));
        fields.addAll(Arrays.asList(this.getClass().getInterfaces().getClass().getDeclaredFields()));

        for (Field field : fields) {
            JsonResponse response = field.getAnnotation(JsonResponse.class);
            if(response == null)
                continue;
            String[] nests = response.value();
            if(nests == null || nests.length == 0)
                continue;
            JSONObject nakJson = json;
            int i = 0;
            for(; i < nests.length - 1; i++){
                if(nakJson.has(nests[i])){
                    if(nakJson != null) {
                        nakJson = nakJson.optJSONObject(nests[i]);
                    }else{
                        break;
                    }
                }else{
                    Logger.warning(Logger.GLOBAL_TAG, "Can not parse field: %s under json name: %s in class: %s",
                            field.getName(), nests[i], this.getClass().getName());
                    break;
                }
            }
            if(i != nests.length - 1 || nakJson == null)
                continue;
            String key = nests[i];
            if (!nakJson.has(key)) {
                String[] replaces = response.replacement();
                if(replaces.length > 0 && !replaces[0].isEmpty()) {
                    for (String replace: replaces) {
                        if(nakJson.has(replace)) {
                            key = replace;
                            break;
                        }
                    }
                }
            }
            if (!nakJson.has(key)) continue; //if key still not exist after search replacement, skip
            String type = field.getGenericType().toString();
            field.setAccessible(true);
            try {
                if (type.equals("class java.lang.String")) {
                    String value = nakJson.getString(key);
                    if (!TextUtils.isEmpty(value) && !value.equals("null")) field.set(this, value);
                }else if(type.equals("int") || type.equals("class java.lang.Integer")){
                    int value = nakJson.getInt(key);
                    field.set(this, value);
                }else{
                    Logger.warning(Logger.GLOBAL_TAG, "Do not support this type of JsonResponse");
                }
            }catch (JSONException e){
                Logger.warning(Logger.GLOBAL_TAG, "Exception happen when parse field: %s under json name: %s in class: %s",
                        field.getName(), key, this.getClass().getName(), e);
            }catch (IllegalAccessException e){
                Logger.warning(Logger.GLOBAL_TAG, "Exception happen when set filed %s", field.getName(), e);
            }
        }
    }
}
