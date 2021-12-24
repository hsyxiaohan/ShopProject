package com.bw.module_mine.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RatingBar;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.cargo.CarGoDao;
import com.bw.library_common.router.cargo.CarGoDataBase;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_mine.R;
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
public class EvaluationActivity extends BaseActivity {
    private androidx.appcompat.widget.Toolbar mineEvaluationToolbar;
    private android.widget.ImageView evaluationImg;
    private android.widget.TextView logisticsTv;
    private android.widget.LinearLayout etxt;
    private android.widget.TextView txt;
    private android.widget.RatingBar ratingbar;
    private android.widget.Button commitBtn;
    private CarGoDao carGoDao;


    @Override
    public int bindLayout() {
        return R.layout.mine_evaluation_layout;
    }

    @Override
    public void initView() {

        mineEvaluationToolbar = findViewById(R.id.mine_evaluation_toolbar);
        evaluationImg = findViewById(R.id.evaluation_img);
        logisticsTv = findViewById(R.id.logistics_tv);
        etxt = findViewById(R.id.etxt);
        commitBtn = findViewById(R.id.commit_btn);
        txt = findViewById(R.id.txt);
        ratingbar = findViewById(R.id.ratingbar);
        setSupportActionBar(mineEvaluationToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mineEvaluationToolbar.setNavigationIcon(R.mipmap.icon_back);
        mineEvaluationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImmersionBar.with(this)
                .statusBarColor("#00a9f4")
                .applySystemFits(true)
                .init();
        carGoDao = CarGoDataBase.getCarGoDataBase(this).getCarGoDao();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String title = intent.getStringExtra("title");
        long id = intent.getLongExtra("id", 0);
        ImageLoader.LoadImage(this,img,evaluationImg,R.mipmap.ic_launcher);
        logisticsTv.setText(title);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarGo carGo = carGoDao.find(id);
                carGo.setEvaluate(true);
                carGoDao.upDataData(carGo);
                EventBus.getDefault().post("335");
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
