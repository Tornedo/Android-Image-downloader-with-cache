package com.mv.demo.util.interfaces;

import android.content.Context;
import android.widget.ImageView;

import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.presenter.BasePresenter;
import com.mv.demo.view.BaseView;

import java.util.List;


public interface ListingContract {

    interface View extends BaseView<Presenter> {

        void setUpViews();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void setItems(List<ImageListResponse> items);

        void onErrorAndNoInternet(String message);

        void goUpListing();

        void onLayoutRefresh();
    }


    interface Presenter extends BasePresenter<View> {

        boolean isInternetAvailable();

        void loadItems();

        void onItemsLoaded(List<ImageListResponse> items);

        void onNoInternet();

        void onError(Throwable t);

        void onItemClicked(ImageListResponse item, Context context, ImageView imageView);

        void onLayoutRefresh();
    }
}
