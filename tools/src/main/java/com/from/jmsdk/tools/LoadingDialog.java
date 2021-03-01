package com.from.jmsdk.tools;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


/**
 * description:弹窗浮动加载进度条
 */
public class LoadingDialog {
    /** 加载数据对话框 */
    private static Dialog mLoadingDialog;


    public static Dialog showDialogForLoading(Activity context) {
        if (mLoadingDialog == null){
            View view = LayoutInflater.from(context).inflate(ResourceUtils.getLayoutIdByName(context,"layout_loading"), null);

            mLoadingDialog = new Dialog(context, ResourceUtils.getStyleIdByName(context,"CustomProgressDialog"));
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mLoadingDialog.show();
        }
        return  mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
    }
}
