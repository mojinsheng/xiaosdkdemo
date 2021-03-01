package com.from.jmsdk.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Author : Ziwen Lan
 * Date : 2019/10/23
 * Time : 15:11
 * Introduction : 截屏工具类
 */
public class ScreenshotUtil {

    /**
     * 截取指定activity显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromActivity(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, activity);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 截取指定View显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromView(View view, Activity context) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, context);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 保存图片至相册
     * 需要读写权限
     */
    private static void saveImageToGallery(Bitmap bmp, Activity context) {
        File appDir = new File(getDCIM());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        if (!file.exists()) {
            Logger.info("文件不存在");
        }else {
            Logger.info("文件存在");

        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + getDCIM())));
    }

    /**
     * 获取相册路径
     */
    private static String getDCIM() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return "";
        }
        String path =Environment.getExternalStorageDirectory()+ "/DCIM/";
        Logger.info("保存的路径:"+path);
        ;
        if (new File(path).exists()) {
            return path;
        }
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return "";
            }
        }
        return path;
    }

    public static boolean shotScreen(Activity activity) {

        String path =Environment.getExternalStorageDirectory()+ "/DCIM/Camera/";
        File appDir = new File(getDCIM());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        Logger.info("====================fileName:"+file);

        //获取屏幕截图
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
            fos.flush();
            fos.close();

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            activity.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
