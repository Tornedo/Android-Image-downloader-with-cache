package com.mv.demo.util.interfaces;

import android.graphics.drawable.Drawable;

import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.presenter.BasePresenter;
import com.mv.demo.view.BaseView;


public interface DetailContract {


    interface View extends BaseView<Presenter> {

        void setImage(Drawable drawable);

        void setUserFullName(String fullName);

    }

    interface Presenter extends BasePresenter<View> {

        void setItem(ImageListResponse item);

        void setImage();

        void onImageLoaded(Drawable drawable);


        void setUserFullName();


    }
}
