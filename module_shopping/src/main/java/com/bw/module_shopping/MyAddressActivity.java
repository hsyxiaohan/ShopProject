package com.bw.module_shopping;

import android.view.View;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.address.MyAddress;
import com.bw.library_common.router.address.MyAddressDao;
import com.bw.library_common.router.address.MyAddressDataBase;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

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
public class MyAddressActivity extends BaseActivity {
    private android.widget.EditText massageName;
    private android.widget.EditText massagePhone;
    private android.widget.Button massageBut;
    MyAddressDao myAddressDao;
    private android.widget.EditText messageAddress;

    @Override
    public int bindLayout() {
        return R.layout.shop_myaddress_layout;
    }

    @Override
    public void initView() {
        massageName = findViewById(R.id.massage_name);
        massagePhone = findViewById(R.id.massage_phone);
        massageBut = findViewById(R.id.massage_but);
        messageAddress = findViewById(R.id.message_address);
        ImmersionBar.with(this)
                .statusBarColor("#fff000")
                .init();
        myAddressDao = MyAddressDataBase.getMyAddressDataBase(this).getMyAddressDao();
    }

    @Override
    public void initData() {
        massageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAddressDao.insertData(new MyAddress(null,massageName.getText().toString(),massagePhone.getText().toString(),messageAddress.getText().toString()));
                EventBus.getDefault().post("添加成功");
                finish();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
