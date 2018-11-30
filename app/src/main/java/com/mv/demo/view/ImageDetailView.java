package com.mv.demo.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mv.demo.R;
import com.mv.demo.util.interfaces.DetailContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailView extends Fragment implements DetailContract.View {


    @BindView(R.id.detailImage)
    ImageView detailImageView;

    private DetailContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        presenter.start();
        return rootView;
    }


    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setImage(Drawable drawable) {
        detailImageView.setImageDrawable(drawable);
    }


    @Override
    public void setUserFullName(String fullName) {

    }
}
