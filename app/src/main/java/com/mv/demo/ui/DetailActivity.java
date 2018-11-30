package com.mv.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mv.demo.R;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.presenter.ImageDetailPresenter;
import com.mv.demo.view.ImageDetailView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setUpDetail();
    }


    private void setUpDetail() {
        ImageDetailView view = new ImageDetailView();
        ImageDetailPresenter presenter = new ImageDetailPresenter();

        view.setPresenter(presenter);
        presenter.setView(view);

        ImageListResponse item = getIntent().getParcelableExtra("item");
        presenter.setItem(item);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.detail_frame, view)
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
