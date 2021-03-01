package com.from.jmsdk.interfaces;

import android.app.Activity;
import android.os.Bundle;



//TODO OP
public interface ActivityLifeApi {

    void onCreate(Activity activity, Bundle savedInstanceState);

    void onStart(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);

    void  onSaveInstanceState(Bundle outState);
}
