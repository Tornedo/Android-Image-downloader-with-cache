package com.mv.demo.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mv.demo.R;
import com.mv.demo.adaptar.ImageListItemAdapter;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.ui.ImageListActivity;
import com.mv.demo.util.EndlessRecyclerViewScrollListener;
import com.mv.demo.util.interfaces.ListingContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListView extends Fragment implements ListingContract.View {


    private static final int FAB_ANIMATION_SENSITIVITY = 0;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout listingSwipeRefreshLayout;

    @BindView(R.id.imageRecylerView)
    RecyclerView listingRecyclerView;

    @BindView(R.id.loading)
    ProgressBar loadingProgressBar;


    private Activity activity;
    private ImageListItemAdapter listingAdapter;
    private Snackbar snackbar;
    private ListingContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
        ButterKnife.bind(this, rootView);
        presenter.start();
        setUpViews();
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ImageListActivity) context;
    }


    @Override
    public void setPresenter(ListingContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setUpViews() {
        listingAdapter = new ImageListItemAdapter(presenter, null, getContext());
        listingRecyclerView.setAdapter(listingAdapter);
        LinearLayoutManager listingLayoutManager = new LinearLayoutManager(getContext());
        listingRecyclerView.setLayoutManager(listingLayoutManager);
        listingRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(listingLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadItems();
            }

            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                super.onScrolled(view, dx, dy);
            }
        });

        listingSwipeRefreshLayout.setOnRefreshListener(this::onLayoutRefresh);
    }


    @Override
    public void goUpListing() {
        if (listingRecyclerView != null)
            listingRecyclerView.smoothScrollToPosition(0);
    }


    @Override
    public void onLayoutRefresh() {
        if (listingAdapter != null)
            listingAdapter.clear();
        presenter.onLayoutRefresh();
    }


    @Override
    public void showLoadingIndicator() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideLoadingIndicator() {
        loadingProgressBar.setVisibility(View.GONE);
        listingSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void setItems(List<ImageListResponse> items) {
        listingAdapter.putItems(items);
        listingAdapter.notifyDataSetChanged();
    }


    @Override
    public void onErrorAndNoInternet(String message) {
        if (snackbar != null && snackbar.isShown())
            snackbar.dismiss();
        snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, view -> presenter.loadItems());
        snackbar.show();
    }
}
