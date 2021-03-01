package com.from.jmsdk.tools;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;


public abstract class MD5decode {


    public static String MD5(@NonNull String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String MD5(@NonNull String s, String key) {
        StringBuffer sb = new StringBuffer();
        String mdMd5Key = sb.append(s).append(key).toString();
        return MD5(mdMd5Key);
    }

    public static String base64_encoding(@NonNull String s) {
        return Base64.encodeToString(s.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    public static String url_decode(@NonNull String s) {
        try {
            return URLDecoder.decode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            Logger.warning(Logger.GLOBAL_TAG, "URL decode exception");
            return "";
        }
    }

    @NonNull
    public static String unzip(@NonNull byte[] data) {
        ByteArrayOutputStream outputStream = null;
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data, 0, data.length);
            outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[data.length];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            byte[] output = outputStream.toByteArray();
            return new String(output, 0, output.length, StandardCharsets.UTF_8);
        } catch (DataFormatException e) {
            Logger.warning(Logger.GLOBAL_TAG, "Uncompress data Exception", e);
            return "";
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Logger.warning(Logger.GLOBAL_TAG, "Exception happen when trying to close ByteArrayOutputStream, this may cause a memory leak");
                }
            }
        }
    }

    /**
     * Merge two Json Object to one, the content for both Json object will be merged.
     * If passed json objects have same field, this method will return {@code null}
     *
     * @param json1 the json to be merged
     * @param json2 another json to be merged
     * @return return the merged Json Object or {@code null} if those two json have same data or
     * unexpected Exception happen
     */
    @Nullable
    public static JSONObject mergeTwoJson(JSONObject json1, JSONObject json2) {
        if (json1 == null) {
            return json2;
        }
        if (json2 == null) {
            return json1;
        }
        String json1Str = json1.toString();
        String merge = json1Str.substring(0, json1Str.length() - 1) + "," + json2.toString().substring(1);
        try {
            return new JSONObject(merge);
        } catch (JSONException e) {
            Logger.warning(Logger.GLOBAL_TAG, "Merge two Json Object Exception", e);
            return null;
        }
    }

}
