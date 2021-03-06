package com.form.xy;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.form.xysdk.plafrom.XYPlafrom;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XYPlafrom.getInstance().wtLogin(MainActivity.this);
            }
        });
    }
}
