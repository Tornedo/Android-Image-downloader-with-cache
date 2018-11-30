package com.mv.demo.interactor;

import com.mv.demo.BuildConfig;
import com.mv.demo.application.MvApplication;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.util.interfaces.ListingContract;
import com.mv.fetcher.MVFetcher;
import com.mv.fetcher.listener.ResponseListener;

import java.util.List;


public class ImageListInteractor {

    // GET the base url from gradle wrapper.
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public void loadItems(ListingContract.Presenter presenter) {
        MVFetcher.json(MvApplication.getContext())
                .loadJsonArray(BASE_URL, ImageListResponse.class, new ResponseListener<List<ImageListResponse>>() {

                    @Override
                    public void onSuccess(List<ImageListResponse> response) {
                        presenter.onItemsLoaded(response);
                    }

                    @Override
                    public void onFailed(String cause) {
                        presenter.onError(new Exception(cause));
                    }
                });
    }
}
