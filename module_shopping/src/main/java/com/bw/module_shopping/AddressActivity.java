package com.bw.module_shopping;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.address.MyAddress;
import com.bw.library_common.router.address.MyAddressDao;
import com.bw.library_common.router.address.MyAddressDataBase;
import com.bw.module_shopping.adapter.MyAddressAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

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
//收货地址页面
public class AddressActivity extends BaseActivity {
    private androidx.appcompat.widget.Toolbar shopAddressToolbar;
    private androidx.recyclerview.widget.RecyclerView shopAddress;
    private android.widget.Button shopAddressBtn;
    MyAddressDao myAddressDao;
    List<MyAddress> all;
    MyAddressAdapter myAddressAdapter;




    @Override
    public int bindLayout() {
        return R.layout.shop_address_layout;
    }

    @Override
    public void initView() {
        shopAddressToolbar = findViewById(R.id.shop_address_toolbar);
        shopAddress = findViewById(R.id.shop_address);
        shopAddressBtn = findViewById(R.id.shop_address_btn);
        setSupportActionBar(shopAddressToolbar);
        ImmersionBar.with(this)
                .statusBarColor("#12B5FF")
                .applySystemFits(true)
                .init();
        shopAddress.setLayoutManager(new LinearLayoutManager(this));
        myAddressDao = MyAddressDataBase.getMyAddressDataBase(this).getMyAddressDao();
        EventBus.getDefault().register(this);

    }

    @Override
    public void initData() {
        all = myAddressDao.findAll();
        myAddressAdapter = new MyAddressAdapter(all);
        shopAddress.setAdapter(myAddressAdapter);
        shopAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,MyAddressActivity.class));
            }
        });
        myAddressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                MyAddress myAddress = myAddressDao.find(all.get(position).getId());
                EventBus.getDefault().post(myAddress);
                finish();
            }
        });
    }

    @Subscribe
    public void getMsg(String msg){
        if (msg.equals("添加成功")){
            all = myAddressDao.findAll();
            myAddressAdapter.getData().clear();
            myAddressAdapter.getData().addAll(all);
            myAddressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
