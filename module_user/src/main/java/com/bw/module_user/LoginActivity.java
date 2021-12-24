package com.bw.module_user;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.router.ARouterActivityPath;
import com.bw.library_common.router.utils.SpUtils;
import com.bw.module_user.bean.LoginAndRegBean;
import com.bw.module_user.contract.LoginContract;
import com.bw.module_user.model.LoginModel;
import com.bw.module_user.presenter.LoginPresenter;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import okhttp3.internal.cache.DiskLruCache;

@Route(path = ARouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    private static final String TAG = "login";
    private android.widget.EditText loginUsername;
    private android.widget.EditText loginPassword;
    private android.widget.Button loginBtn;
    private android.widget.Button regBtn;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this)
                .applySystemFits(true)
                .statusBarColor("#CBF6DBE5")
                .init();
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        regBtn = findViewById(R.id.reg_btn);
        mPresenter = new LoginPresenter(new LoginModel(),this);
    }


    @Override
    public void initData() {

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAndRegBean.DataBean dataBean = new LoginAndRegBean.DataBean();
                dataBean.setUsername(loginUsername.getText().toString());
                dataBean.setPwd(loginPassword.getText().toString());
                mPresenter.login(dataBean);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(LoginAndRegBean loginAndRegBean) {
        SpUtils instance = SpUtils.getInstance("login.xml", MODE_PRIVATE, this);
        if (loginAndRegBean.getCode() == 200){
            instance.put("islogin",true);
            instance.put("username",loginAndRegBean.getData().getUsername());
            ARouter.getInstance().build(ARouterActivityPath.Main.PAGER_MAIN).navigation();
        }
    }
}