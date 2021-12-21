package com.bw.module_home.adapter;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bw.library_common.router.utils.ImageLoader;
import com.bw.module_home.R;
import com.bw.module_home.bean.BannerBean;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import okhttp3.internal.Util;

public class BannerAdapter extends BaseBannerAdapter<BannerBean> {
    @Override
    protected void bindData(BaseViewHolder<BannerBean> holder, BannerBean data, int position, int pageSize) {
        holder.setImageResource(R.id.banner_img,data.getImagePath());
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_banner_item_layout;
    }
}
