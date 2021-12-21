package com.bw.module_shopping.adapter;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.library_common.router.goods.Goods;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_shopping.R;
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
class GoodsAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {
    public GoodsAdapter( @Nullable List<Goods> data) {
        super(R.layout.shop_item, data);
        addChildClickViewIds(R.id.item_checkbox,R.id.add,R.id.jian);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Goods goods) {
        ImageLoader.LoadImage(getContext(),goods.getPic(),baseViewHolder.getView(R.id.good_item_img),R.mipmap.ic_launcher);
        baseViewHolder.setText(R.id.good_item_title,goods.getText());
        baseViewHolder.setText(R.id.count,goods.getCount().toString());
        baseViewHolder.setText(R.id.price,goods.getPrice().toString());
        CheckBox view = baseViewHolder.getView(R.id.item_checkbox);
        if (goods.isIschecked()){
            view.setChecked(true);
        }else {
            view.setChecked(false);
        }
    }
}
