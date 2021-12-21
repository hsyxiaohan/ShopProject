package com.bw.module_home.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_home.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class SpecialAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public SpecialAdapter( @Nullable List<String> data) {
        super(R.layout.home_rv_one_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
        ImageLoader.LoadImage(getContext(),s,baseViewHolder.getView(R.id.rv_one_img),R.mipmap.ic_launcher);
        baseViewHolder.setText(R.id.rv_one_title,"￥2634.00");
        baseViewHolder.setText(R.id.rv_one_money,"$1000.00");
        TextView money = baseViewHolder.getView(R.id.rv_one_money);
        money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
