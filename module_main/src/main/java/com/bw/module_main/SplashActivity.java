package com.bw.module_main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.utils.SpUtils;
import com.gyf.immersionbar.BarHide;
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
public class SplashActivity extends BaseActivity {
    private Handler handler = new Handler();
    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this)
                .titleBar(R.id.top_view)
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .init();
        handler.postDelayed(this::startToMain,3000);
    }



    private void startToMain(){
        boolean o = (boolean) SpUtils.getInstance("isFirst", MODE_PRIVATE, this).get("isfirst", true);
        if (o){
            startActivity(new Intent(this,GuideActivity.class));
        }else {
            MainActivity.Companion.start(this);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
