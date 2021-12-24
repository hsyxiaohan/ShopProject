package com.bw.module_user;

import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.library_base.view.BaseActivity;
import com.bw.module_user.bean.LoginAndRegBean;
import com.bw.module_user.contract.RegContract;
import com.bw.module_user.model.RegModel;
import com.bw.module_user.presenter.RegPresenter;
import com.gyf.immersionbar.ImmersionBar;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
public class RegisterActivity extends BaseActivity<RegPresenter> implements RegContract.IRegView {
    private android.widget.EditText regUsername;
    private android.widget.EditText regPassword;
    private android.widget.EditText regSex;
    private android.widget.EditText regBirthday;
    private android.widget.Button regBtn;

    @Override
    public int bindLayout() {
        return R.layout.activity_reg;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this)
                .applySystemFits(true)
                .statusBarColor("#CBF6DBE5")
                .init();
        regUsername = findViewById(R.id.reg_username);
        regPassword = findViewById(R.id.reg_password);
        regSex = findViewById(R.id.reg_sex);
        regBirthday = findViewById(R.id.reg_birthday);
        regBtn = findViewById(R.id.reg_btn);
        mPresenter = new RegPresenter(new RegModel(),this);
    }

    @Override
    public void initData() {
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAndRegBean.DataBean dataBean = new LoginAndRegBean.DataBean();
                dataBean.setUsername(regUsername.getText().toString());
                dataBean.setPwd(regPassword.getText().toString());
                dataBean.setSex(regSex.getText().toString());
                dataBean.setBirthday(regBirthday.getText().toString());
                mPresenter.reg(dataBean);
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
        if (loginAndRegBean.getCode()==200){
            ToastUtils.showShort("注册成功");
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
