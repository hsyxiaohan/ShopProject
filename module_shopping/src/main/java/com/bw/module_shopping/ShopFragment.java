package com.bw.module_shopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.goods.Goods;
import com.bw.library_common.router.goods.GoodsDao;
import com.bw.library_common.router.goods.GoodsDataBase;
import com.bw.library_common.router.router.ARouterFragmentPath;
import com.bw.library_common.router.utils.LoggerUtils;
import com.bw.library_pay.PayResult;
import com.bw.library_pay.util.OrderInfoUtil2_0;
import com.bw.module_shopping.adapter.GoodsAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Route(path = ARouterFragmentPath.Shopping.PAGER_SHOP)
public class ShopFragment extends BaseFragment {
    private static final String TAG = "Cannot invoke method length() on null object";
    private RecyclerView shopRv;
    private CheckBox shopCheckbox;
    private TextView shopPrice; 
    private Button payBtn;
    GoodsDao goodsDao;
    GoodsAdapter goodsAdapter;
    List<Goods> all;
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                shopRv.setAdapter(goodsAdapter);
            }
        }
    };
    private Toolbar shopToolbar;


    @Override
    public void onResume() {
        super.onResume();
        if (goodsAdapter != null){
            all = goodsDao.findAll();
            goodsAdapter.getData().clear();
            goodsAdapter.getData().addAll(all);
            goodsAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public int bindLayout() {
        return R.layout.shop_fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        shopRv = (RecyclerView) getViewById(R.id.shop_rv);
        shopCheckbox = (CheckBox) getViewById(R.id.shop_checkbox);
        shopPrice = (TextView) getViewById(R.id.shop_price);
        payBtn = (Button) getViewById(R.id.pay_btn);
        shopRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        goodsDao = GoodsDataBase.getGoodsDataBase(getActivity()).getGoodsDao();
        shopToolbar = (Toolbar) getViewById(R.id.shop_toolbar);
        getActivity().setActionBar(shopToolbar);

    }





    @Override
    public void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                all = goodsDao.findAll();
                goodsAdapter = new GoodsAdapter(all);
                handler.sendEmptyMessage(100);
                goodsAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                        if (view.getId() == R.id.item_checkbox) {
                            all.get(position).setIschecked(!all.get(position).isIschecked());
                            int a = 0;
                            for (int i = 0; i < all.size(); i++) {
                                if (all.get(i).isIschecked()) {
                                    a++;
                                }
                            }
                            if (a == all.size()) {
                                shopCheckbox.setChecked(true);
                            } else {
                                shopCheckbox.setChecked(false);
                            }
                            if (all.get(position).isIschecked()) {
                                //获取当前总价
                                int sumPrice = Integer.parseInt(shopPrice.getText().toString());
                                //获取数量和单价
                                TextView viewByPosition = (TextView) adapter.getViewByPosition(position, R.id.count);
                                int num = Integer.parseInt(viewByPosition.getText().toString());
                                Float price = Float.valueOf(all.get(position).getPrice());
                                sumPrice += num * price;
                                shopPrice.setText(String.valueOf(sumPrice));
                            } else {
                                //获取当前总价
                                int sumPrice = Integer.parseInt(shopPrice.getText().toString());
                                //获取数量和单价
                                TextView viewByPosition = (TextView) adapter.getViewByPosition(position, R.id.count);
                                int num = Integer.parseInt(viewByPosition.getText().toString());
                                float price = Float.parseFloat(all.get(position).getPrice());
                                sumPrice -= num * price;
                                shopPrice.setText(String.valueOf(sumPrice));
                            }
                        }
                        if (view.getId() == R.id.add) {
                            TextView viewByPosition = (TextView) adapter.getViewByPosition(position, R.id.count);
                            int num = Integer.parseInt(viewByPosition.getText().toString());
                            viewByPosition.setText(String.valueOf(num + 1));
                            Goods goods = goodsDao.find(all.get(position).getId());
                            goods.setCount(viewByPosition.getText().toString());
                            goodsDao.upDataData(goods);
                            LoggerUtils.i(goods.getCount());
                            if (all.get(position).isIschecked()) {
                                //获取当前总价
                                int sumPrice = Integer.parseInt(shopPrice.getText().toString());
                                Float price = Float.valueOf(all.get(position).getPrice());
                                sumPrice += price;
                                shopPrice.setText(String.valueOf(sumPrice));
                            }
                        }
                        if (view.getId() == R.id.jian) {
                            TextView viewByPosition = (TextView) adapter.getViewByPosition(position, R.id.count);
                            int num = Integer.parseInt(viewByPosition.getText().toString());
                            int num2 = num - 1;
                            if (num2 <= 0) {
                                return;
                            }
                            viewByPosition.setText(String.valueOf(num - 1));
                            Goods goods = goodsDao.find(all.get(position).getId());
                            goods.setCount(viewByPosition.getText().toString());
                            goodsDao.upDataData(goods);
                            if (all.get(position).isIschecked()) {
                                //获取当前总价
                                int sumPrice = Integer.parseInt(shopPrice.getText().toString());
                                Float price = Float.valueOf(all.get(position).getPrice());
                                sumPrice -= price;
                                shopPrice.setText(String.valueOf(sumPrice));
                            }
                        }
                    }
                });
            }
        }).start();
        shopCheckbox.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            @Override
            public void onClick(View v) {
                if (shopCheckbox.isChecked()) {
                    for (int i = 0; i < all.size(); i++) {
                        if (all.get(i).isIschecked()) {

                        } else {
                            all.get(i).setIschecked(true);
                        }
                        TextView viewByPosition = (TextView) goodsAdapter.getViewByPosition(i, R.id.count);
                        int num = Integer.parseInt(viewByPosition.getText().toString());
                        Float price = Float.valueOf(all.get(i).getPrice());
                        count += num * price;
                    }
                    shopPrice.setText(String.valueOf(count));
                } else {
                    for (int i = 0; i < all.size(); i++) {
                        if (all.get(i).isIschecked()) {
                            all.get(i).setIschecked(false);
                        }
                        count = 0;
                    }
                    shopPrice.setText(String.valueOf(count));
                }
                goodsAdapter.notifyDataSetChanged();
            }
        });
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggerUtils.i(111 + "");
                List<Goods> goodsDaoAll = goodsDao.findAll();
                List<ShopEntity> shopEntities = new ArrayList<>();
                for (int i = 0; i < all.size(); i++) {
                    if (ShopFragment.this.all.get(i).isIschecked()){
                        ShopEntity shopEntity = new ShopEntity(ShopFragment.this.all.get(i).getText(), ShopFragment.this.all.get(i).getPic(), ShopFragment.this.all.get(i).getPrice(), goodsDaoAll.get(i).getCount());
                        shopEntities.add(shopEntity);
                    }
                }
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("shopList", (Serializable) shopEntities);
                intent.putExtra("sumPrice",shopPrice.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true)
    public void getMsg(String msg){
        if (msg.equals("999")){
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).isIschecked()){
                    Goods goods = goodsDao.find(all.get(i).getId());
                    goodsDao.DeleteData(goods);
                    LoggerUtils.i(i+"");
                }
            }
            all = goodsDao.findAll();
            goodsAdapter.getData().clear();
            goodsAdapter.getData().addAll(all);
            goodsAdapter.notifyDataSetChanged();
            if (shopCheckbox.isChecked()){
                shopCheckbox.setChecked(false);
            }
            shopPrice.setText(String.valueOf(0));
        }
        if (msg.equals("1111")){
            all = goodsDao.findAll();
            goodsAdapter.getData().clear();
            goodsAdapter.getData().addAll(all);
            goodsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void hideLoading() {

    }
}
