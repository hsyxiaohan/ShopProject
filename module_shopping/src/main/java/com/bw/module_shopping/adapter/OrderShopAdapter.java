package com.bw.module_shopping.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_shopping.R;
import com.bw.module_shopping.ShopEntity;
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
class OrderShopAdapter extends BaseQuickAdapter<ShopEntity, BaseViewHolder> {
    public OrderShopAdapter( @Nullable List<ShopEntity> data) {
        super(R.layout.order_shop_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ShopEntity shopEntity) {
        ImageLoader.LoadImage(getContext(),shopEntity.getPic(),baseViewHolder.getView(R.id.shop_item_img),R.mipmap.ic_launcher);
        baseViewHolder.setText(R.id.shop_item_tv,shopEntity.getTitle());
        baseViewHolder.setText(R.id.shop_item_price,"￥"+shopEntity.getPrice());
        baseViewHolder.setText(R.id.shop_item_count,"x"+shopEntity.getCount());
    }
}
