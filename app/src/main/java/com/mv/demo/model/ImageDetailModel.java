package com.mv.demo.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mv.demo.util.interfaces.DetailContract;
import com.mv.fetcher.MVFetcher;

import java.util.List;


public class ImageDetailModel {


    public void loadImage(DetailContract.Presenter presenter, Context context, String url) {
        MVFetcher.image(context)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        presenter.onImageLoaded(resource);
                    }
                });
    }


}
