package com.bw.module_mine.ui;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.LogisticsAdapter;
import com.bw.module_mine.bean.LogisticsBean;
import com.bw.module_mine.contract.MineContract;
import com.bw.module_mine.model.MineModel;
import com.bw.module_mine.presenter.MinePresenter;
import com.gyf.immersionbar.ImmersionBar;

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
 * 物流页面
 **/
public class LogisticsActivity extends BaseActivity<MinePresenter> implements MineContract.ILogisticsView {
    private androidx.appcompat.widget.Toolbar logisticsToolbar;
    private android.widget.ImageView logisticsImg;
    private androidx.recyclerview.widget.RecyclerView logisticsRv;
    private android.widget.TextView logisticsTv;


    @Override
    public int bindLayout() {
        return R.layout.mine_logistics_layout;
    }

    @Override
    public void initView() {
        logisticsToolbar = findViewById(R.id.logistics_toolbar);
        logisticsImg = findViewById(R.id.logistics_img);
        logisticsRv = findViewById(R.id.logistics_rv);
        logisticsTv = findViewById(R.id.logistics_tv);
        mPresenter = new MinePresenter(new MineModel(),this);
        logisticsRv.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(logisticsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logisticsToolbar.setNavigationIcon(R.mipmap.icon_back);
        logisticsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImmersionBar.with(this)
                .statusBarColor("#00a9f4")
                .applySystemFits(true)
                .init();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String title = intent.getStringExtra("title");
        ImageLoader.LoadImage(this,img,logisticsImg,R.mipmap.ic_launcher);
        logisticsTv.setText(title);
        mPresenter.getLogistics();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initAdapter(List<LogisticsBean.DataBean> dataBeans) {
        LogisticsAdapter logisticsAdapter = new LogisticsAdapter(dataBeans);
        logisticsRv.setAdapter(logisticsAdapter);
    }
}
