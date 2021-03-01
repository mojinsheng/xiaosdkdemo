package com.from.jmsdk.callback;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;



import com.from.jmsdk.interfaces.ActivityLifeApi;
import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ResourceUtils;
import com.from.jmsdk.tools.consent.Consent;
import com.from.jmsdk.tools.consent.ResourceName;

//TODO DOC javadoc here
public abstract class BaseInitImpl implements ActivityLifeApi {

    public abstract void init(Activity activity, String appId, String appKey, String secret, InitCallback callback);

    public void exit(Activity activity, ExitCallback callback){}

    public void splash(Activity activity){}

    public boolean setLogoutCallback(LogoutCallback logoutCallback) { return false; }


    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onStart(Activity activity) {}

    @Override
    public void onResume(Activity activity) {}

    @Override
    public void onPause(Activity activity) {}

    @Override
    public void onStop(Activity activity) {}

    @Override
    public void onDestroy(Activity activity) {}

    @Override
    public void onSaveInstanceState(Bundle outState) {}


    protected final void displaySplash(final Activity activity, String drawableName){
        if(activity.getWindow() == null){
            Logger.error(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.ACTIVITY_INVISIBLE));
            return;
        }
        Drawable drawable = ResourceUtils.getDrawable(activity, drawableName);
        if(drawable == null) {
            Logger.warning(Logger.GLOBAL_TAG, ResourceUtils.getString(activity, ResourceName.DRAWABLE_MISS_IS), drawableName);
            return;
        }
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ImageView view = new ImageView(activity);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageDrawable(drawable);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        activity.addContentView(view, params);
        new Handler().postDelayed(new Runnable() {
            @Override
            @SuppressWarnings("ConstantConditions")
            public void run() {
                if(view != null && view.getParent() != null) ((ViewGroup)view.getParent()).removeView(view);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }, Consent.SPLASH_TIME);
    }

}
