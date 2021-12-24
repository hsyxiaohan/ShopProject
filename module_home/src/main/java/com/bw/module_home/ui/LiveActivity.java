package com.bw.module_home.ui;

import android.util.Log;
import android.view.View;

import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.module_home.R;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

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
public class LiveActivity extends BaseActivity {
    private android.widget.Button pullBtn;
    private TXCloudVideoView videoView;
    private TXLivePlayer mLivePlayer;
    private androidx.appcompat.widget.Toolbar homeLiveToolbar;
    private TXCloudVideoView pusherTxCloudView;
    private android.widget.Button pushBtn;
    private TXLivePusher mLivePusher;

    @Override
    public int bindLayout() {
        return R.layout.home_live_layout;
    }

    @Override
    public void initView() {
        pullBtn = findViewById(R.id.pull_btn);
        videoView = findViewById(R.id.video_view);
        homeLiveToolbar = findViewById(R.id.home_live_toolbar);
        pusherTxCloudView = findViewById(R.id.pusher_tx_cloud_view);
        pushBtn = findViewById(R.id.push_btn);
        setSupportActionBar(homeLiveToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeLiveToolbar.setNavigationIcon(R.mipmap.icon_back);
        homeLiveToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        //关键 player 对象与界面 view
        mLivePlayer.setPlayerView(videoView);
        pullBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flvUrl = "https://hsplay-360.v.btime.com/live_btime/btv_sn_20170706_s1/index.m3u8?time=1640223876&sign=32a27b876d111e4b94fafb5a31b5cc5b";
                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_HLS); //推荐 FLV
            }
        });

        TXLivePushConfig mLivePushConfig  = new TXLivePushConfig();
        mLivePusher = new TXLivePusher(this);
        // 一般情况下不需要修改 config 的默认配置
        mLivePusher.setConfig(mLivePushConfig);
        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePusher.startCameraPreview(pusherTxCloudView);
                String rtmpURL = "rtmp://3891.livepush.myqcloud.com/live/3891_user_ca7c5992_bb9e?bizid=3891&txSecret=9d986167f57ec1b80d7861f842e0ec31&txTime=61C3D9E0"; //此处填写您的 rtmp 推流地址
                int ret = mLivePusher.startPusher(rtmpURL.trim());
                if (ret == -5) {
                    LoggerUtils.i("startRTMPPush: license 校验失败");
                }else {
                    LoggerUtils.i("校检成功");
                }
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        videoView.onDestroy();
        mLivePusher.stopPusher();
        mLivePusher.stopCameraPreview(true); //如果已经启动了摄像头预览，请在结束推流时将其关闭
    }
}
