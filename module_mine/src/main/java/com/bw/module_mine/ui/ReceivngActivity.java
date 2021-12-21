package com.bw.module_mine.ui;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.cargo.CarGoDao;
import com.bw.library_common.router.cargo.CarGoDataBase;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.ReceivingAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

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
    }

    @Override
    public void initData() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(1));
        List<CarGo> all = carGoDao.findAll();
        ReceivingAdapter receivingAdapter = new ReceivingAdapter(all);
        mineReceivingRv.setAdapter(receivingAdapter);
        receivingAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.mine_Receiving_item_look){
                    Intent intent = new Intent(ReceivngActivity.this, LogisticsActivity.class);
                    intent.putExtra("img",all.get(position).getPic());
                    intent.putExtra("title",all.get(position).getTitle());

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
