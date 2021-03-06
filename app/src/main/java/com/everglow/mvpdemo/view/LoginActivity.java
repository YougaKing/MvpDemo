package com.everglow.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.everglow.mvpdemo.R;
import com.everglow.mvpdemo.contract.UserContract;
import com.everglow.mvpdemo.model.UserInfoBean;
import com.everglow.mvpdemo.presenter.LoginPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements UserContract.LoginView {


    /**
     * 测试账号
     * 企业ID 44444
     * 账号   李媛abcd
     * 密码   abc123
     */
    @Bind(R.id.tv_empty)
    TextView mTvEmpty;
    @Bind(R.id.tv_company)
    TextView mTvCompany;
    @Bind(R.id.et_company)
    EditText mEtCompany;
    @Bind(R.id.tv_user)
    TextView mTvUser;
    @Bind(R.id.et_user)
    EditText mEtUser;
    @Bind(R.id.tv_pwd)
    TextView mTvPwd;
    @Bind(R.id.et_pwd)
    EditText mEtPwd;
    @Bind(R.id.tv_submit)
    TextView mTvSubmit;
    UserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new LoginPresenter(this).onSubscribe();

        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put("keynumber", "");
                map.put("username", "");
                map.put("password", "");
                presenter.login(map);
            }
        });
    }

    @Override
    public void showLoading() {
        mTvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mTvEmpty.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(UserInfoBean user) {
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void toastMessage(UserInfoBean user) {
        Toast.makeText(getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "网络链接错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
