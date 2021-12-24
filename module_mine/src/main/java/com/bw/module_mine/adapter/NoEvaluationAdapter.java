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
public class NoEvaluationAdapter extends BaseQuickAdapter<CarGo, BaseViewHolder> {
    public NoEvaluationAdapter(@Nullable List<CarGo> data) {
        super(R.layout.noevaluation_item_layout, data);
        addChildClickViewIds(R.id.mine_noevaluation_item_evaluation);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, CarGo carGo) {
        baseViewHolder.setText(R.id.mine_noevaluation_item_tv,carGo.getTitle());
        ImageLoader.LoadImage(getContext(),carGo.getPic(),baseViewHolder.getView(R.id.mine_noevaluation_item_img),R.mipmap.ic_launcher);
    }
}
