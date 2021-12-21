package com.bw.module_home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_home.R;
import com.bw.module_home.bean.GoodsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

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
class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean, BaseViewHolder> {
    public GoodsAdapter( @Nullable List<GoodsBean.DataBean> data) {
        super(R.layout.home_goods_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, GoodsBean.DataBean goodsBean) {
        ImageLoader.LoadRoundImage(getContext(),goodsBean.getPictUrl(),40,R.mipmap.ic_launcher,baseViewHolder.getView(R.id.home_goods_img));
        baseViewHolder.setText(R.id.home_good_tv,goodsBean.getTitle());
    }
}
