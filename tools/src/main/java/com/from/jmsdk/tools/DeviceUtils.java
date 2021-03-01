package com.from.jmsdk.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.from.jmsdk.tools.consent.ResourceName;

import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


/**
 * <p>
 * 此类用来生成唯一设备码。
 * </p>
 * <ol>
 * 生成逻辑如下：
 * <li>首先查询本地 {@link SharedPreferences} 中是否有已经生成的值，有则直接使用</li>
 * <li>若失败，则尝试获取设备 <b>IMEI</b> 号，获取成功则返回 <b>"imei_$value"</b></li>
 * <li>若失败，则尝试获取设备 <b>MAC</b> 地址, 获取成功作为基础值 <b>"mac_$value"</b></li>
 * <li>若失败，则尝试获取设备 <b>Android ID</b>, 获取成功作为基础值 <b>"androidid_$value_4位随机码"</b></li>
 * <li>若失败，则尝试获取设备 <b>UUID</b> 地址, 获取成功作为基础值 <b>"uuid_&value"</b></li>
 * <li>UUID 理论上不会获取失败，且有较好唯一性</li>
 * </ol>
 * <p>
 * </p>
 * <p>
 * 注意： 获取 IMEI 需要  {@link Manifest.permission#READ_PHONE_STATE READ_PHONE_STATE} 权限
 * </p>
 */
@SuppressWarnings("Hardwareids")
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public abstract class DeviceUtils {

    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    public static String OAID = "";
    public static String nativePhoneNumber = "";

    private static String hostIp6;



    public static String getDeviceId(Activity activity) {
        String id = "";
        String gross;
        SharedPreferences preferences = activity.getApplicationContext().getSharedPreferences(ResourceName.SHARED_JM_FILE, Activity.MODE_PRIVATE);
        if (preferences.contains(ResourceName.SHARED_UNIQUE_DEVICE_ID)) {
            return preferences.getString(ResourceName.SHARED_UNIQUE_DEVICE_ID, "");
        }

        if (TextUtils.isEmpty(id) && !TextUtils.isEmpty(gross = getIMEI(activity))) {
            id = gross;
        }
        if (TextUtils.isEmpty(id) && !TextUtils.isEmpty(gross = getMac(activity))) {
            id =  gross.replace(":", "");
        }
        if (TextUtils.isEmpty(id) && !TextUtils.isEmpty(gross = getAndroidId(activity))) {
            id =  gross ; // Android id 可能在极少数情况下重复，此处添加一个随机数
        }

        if (TextUtils.isEmpty(id)) {
            id = getUUID();
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ResourceName.SHARED_UNIQUE_DEVICE_ID, id);
        editor.apply();
        Logger.info(Logger.GLOBAL_TAG, "Generate a new device id: %s", id);

        return id;
    }
    /**
     * 获取IMEI
     *
     * @return
     */
    public static String getIMEI(Activity activity) {
        if (Util.checkAndroidPermission(activity, Manifest.permission.READ_PHONE_STATE) == 0) {

            return  getIMEI(activity,0);
        } else {
            return "";
        }
    }


    /**
     * @param slotId slotId为卡槽Id，它的值为 0、1；
     * @return
     */
    public static String getIMEI(Context context, int slotId) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, slotId);
            if (TextUtils.isEmpty(imei)) {
                return "";
            }
            return imei;
        } catch (Exception e) {
            return "";
        }
    }


    public static String getOAID() {
        return OAID;
    }

        //获取电话号码
    public static String getNativePhoneNumber(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }

        nativePhoneNumber = telephonyManager.getLine1Number();
        return nativePhoneNumber;
    }


    public static String getMac(Activity activity) {
        String mac = "";
        if (PermissionUtil.getCurrentAndroidVersion() >= Build.VERSION_CODES.M) {
            try {
                List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                Log.d("Utils", "all:" + all.size());
                for (NetworkInterface nif : all) {
                    if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return null;
                    }
                    Log.d("Utils", "macBytes:" + macBytes.length + "," + nif.getName());

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }

            } catch (SocketException e) {
                Log.i("Utils", "Get Mac address via NetworkInterface exception");

            }
        } else {
            WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            mac = wifiManager.getConnectionInfo().getMacAddress();
        }
        return (TextUtils.isEmpty(mac) || DEFAULT_MAC_ADDRESS.equals(mac)) ? "" : mac;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getActionID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getAndroidId(Activity activity) {
        String id = Settings.System.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        return TextUtils.isEmpty(id) ? "" : id;
    }


    /**
     * <p>
     * As it said, simply get IP address,
     * <ol>
     * First it will try to get IP address from {@link NetworkInterface}
     * </ol>
     * <ol>
     * Then it will try to get IP address from {@link android.net.wifi.WifiInfo#getIpAddress()}
     * </ol>
     * <ol>
     * Return empty string if any exception happen.
     * </ol>
     * </p>
     *
     * @param activity activity context
     * @return IP Address, support both IPV4 & IPV6 address, support mobile network & WIFI
     * "" if exception happen
     */
    /**
     * 获取本机IPv4地址
     *
     * @param context
     * @return 本机IPv4地址；null：无网络连接
     */
    public static String getIpAddress(Context context) {
        // 获取WiFi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 判断WiFi是否开启
        if (wifiManager.isWifiEnabled()) {
            // 已经开启了WiFi
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = intToIp(ipAddress);
            return ip;
        } else {
            // 未开启WiFi
            return getIpAddress();
        }
    }

    private static String intToIp(int ipAddress) {
        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                (ipAddress >> 24 & 0xFF);
    }

    /**
     * 获取本机IPv4地址
     *
     * @return 本机IPv4地址；null：无网络连接
     */
    private static String getIpAddress() {
        try {
            NetworkInterface networkInterface;
            InetAddress inetAddress;
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                networkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    //ipv6
    public static String getLocalIpV6() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    // logger.error("ip1       " + inetAddress);
                    Logger.error("ip1  " + inetAddress.getHostAddress());
                 /*   logger.error("getHostName  " + inetAddress.getHostName());
                    logger.error("getCanonicalHostName  " + inetAddress.getCanonicalHostName());
                    logger.error("getAddress  " + Arrays.toString(inetAddress.getAddress()));
                    logger.error("getHostAddress  " + inetAddress.getHostAddress());*/

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
                        return inetAddress.getHostAddress();
                    }


                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
    public static String getlocalIp() {
        String ip;

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
//                           ip=inetAddress.getHostAddress().toString();
                        System.out.println("ip==========" + inetAddress.getHostAddress());
                        return inetAddress.getHostAddress();

                    }
                }
            }
        } catch (SocketException ignored) {

        }
        return null;
    }

    public static String validateV6() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                hostIp6 = getLocalIpV6();
            }
        }).start();

        //过滤找到真实的ipv6地址
        Logger.error("v6 validateV6 " + hostIp6);
        if (hostIp6 != null && hostIp6.contains("%")) {
            String[] split = hostIp6.split("%");
            String s1 = split[0];
            Logger.error("v6 remove % is " + s1);

            if (s1 != null && s1.contains(":")) {
                String[] split1 = s1.split(":");
                if (split1.length == 6||split1.length==8) {
                    if (split1[0].contains("fe") || split1[0].contains("fc")) {
                        return "0.0.0.0";
                    } else {
                        return s1;
                    }
                }
            }
        }
        return "0.0.0.0";
    }



}
