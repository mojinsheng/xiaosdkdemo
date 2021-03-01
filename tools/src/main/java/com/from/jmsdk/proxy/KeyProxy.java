package com.from.jmsdk.proxy;

import android.content.Context;
import android.content.res.AssetManager;


import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ResourceUtils;
import com.from.jmsdk.tools.consent.Consent;
import com.from.jmsdk.tools.consent.ResourceName;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * A proxy for store key, id & secret
 */
public enum  KeyProxy {
    INSTANCE;

    //Store keys
    public String qq_app_id;
    public String wx_app_id;
    public String secret;
    public String gameurl;

    //Store flag
    public String channelName;

    //JM backend request keys
    public String channel;
    public String packageId;
    public String gameId;
    public String gametag;
    public String jmkey;
    public String fmkey;
    public String reyunchannl;
    public String trackid;
    public String channlUrl;
    public String base_jmapi_url;
    public String gamereport_id;
    public String gamereport_key;
    private boolean isInitialized;

    KeyProxy(){
        qq_app_id = Consent.DEFAULT;
        wx_app_id = Consent.DEFAULT;
        secret = Consent.DEFAULT;
        channel = Consent.DEFAULT;
        packageId = Consent.DEFAULT;
        gameId = Consent.DEFAULT;
        gametag = Consent.DEFAULT;
        channelName = Consent.DEFAULT;
        reyunchannl = Consent.DEFAULT;
        trackid = Consent.DEFAULT;
        channlUrl =Consent.DEFAULT;
        base_jmapi_url = Consent.DEFAULT;
        gamereport_id = Consent.DEFAULT;
        gamereport_key = Consent.DEFAULT;
        gameurl = Consent.DEFAULT;
        isInitialized = false;
    }

    //TODO DOC
    public boolean readKeyToProxy(Context activity){
        if(!isInitialized) {
            InputStreamReader reader = null;
            try {
                Properties prop = new Properties();
                reader = new InputStreamReader(activity.getAssets().open(ResourceName.CONFIG_FILE_NAME, AssetManager.ACCESS_BUFFER), StandardCharsets.UTF_8);
                prop.load(reader);
                this.wx_app_id = prop.getProperty(ResourceName.WX_APP_ID, Consent.DEFAULT);
                this.qq_app_id = prop.getProperty(ResourceName.QQ_APP_ID, Consent.DEFAULT);
                this.secret = prop.getProperty(ResourceName.CONFIG_PROXY_SECRET, Consent.DEFAULT);
                this.channel = prop.getProperty(ResourceName.CONFIG_PROXY_CHANNEL, Consent.DEFAULT);
                this.packageId = prop.getProperty(ResourceName.CONFIG_PROXY_PACKAGE_ID, Consent.DEFAULT);
                this.gameId = prop.getProperty(ResourceName.CONFIG_PROXY_GAME_ID, Consent.DEFAULT);
                this.gametag = prop.getProperty(ResourceName.GAMETAG, Consent.DEFAULT);
                this.jmkey = prop.getProperty(ResourceName.JMKEY, Consent.DEFAULT);
                this.fmkey = prop.getProperty(ResourceName.FMKEY, Consent.DEFAULT);
                this.reyunchannl= prop.getProperty(ResourceName.REYUNCHANNL, Consent.DEFAULT);
                this.trackid = prop.getProperty(ResourceName.TRACKID, Consent.DEFAULT);
                this.channlUrl = prop.getProperty(ResourceName.CHANNLURL, Consent.DEFAULT);
                this.base_jmapi_url=prop.getProperty(ResourceName.BASE_JMAPI_URL, Consent.DEFAULT);
                this.channelName = prop.getProperty(ResourceName.CONFIG_PROXY_STORE_NAME, Consent.DEFAULT);
                this.gamereport_id = prop.getProperty(ResourceName.GAMEREPORT_ID, Consent.DEFAULT);
                this.gamereport_key = prop.getProperty(ResourceName.GAMEREPORT_KEY, Consent.DEFAULT);
                gameurl = prop.getProperty(ResourceName.GAMEURL, Consent.DEFAULT);
                isInitialized = true;
            }catch (IOException e) {
                Logger.error(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.CONFIG_FILE_MISS), e);
            }finally {
                if(reader != null){
                    try{
                        reader.close();
                    }catch (IOException e){
                        Logger.warning(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.CLOSE_EXCEPTION_IS), "KeyProxy InputStreamReader", e);
                    }
                }
            }
        }
        Logger.info("初始化数据:"+toString());
        return isInitialized;
    }

    @Override
    public String toString() {
        return "KeyProxy{" +
                "qq_app_id='" + qq_app_id + '\'' +
                ", wx_app_id='" + wx_app_id + '\'' +
                ", secret='" + secret + '\'' +
                ", gameurl='" + gameurl + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channel='" + channel + '\'' +
                ", packageId='" + packageId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", gametag='" + gametag + '\'' +
                ", jmkey='" + jmkey + '\'' +
                ", fmkey='" + fmkey + '\'' +
                ", reyunchannl='" + reyunchannl + '\'' +
                ", trackid='" + trackid + '\'' +
                ", channlUrl='" + channlUrl + '\'' +
                ", base_jmapi_url='" + base_jmapi_url + '\'' +
                ", gamereport_id='" + gamereport_id + '\'' +
                ", gamereport_key='" + gamereport_key + '\'' +
                ", isInitialized=" + isInitialized +
                '}';
    }
}
