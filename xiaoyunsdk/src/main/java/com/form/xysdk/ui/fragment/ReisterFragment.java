package com.form.xysdk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.from.jmsdk.tools.ResourceUtils;

public class ReisterFragment extends BaseFragment {
    TextView txt_regiter;
    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResource(), container, false);
        }
        return mView;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListeners() {
    }

    @Override
    protected int getLayoutResource() {
        return ResourceUtils.getLayoutIdByName(mActivity,"fragment_login");

    }
}
