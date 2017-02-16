package com.fjz.androidlittlesamples.databindingdemo;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by fjz on 16/02/2017.
 */

public class ImageUtils {

    @BindingAdapter("image")
    public static void imageUrl(ImageView iv, String url) {
        if (iv != null && url != null) {
            Glide.with(iv.getContext()).load(url).into(iv);
        }
    }
}
