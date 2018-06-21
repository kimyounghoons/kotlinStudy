package com.study.kotlin.kotlinstudy.binders;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

final public class DataBinder {
    private DataBinder(){}

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
