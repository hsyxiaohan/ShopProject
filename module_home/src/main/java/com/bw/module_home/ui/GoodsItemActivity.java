package com.bw.module_home.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.library_base.view.BaseActivity;
import com.bw.library_common.router.goods.Goods;
import com.bw.library_common.router.goods.GoodsDao;
import com.bw.library_common.router.goods.GoodsDataBase;
import com.bw.library_common.router.router.ARouterActivityPath;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.library_common.router.utils.SpUtils;
import com.bw.module_home.R;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

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
public class GoodsItemActivity extends BaseActivity
{


    private androidx.appcompat.widget.Toolbar homeGoodsToolbar;
    private android.widget.ImageView homeGoodsItemImg;
    private android.widget.TextView homeGoodsItemTv;
    private android.widget.TextView homeGoodsItemShare;
    private com.ehenjoom.redspot.RedSpot homeGoodsItemRed;

    int page;

    GoodsDao goodsDao;
    private android.widget.Button homeGoodsItemShopBtn;
    private android.widget.TextView homeGoodsItemShop;

    @Override
    public int bindLayout() {
        return R.layout.home_goods_activity_layout;
    }

    @Override
    public void initView() {
        homeGoodsToolbar = findViewById(R.id.home_goods_toolbar);
        //初始化toolbar
        setSupportActionBar(homeGoodsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeGoodsToolbar.setNavigationIcon(R.mipmap.icon_back);
        homeGoodsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImmersionBar.with(this)
                .statusBarColor("#00a9f4")
                .applySystemFits(true)//解决状态栏和布局重叠问题
                .init();
        homeGoodsItemImg = findViewById(R.id.home_goods_item_img);
        homeGoodsItemTv = findViewById(R.id.home_goods_item_tv);
        homeGoodsItemShare = findViewById(R.id.home_goods_item_share);
        homeGoodsItemRed = findViewById(R.id.home_goods_item_red);

        goodsDao = GoodsDataBase.getGoodsDataBase(this).getGoodsDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Goods> all = goodsDao.findAll();
                homeGoodsItemRed.setText(String.valueOf(all.size()));
            }
        }).start();

        homeGoodsItemShopBtn = findViewById(R.id.home_goods_item_shop_btn);
        homeGoodsItemShop = findViewById(R.id.home_goods_item_shop);
    }



    @Override
    public  void initData() {
        Intent intent = getIntent();
        String pic = intent.getStringExtra("pic");
        String text = intent.getStringExtra("text");
        String price = intent.getStringExtra("price");
        ImageLoader.LoadImage(this,pic,homeGoodsItemImg,R.mipmap.ic_launcher);
        homeGoodsItemTv.setText(text);
        //添加购物车
        homeGoodsItemShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GoodsItemActivity.this);
                builder.setMessage("是否要加入购物车");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                goodsDao.insertData(new Goods(null,pic,text,price,"1"));
                                List<Goods> all = goodsDao.findAll();
                                homeGoodsItemRed.setText(String.valueOf(all.size()));
                                homeGoodsItemRed.postInvalidate();
                            }
                        }).start();
                    }
                });
                builder.create().show();
            }
        });
        //跳到购物车页面
        homeGoodsItemShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("2");
//                ARouter.getInstance().build(ARouterActivityPath.Main.PAGER_MAIN).navigation();
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
