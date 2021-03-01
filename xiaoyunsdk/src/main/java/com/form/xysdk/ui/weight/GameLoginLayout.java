package com.form.xysdk.ui.weight;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ScreenUtils;


public class GameLoginLayout extends LinearLayout {

    int mSWidth = 1080;
    int mSHeight = 1920;
    private Context mContext;
    int mScreenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ;

    public GameLoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context mContext) {
        Logger.info("============================执行横竖屏之前==============");
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == Configuration.ORIENTATION_PORTRAIT) {
            mSWidth = ScreenUtils.getWidthPixels(mContext);
            mSHeight = ScreenUtils.getHeightPixels(mContext);
            Logger.info("============================执行竖屏之前==============，宽："+mSWidth+",高:"+mSHeight);

        } else {
            mSHeight = ScreenUtils.getWidthPixels(mContext);
            mSWidth = ScreenUtils.getHeightPixels(mContext);
            Logger.info("============================执行横屏之前==============，宽："+mSWidth+",高:"+mSHeight);

        }
    }

//    public void onScreenStateChanged(int screenState) {
//        // TODO Auto-generated method stub
//        super.onScreenStateChanged(screenState);
//        init(GameSDK.getInstance().getApplication());
//    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));

        int baseSize = getBaseSize() ;

        int childWidthSize = (int) (5.2 * baseSize);
        int childHeightSize = (int) (childWidthSize*0.9);

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize,
                MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getBaseSize(){
        int baseSize ;
        if(mScreenOrientation == Configuration.ORIENTATION_LANDSCAPE){
            baseSize = mSHeight / 5 ;//横屏时，取高度为最小计量
        }else {
            baseSize = mSWidth / 6 ;//竖屏时，取宽为最小计量
        }
        if(baseSize == 0){
            baseSize = 180 ;
        }
        return baseSize;
    }

}

