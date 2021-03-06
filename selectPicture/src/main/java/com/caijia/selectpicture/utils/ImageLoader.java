package com.caijia.selectpicture.utils;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by cai.jia on 2017/6/22 0022
 */

public class ImageLoader {

    private static volatile ImageLoader instance;

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public void loadImage(String url, ImageView imageView,@DrawableRes int defaultResId,int width,
                          int height) {
        if (width <= 0 || height <= 0) {
            loadImage(url, imageView, defaultResId);
            return;
        }
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .error(defaultResId)
                        .placeholder(defaultResId)
                        .fallback(defaultResId)
                        .override(width, height)
                        .dontAnimate())
                .into(imageView);
    }

    public void loadImage(String url, ImageView imageView,@DrawableRes int defaultResId) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .error(defaultResId)
                        .placeholder(defaultResId)
                        .fallback(defaultResId)
                        .dontAnimate())
                .into(imageView);
    }

    public void loadImage(String url, ImageView imageView,boolean useCache,@DrawableRes int defaultResId) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .error(defaultResId)
                .placeholder(defaultResId)
                .fallback(defaultResId)
                .dontAnimate();

        if (!useCache) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true);
        }

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
