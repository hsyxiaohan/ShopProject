package com.bw.library_common.router.utils;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageLoader {

    //加载图片
    public static void LoadImage(Context context, String path, ImageView imageView,int errorImage){
        Glide.with(context).load(path).error(errorImage).into(imageView);
    }

    //加载圆角图片
    public static void LoadRoundImage(Context context, String path,int roundSize,int errorImage,ImageView imageView){
        RoundedCorners roundedCorners = new RoundedCorners(roundSize);
        RequestOptions requestOptions = new RequestOptions().transform(new CenterCrop(), roundedCorners);
        Glide.with(context).applyDefaultRequestOptions(requestOptions).load(path).error(errorImage).into(imageView);
    }

    //加载圆形图片
    public static void LoadCircleImage(Context context,String path,int errorImage,ImageView imageView){
        Glide.with(context).load(path).error(errorImage).into(imageView);
    }

    //指定角圆角图片
    public static void loadRadImage(Context context, String path, int roundSize, RoundedCornersTransformation.CornerType cornerType,ImageView imageView){
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .apply(bitmapTransform(new RoundedCornersTransformation(roundSize, 0, cornerType)))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }





}
