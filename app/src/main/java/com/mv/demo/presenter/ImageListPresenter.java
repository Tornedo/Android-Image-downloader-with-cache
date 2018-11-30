package com.mv.demo.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.widget.ImageView;

import com.mv.demo.R;
import com.mv.demo.application.MvApplication;
import com.mv.demo.interactor.ImageListInteractor;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.ui.DetailActivity;
import com.mv.demo.util.NetworkUtility;
import com.mv.demo.util.interfaces.ListingContract;

import java.util.List;


public class ImageListPresenter implements ListingContract.Presenter {


    private static final String TAG = ImageListPresenter.class.getSimpleName();
    private ListingContract.View view;
    private ImageListInteractor model;
    private Context context;


    @Override
    public void setView(ListingContract.View view) {
        this.view = view;
    }


    @Override
    public void start() {
        setUpContext();
        setUpModel();
        loadItems();
    }


    private void setUpContext() {
        this.context = MvApplication.getContext();
    }


    private void setUpModel() {
        model = new ImageListInteractor();
    }


    @Override
    public boolean isInternetAvailable() {
        return NetworkUtility.isInternetAvailable();
    }


    @Override
    public void loadItems() {
        if (isInternetAvailable()) {
            view.showLoadingIndicator();
            model.loadItems(this);
        } else
            onNoInternet();
    }


    @Override
    public void onItemsLoaded(List<ImageListResponse> items) {
        view.hideLoadingIndicator();
        view.setItems(items);
    }


    @Override
    public void onNoInternet() {
        view.hideLoadingIndicator();
        view.onErrorAndNoInternet(context.getString(R.string.no_internet));
    }


    @Override
    public void onError(Throwable t) {
        view.hideLoadingIndicator();
        view.onErrorAndNoInternet(context.getString(R.string.error));
        Log.e(TAG, "Error while loading data in listing: " + t.getMessage());
    }


    @Override
    public void onItemClicked(ImageListResponse item, Context context, ImageView imageView) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("item", item);
        Bundle animationBundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imageView, "image").toBundle();
        context.startActivity(intent, animationBundle);
    }


    @Override
    public void onLayoutRefresh() {
        loadItems();
    }
}
