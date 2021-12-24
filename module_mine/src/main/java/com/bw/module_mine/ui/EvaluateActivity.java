package com.bw.module_mine.ui;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.bw.library_base.view.BaseActivity;
import com.bw.module_mine.R;
import com.bw.module_mine.adapter.MyFragmentAdapter;
import com.bw.module_mine.ui.fragment.HaveEvaluationFragment;
import com.bw.module_mine.ui.fragment.NoEvaluation;
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
public class EvaluateActivity extends BaseActivity
{
    private androidx.appcompat.widget.Toolbar mineEvaluateToolbar;
    private com.flyco.tablayout.SlidingTabLayout mineEvaluateTl;
    private androidx.viewpager.widget.ViewPager mineEvaluateVp;
    private List<Fragment> list;
    private List<String> titles;

    @Override
    public int bindLayout() {
        return R.layout.mine_evaluate_layout;
    }

    @Override
    public void initView() {
        mineEvaluateToolbar = findViewById(R.id.mine_evaluate_toolbar);
        mineEvaluateTl = findViewById(R.id.mine_evaluate_tl);
        mineEvaluateVp = findViewById(R.id.mine_evaluate_vp);
        setSupportActionBar(mineEvaluateToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mineEvaluateToolbar.setNavigationIcon(R.mipmap.icon_back);
        mineEvaluateToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        list = new ArrayList<>();
        titles = new ArrayList<>();
        list.add(new NoEvaluation());
        list.add(new HaveEvaluationFragment());
        titles.add("未评价");
        titles.add("已评价");
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), list, titles);
        mineEvaluateVp.setAdapter(myFragmentAdapter);
        mineEvaluateTl.setViewPager(mineEvaluateVp);
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
