package com.bw.module_mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.library_common.router.cargo.CarGo;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_mine.R;
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
public class ReceivingAdapter extends BaseQuickAdapter<CarGo, BaseViewHolder> {
    public ReceivingAdapter(@Nullable List<CarGo> data) {
        super(R.layout.mine_rececving_item_layout, data);
        addChildClickViewIds(R.id.mine_Receiving_item_look,R.id.mine_Receiving_item_affirm);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, CarGo carGo) {
        if (carGo.isReceiving()){

        }else {
            ImageLoader.LoadImage(getContext(),carGo.getPic(),baseViewHolder.getView(R.id.mine_Receiving_item_img),R.mipmap.ic_launcher);
            baseViewHolder.setText(R.id.mine_Receiving_item_tv,carGo.getTitle());
        }
    }
}
