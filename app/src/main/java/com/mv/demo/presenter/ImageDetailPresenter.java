package com.mv.demo.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.mv.demo.application.MvApplication;

import com.mv.demo.model.ImageDetailModel;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.util.interfaces.DetailContract;



public class ImageDetailPresenter implements DetailContract.Presenter {


    private static final String TAG = ImageDetailPresenter.class.getSimpleName();
    private DetailContract.View view;
    private ImageDetailModel model;
    private Context context;
    private ImageListResponse item;


    @Override
    public void setView(DetailContract.View view) {
        this.view = view;
    }


    @Override
    public void start() {
        if (item == null)
            return;

        setUpModel();
        setUpContext();

        setImage();

        setUserFullName();
    }


    private void setUpModel() {
        this.model = new ImageDetailModel();
    }


    private void setUpContext() {
        this.context = MvApplication.getContext();
    }


    @Override
    public void setItem(ImageListResponse item) {
        this.item = item;
    }


    @Override
    public void setImage() {
        model.loadImage(this, context, item.urls.regular);
    }


    @Override
    public void onImageLoaded(Drawable drawable) {
        view.setImage(drawable);
    }


    @Override
    public void setUserFullName() {
        view.setUserFullName(item.user.name);
    }
}
