package com.from.jmsdk.proxy;

import android.app.Activity;
import android.content.res.AssetManager;

import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ResourceUtils;
import com.from.jmsdk.tools.consent.Consent;
import com.from.jmsdk.tools.consent.ResourceName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * A proxy for store key, id & secret
 */
public enum FsdkKeyProxy {
    INSTANCE;


    //JM backend request keys
    public String channel;
    public String packageId;
    public String gameId;
    public String gameChannel;


    private boolean isInitialized;

    FsdkKeyProxy() {
        channel = Consent.DEFAULT;
        packageId = Consent.DEFAULT;
        gameId = Consent.DEFAULT;
        gameChannel = Consent.DEFAULT;
        isInitialized = false;
    }

    //TODO DOC
    public void readKeyToFsdkProxy(Activity activity) {
        if (!isInitialized) {
            InputStreamReader reader = null;
            try {
                Properties prop = new Properties();
                reader = new InputStreamReader(activity.getAssets().open(ResourceName.CONFIG_FILE_NAME, AssetManager.ACCESS_BUFFER), StandardCharsets.UTF_8);
                prop.load(reader);
                this.channel = prop.getProperty(ResourceName.FSDK_CHANNL_CONFIG, Consent.DEFAULT);
                this.packageId = prop.getProperty(ResourceName.FSDK_PACKAGE_CONFIG, Consent.DEFAULT);
                this.gameChannel = prop.getProperty(ResourceName.FSDK_GAME_CHANNEL_CONFIG, Consent.DEFAULT);
                this.gameId = prop.getProperty(ResourceName.FSDK_GAMEID_CONFIG, Consent.DEFAULT);
                isInitialized = true;
            } catch (IOException e) {
                Logger.error(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.CONFIG_FILE_MISS), e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Logger.warning(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.CLOSE_EXCEPTION_IS), "FsdkKeyProxy InputStreamReader", e);
                    }
                }
            }
        }
    }

    public String getTermsString(Activity activity) {
        StringBuilder termsString = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(activity.getAssets().open("user_agreement.txt")));

            String str;
            while ((str = reader.readLine()) != null) {
                termsString.append(str + "\n");
            }
            reader.close();
            return termsString.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Logger.warning(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.CLOSE_EXCEPTION_IS), "FsdkKeyProxy BufferedReader", e);
                }
            }
        }
        return null;
    }

}
