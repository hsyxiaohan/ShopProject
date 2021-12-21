package com.bw.module_main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.utils.LoggerUtils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.zhpan.indicator.enums.IndicatorSlideMode;

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
public
class GuideActivity extends BaseActivity {
    private com.zhpan.bannerview.BannerViewPager bannerVp;
    private android.widget.TextView tvDescribe;
    private android.widget.TextView btnStart;
    private String[] strings = new String[]{"在这里\n你可以买到你想买的东西", "在这里\n购物是心情愉快的选择", "在这里\n不再错过可以改变你一生的商品"};
    private List<CustomBean> data;


    @Override
    public int bindLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        bannerVp = findViewById(R.id.banner_vp);
        tvDescribe = findViewById(R.id.tv_describe);
        btnStart = findViewById(R.id.btn_start);
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
    }

    @Override
    public void initData() {
        data = addDrawable();
        setupViewPager();
        updateUI(0);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Companion.start(GuideActivity.this);
            }
        });
    }

    private void setupViewPager(){
        bannerVp.setCanLoop(false)//是否开启循环
                .setPageTransformer(new RotateUpTransformer())//设置页面Transformer样式
                .setIndicatorMargin(0,0,0, (int) getResources().getDimension(R.dimen.dp_100))//设置指示器边距
                .setIndicatorSliderGap((int) getResources().getDimension(R.dimen.dp_10))//指示器圆点边距
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)//设置指示器滑动模式
                .setIndicatorSliderRadius((int) getResources().getDimension(R.dimen.dp_3),(int)getResources().getDimension(R.dimen.dp_4_5))//设置指示器圆点半径
                //页面改变监听事件
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        updateUI(position);
                        LoggerUtils.i(position+"");
                    }
                })
        .setAdapter(new SimpleAdapter())//设置适配器
        .setIndicatorSliderColor(Color.BLACK, ContextCompat.getColor(this,R.color.black_alpha))//设置指示器颜色
        .create(data);

    }

    //更新画面执行的操作
    public void updateUI(int position){
        tvDescribe.setText(strings[position]);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(tvDescribe, "translationX", -120f, 0f);
        translationX.setDuration(1300).setInterpolator(new DecelerateInterpolator());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tvDescribe, "alpha", 0f, 1f);
        alpha.setDuration(1300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX,alpha);
        animatorSet.start();
        if (position == bannerVp.getData().size() - 1 && btnStart.getVisibility() == View.GONE){
            btnStart.setVisibility(View.VISIBLE);
            LoggerUtils.i("222");
            ObjectAnimator alpha1 = ObjectAnimator.ofFloat(btnStart, "alpha", 0, 1);
            ObjectAnimator translationX1 = ObjectAnimator.ofFloat(btnStart, "translationY", 120f, 0f);
            alpha1.setDuration(1300);
            translationX1.setDuration(1300);
            translationX1.setInterpolator(new DecelerateInterpolator());
            AnimatorSet animatorSet1 = new AnimatorSet();
            animatorSet1.playTogether(translationX1,alpha1);
            animatorSet1.start();
        }else {
            LoggerUtils.i("111");
            btnStart.setVisibility(View.GONE);
        }
    }

    public List<CustomBean> addDrawable(){
        List<CustomBean> list = new ArrayList<>();
        CustomBean customBean = new CustomBean(R.drawable.guide0, strings[0]);
        list.add(customBean);
        CustomBean customBea2 = new CustomBean(R.drawable.guide1, strings[1]);
        list.add(customBea2);
        CustomBean customBea3 = new CustomBean(R.drawable.guide2, strings[2]);
        list.add(customBea3);
        return list;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
