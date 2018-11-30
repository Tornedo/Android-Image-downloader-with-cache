package com.mv.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mv.demo.R;
import com.mv.demo.view.ImageListView;
import com.mv.demo.presenter.ImageListPresenter;

public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        setupListing();
    }


    /**
     * Set up the listing view and presenter
     */
    private void setupListing() {
        ImageListView view = new ImageListView();
        ImageListPresenter presenter = new ImageListPresenter();

        view.setPresenter(presenter);
        presenter.setView(view);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.listing_frame, view)
                .commit();
    }
}
