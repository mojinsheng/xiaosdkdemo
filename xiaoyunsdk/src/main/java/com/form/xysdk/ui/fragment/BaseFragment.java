package com.form.xysdk.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.form.xysdk.ui.activity.ContainerActivity;


/**
 * 基类碎片
 *
 * @author Jesse
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    //	protected SdkListener mManager;
    protected View mView;
//    protected SdkManager mSDKManager;

    protected abstract View getContentView(LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 初始化监听
     */
    protected abstract void initListeners();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = getActivity();

    }

    //获取布局文件
    protected abstract int getLayoutResource();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = getContentView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mSDKManager = ((ContainerActivity) (getActivity())).getManager();

        initViews();
        initDatas();
        initListeners();
//        Logger.info("================" + mSDKManager);
    }

    /**
     * 启动 fragment
     *
     * @param fragment
     * @param tag
     */
    public void startFragment(Fragment fragment, String tag) {
        ((ContainerActivity) getActivity()).startFragment(fragment, tag);
    }

    public static void showLong(Activity activity, CharSequence message) {

        Toast toast = Toast.makeText(activity, null, Toast.LENGTH_LONG);
        toast.setText(message);
        toast.show();
    }


}
