package com.from.jmsdk.tools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TODO DOC whole class
public abstract class Util {

    public static int getVersionCode(@NonNull Context context){
        int versionCode = -1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.warning(Logger.GLOBAL_TAG, "Exception happen when trying to get version code", e);
        }
        return versionCode;
    }

    public static String getVersionName(@NonNull Context context){
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.warning(Logger.GLOBAL_TAG, "Exception happen when trying to get version name", e);
        }
        return versionName;
    }

    public static String getSdkVersion(){
        return BuildConfig.VERSION_NAME;
    }

    public static String getPackageName(@NonNull Activity activity){
        return activity.getPackageName();
    }

    public static boolean verifyKeyID(String id, String key){
        // for now return true anyway, since we don't set key & id
        // Key & id verify feature is plan to next version 1.1
        return true;
    }

    public static boolean isUiThread(){
        return isVersionAboveM() ? isUiThreadApi23() : Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isUiThreadApi23(){
        return Looper.getMainLooper().isCurrentThread();
    }

    public static boolean isVersionAboveM(){
        return getCurrentAndroidVersion() >= Build.VERSION_CODES.M;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getCurrentAndroidVersion(){
        return Build.VERSION.SDK_INT;
    }

    public static int getPid() {
        return android.os.Process.myPid();
    }



    public static String getStackTrace (Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    public static ActivityManager.MemoryInfo getMemoryInfo (Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);
        return info;
    }


    /**
     * Check request permission is granted or not.
     * @param activity context
     * @param permission the permission you would like to check, e.g: Manifest.permission.WRITE_EXTERNAL_STORAGE
     * @return
     * In API 23 and above
     * <ol>
     *     <li>return {@link PackageManager#PERMISSION_GRANTED} (0) if permission granted</li>
     *     <li>return {@link PackageManager#PERMISSION_DENIED} (-1) if permission denied</li>
     * </ol>
     * Lower than API 23
     * <ol>
     *     <li>always return {@link PackageManager#PERMISSION_GRANTED} (0)</li>
     * </ol>
     *
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static int checkAndroidPermission(@NonNull Activity activity, @NonNull String permission){
        if(getCurrentAndroidVersion() >= Build.VERSION_CODES.M) {
            return activity.checkSelfPermission(permission);
        }else {
            return 0;
        }
    }

    public static String getErrorMessage(int code) {
        String msg;
        switch (code) {
            case 1001:
                msg = "请求失败";
                break;
            case 1002:
                msg = "请求数据为空";
                break;
            case 1003:
                msg = "签名错误";
                break;
            case 1004:
                msg = "请求参数错误";
                break;
            case 1005:
                msg = "ip没有访问权限";
                break;
            case 1006:
                msg = "内部服务器错误";
                break;
            case 1007:
                msg = "其他错误";
                break;
            case 1008:
                msg = "请求action不存在，并实例公共类失败";
                break;
            case 1009:
                msg = "后台配置错误";
                break;
            case 1010:
                msg = "无法匹配接口类型";
                break;
            case 1011:
                msg = "实例对象失败";
                break;
            case 1012:
                msg = "数据库操作错误";
                break;
            case 1013:
                msg = "请求次数限制";
                break;
            case 1014:
                msg = "组装数据为空";
                break;
            case 1015:
                msg = "记录不存在";
                break;
            case 1016:
                msg = "缓存错误";
                break;
            case 1017:
                msg = "帐号已经存在";
                break;
            case 1018:
                msg = "密码错误";
                break;
            case 1019:
                msg = "手机信息错误";
                break;
            case 1020:
                msg = "手机验证码失败";
                break;
            case 1021:
                msg = "数据库操作失败";
                break;
            case 1022:
                msg = "手机号码已存在";
                break;
            case 1023:
                msg = "帐号不存在";
                break;
            case 1024:
                msg = "登录校验sessionId错误";
                break;
            case 1025:
                msg = "获取session_key失败";
                break;
            case 1026:
                msg = "状态关闭";
                break;
            case 1027:
                msg = "余额不足";
                break;
            case 1028:
                msg = "扣款失败";
                break;
            case 1100:
                msg = "要求重复请求检查";
                break;
            case 1999:
                msg = "内部强制打印日志";
                break;
            default:
                msg = "未知错误";
                break;
        }
        return msg;
    }

    /**
     * 判断是否包含特殊字符
     * @return  false:未包含 true：包含
     */
    public static boolean isSpecialChar(String editText) {
        String speChar = "[`~!！@#$%^&*()+=|{}':;',.<>/?￥%…（）【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChar);
        Matcher matcher = pattern.matcher(editText);
        return matcher.find();
    }

    //固定屏幕方向
    public static void fixOrientation(Activity activity, int orientation) {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(activity);
            o.screenOrientation = orientation;
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
