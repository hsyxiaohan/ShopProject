package com.bw.module_mine.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.cargo.CarGoDao;
import com.bw.library_common.router.cargo.CarGoDataBase;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.ReceivingAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
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
public class ReceivngActivity extends BaseActivity {
    private androidx.appcompat.widget.Toolbar mineReceivingToolbar;
    private androidx.recyclerview.widget.RecyclerView mineReceivingRv;
    private CarGoDao carGoDao;
    private AlertDialog.Builder builder;
    List<CarGo> carGos;


    @Override
    public int bindLayout() {
        return R.layout.mine_receiving_layout;
    }

    @Override
    public void initView() {
        mineReceivingToolbar = findViewById(R.id.mine_Receiving_toolbar);
        mineReceivingRv = findViewById(R.id.mine_Receiving_rv);
        setSupportActionBar(mineReceivingToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mineReceivingToolbar.setNavigationIcon(R.mipmap.icon_back);
        mineReceivingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mineReceivingRv.setLayoutManager(new LinearLayoutManager(this));
        carGoDao = CarGoDataBase.getCarGoDataBase(this).getCarGoDao();
        ImmersionBar.with(this)
                .statusBarColor("#00a9f4")
                .applySystemFits(true)
                .init();
    }

    @Override
    public void initData() {
        carGos = new ArrayList<>();
        List<CarGo> all = carGoDao.findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isReceiving()){

            }else {
                carGos.add(all.get(i));
            }
        }
        ReceivingAdapter receivingAdapter = new ReceivingAdapter(carGos);
        mineReceivingRv.setAdapter(receivingAdapter);
        builder = new AlertDialog.Builder(this);
        receivingAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.mine_Receiving_item_look){
                    Intent intent = new Intent(ReceivngActivity.this, LogisticsActivity.class);
                    intent.putExtra("img",all.get(position).getPic());
                    intent.putExtra("title",all.get(position).getTitle());
                    startActivity(intent);
                }
                if (view.getId() == R.id.mine_Receiving_item_affirm){
                    builder.setMessage("是否确认收货");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    CarGo carGo = carGoDao.find(carGos.get(position).getId());
                                    carGo.setReceiving(true);
                                    carGoDao.upDataData(carGo);
                                    List<CarGo> all1 = carGoDao.findAll();
                                    List<CarGo> all2 = new ArrayList<>();
                                    for (int i = 0; i < all1.size(); i++) {
                                        if (all1.get(i).isReceiving()){
                                            LoggerUtils.i("111");
                                        }else {
                                            all2.add(all1.get(i));
                                        }
                                    }
                                    receivingAdapter.getData().clear();
                                    receivingAdapter.getData().addAll(all2);
                                    LoggerUtils.i(all2.size()+"333");
                                }
                            }).start();
                            receivingAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
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
