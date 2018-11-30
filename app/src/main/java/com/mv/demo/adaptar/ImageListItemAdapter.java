package com.mv.demo.adaptar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mv.demo.R;
import com.mv.demo.model.apiResponse.ImageListResponse;
import com.mv.demo.util.interfaces.ListingContract;
import com.mv.fetcher.MVFetcher;

import java.util.ArrayList;
import java.util.List;


public class ImageListItemAdapter extends RecyclerView.Adapter<ImageViewHolder> {


    private static final String TAG = ImageListItemAdapter.class.getSimpleName();
    private ListingContract.Presenter presenter;
    private List<ImageListResponse> items;
    private Context context;


    public ImageListItemAdapter(ListingContract.Presenter presenter, List<ImageListResponse> items, Context context) {
        this.presenter = presenter;
        this.items = items;
        this.context = context;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
        return new ImageViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageListResponse item = items.get(position);
        MVFetcher.image(context).load(item.urls.regular).into(holder.listingItemImageView);
        holder.itemView.setOnClickListener(view -> presenter.onItemClicked(item, context, holder.listingItemImageView));
    }



    /**
     * Add items to the adaptar
     *
     * @param items
     */
    public void putItems(List<ImageListResponse> items) {
        if (this.items == null)
            this.items = new ArrayList<>();
        this.items.addAll(items);
    }


    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.size();
    }


    /**
     * remove all item from list.
     */
    public void clear() {
        if (items != null) {
            items.clear();
        }
    }
}
