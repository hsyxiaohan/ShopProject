package com.bw.module_classify.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.module_classify.R;
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
class ClassTextAdapter extends BaseQuickAdapter<ClassTextBean.DataBean, BaseViewHolder> {
    int index = 0;
    public ClassTextAdapter(@Nullable List<ClassTextBean.DataBean> data) {
        super(R.layout.class_text_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ClassTextBean.DataBean dataBean) {
        TextView view = baseViewHolder.getView(R.id.text_tv);
        if (index == 0){
            view.setTextColor(Color.RED);
            index++;
        }
        baseViewHolder.setText(R.id.text_tv,dataBean.getCategory_name());
    }
}
