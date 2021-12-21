package com.bw.module_classify.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_classify.R;
import com.bw.module_classify.bean.ClassGoodsBean;
import com.bw.module_classify.bean.ClassTextBean;
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
class ClassGoodsAdapter extends BaseQuickAdapter<ClassGoodsBean.DataBean, BaseViewHolder> {


    public ClassGoodsAdapter( @Nullable List<ClassGoodsBean.DataBean> data) {
        super(R.layout.class_goods_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ClassGoodsBean.DataBean dataBean) {
        ImageLoader.LoadImage(getContext(),dataBean.getPictUrl(),baseViewHolder.getView(R.id.goods_img),R.mipmap.ic_launcher);
        baseViewHolder.setText(R.id.good_title,dataBean.getShopTitle());
    }
}
