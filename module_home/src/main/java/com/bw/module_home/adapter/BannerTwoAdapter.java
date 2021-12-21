package com.bw.module_home.adapter;

import com.blankj.utilcode.util.Utils;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_home.R;
import com.bw.module_home.bean.BannerBean;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BannerTwoAdapter extends BaseBannerAdapter<String> {


    @Override
    protected void bindData(BaseViewHolder<String> holder, String data, int position, int pageSize) {
        ImageLoader.LoadRoundImage(Utils.getApp(),data,50,R.mipmap.ic_launcher,holder.findViewById(R.id.banner_img));
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_banner_item_two_layout;
    }
}
