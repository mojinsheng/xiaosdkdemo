package com.form.xysdk.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ResourceUtils;

import java.util.Observer;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ContainerActivity extends FragmentActivity {
//    protected SdkManager mManager;
//    protected AdsEventListener mEvent;
    private FragmentManager manager;
    private FragmentTransaction transaction = null;
    // 定义一个变量，来标识是否退出
    private boolean isExit = false;
    private String loginType;
    private String fragmentType = "";
    // 定义一个变量，来标识是否退出
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Logger.info("=========================开始===========");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(ResourceUtils.getLayoutIdByName(this, "base_activity_landscape"));
        Logger.info("=========================1==========="+ResourceUtils.getLayoutIdByName(this, "base_activity_landscape"));

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
//        transaction.replace(ResourceUtils.getViewIdByName(this, "cl_login"), new AccountLoginFragment(), AccountLoginFragment.class.getSimpleName());

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        Logger.info("=========================结束===========");

       // initSdkListner();
//        if(!PermissionUtil.requestPermissions_PHONE_STORAGE(this, ConstantUI.MAIN_SD_CODE)){
//            return;
//        }

    }


    public void startFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                ResourceUtils.getAnimIdByName(this, "fragment_enter_go"),
                ResourceUtils.getAnimIdByName(this, "fragment_exit_go"),
                ResourceUtils.getAnimIdByName(this, "fragment_enter_back"),
                ResourceUtils.getAnimIdByName(this, "fragment_exit_back"));
        transaction.replace(ResourceUtils.getViewIdByName(this, "cl_login"), fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //弹出对话框方法
    private void showExitGameAlert() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(ResourceUtils.getLayoutIdByName(this, "layout_dialog"));
        TextView tv = (TextView) window.findViewById(ResourceUtils.getViewIdByName(this, "tv_no"));
        tv.setText("你确定要退出吗");
        LinearLayout ok = (LinearLayout) window.findViewById(ResourceUtils.getViewIdByName(this, "tv_ok"));
        //确定按钮
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                exit(); // 退出应用
            }
        });

        //取消按钮
        LinearLayout cancel = (LinearLayout) window.findViewById(ResourceUtils.getViewIdByName(this, "tv_cancel"));
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlg.cancel();
            }

        });
    }


    /**
     * 初始化SdkManager
     *
     * @throws java.lang.InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
//    private void initSdkListner() {
//        try {
//            if (mManager == null) {
//                //String requestlistener = EfunResourceUtil.getApplicationMetaData(this, MetaData.SDK_LISTENER);
//                String requestlistener = "com.from.wtlib.logintask.SdkListener";
//                if (!TextUtils.isEmpty(requestlistener) && requestlistener.startsWith("com.")) {
//                    @SuppressWarnings("unchecked")
//                    Class<SdkManager> clazz = (Class<com.from.control.SdkManager>) Class.forName(requestlistener);
//                    if (clazz != null) {
//                        mManager = clazz.newInstance();
//                        /**
//                         * 添加监听者
//                         */
//                        SdkManager.Response response = new SdkManager.Response();
//                        response.setStatus(0, null,null);
//                        response.addObserver(new StatusObserver());
//                    }
//                }
//            }
//            if (mEvent == null) {
//                //String eventlistener = EfunResourceUtil.getApplicationMetaData(this,MetaData.EVENT_LISTENER);
//                String eventlistener = "com.from.wtlib.logintask.EventListener";
//                if (!TextUtils.isEmpty(eventlistener) && eventlistener.startsWith("com.")) {
//                    @SuppressWarnings("unchecked")
//                    Class<AdsEventListener> clazz = (Class<com.from.control.AdsEventListener>) Class.forName(eventlistener);
//                    if (clazz != null) {
//                        mEvent = clazz.newInstance();
//                    }
//                }
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    public SdkManager getManager() {
//        return mManager;
//    }


    //onPause

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void exit() {
        if (!isExit) {
            isExit = true;

//            ToastUtil.showLong(this,"再按一次退出程序");

            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }



    /**
     * 监听者
     *
     * @author Jesse
     */
//    public class StatusObserver implements Observer {
//        @Override
//        public void update(Observable observable, Object data) {
//            int status = ((SdkManager.Response) observable).getStatus();
//            String code = ((SdkManager.Response) observable).getCode();
//            String userId = ((SdkManager.Response) observable).getUserId();
//            String[] inputs = ((SdkManager.Response) observable).getInputs();
//            /**
//             * efun,mac,facebook 方式登陆销毁页面，否则回滚一个碎片
//             */
//            if ( status ==SdkManager.Request.REQUEST_LOGIN_WT) {
//                mEvent.loginEvent(ContainerActivity.this, status,userId);
//                SdkManager.Request request = new SdkManager.Request();
//
//                request.setRequestType(SdkManager.Request.REQUEST_CHECK_VERIFIED);
//                mManager.sdkRequest(ContainerActivity.this, request);
//            } else if (status == SdkManager.Request.REQUEST_LOGIN_QUICK ||status==SdkManager.Request.REQUEST_LOGIN_PHONE) {
//                mEvent.registerEvent(ContainerActivity.this, status,userId);
//
////                }
//            }
//
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == ConstantUI.QUICK_SD_CODE && hasAllPermissionsGranted(grantResults)) {
//            //  FSDK.INSTANCE.logAction(ContantsUI.START_APP);
////            ScreenshotUtil screenshotUtil=new ScreenshotUtil();
////            screenshotUtil.viewSaveToImage(layout_quick,this);
//            if (ScreenshotUtil.shotScreen(this)) {
//                ToastUtil.showLong(this,"已保存到你的相册中");
//
//            } else {
//                ToastUtil.showLong(this,"保存失败");
//
//            }
//
//        }else if(requestCode == ConstantUI.MAIN_SD_CODE && hasAllPermissionsGranted(grantResults)){
//
//        } else {
//            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
//            ToastUtil.showLong(this,"应用缺少必要的权限！请点击\"权限\"，打开所需要的权限");
//
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivity(intent);
//            finish();
//        }
//    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KEYCODE_BACK) {
//
//
//            Fragment fragment = manager.findFragmentByTag(AccountLoginFragment.class.getSimpleName());
//
//            if(fragment==null){
//                finish();
//            }else{
//                if (fragment.isVisible()) {
//                    //finish();
//                    exit(); // 退出应用
//                } else {
//                    this.onBackPressed();
//                }
//            }
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
