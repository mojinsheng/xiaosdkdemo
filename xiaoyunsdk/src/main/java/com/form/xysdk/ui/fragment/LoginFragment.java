package com.form.xysdk.ui.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.form.xysdk.R;
import com.from.jmsdk.tools.Logger;
import com.from.jmsdk.tools.ResourceUtils;

public class LoginFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    TextView txt_regiter,btn_code;
    RadioGroup radio_login_type;
    RadioButton  radio_account,radio_phone;
    EditText et_account,et_password;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResource(), container, false);
        }
        return mView;
    }

    @Override
    protected void initViews() {
        txt_regiter = mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"txt_regiter"));
        radio_login_type=mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"radio_login_type"));
        radio_account =mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"radio_account"));
        radio_phone = mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"radio_phone"));
        btn_code = mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"btn_code"));
        et_account = mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"et_account"));
        et_password = mView.findViewById(ResourceUtils.getViewIdByName(mActivity,"et_password"));
        radio_login_type.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==radio_account.getId()){
            btn_code.setVisibility(View.GONE);

            et_account.setHint(ResourceUtils.getString(mActivity,"input_hint_account"));
            et_password.setHint(ResourceUtils.getString(mActivity,"input_hint_password"));
        }else {
            btn_code.setVisibility(View.VISIBLE);
            et_account.setHint(ResourceUtils.getString(mActivity,"input_hint_phone"));
            et_password.setHint(ResourceUtils.getString(mActivity,"input_hint_code"));


        }
    }

    @Override
    protected void initListeners() {
        txt_regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return ResourceUtils.getLayoutIdByName(mActivity,"fragment_login");

    }
}
