package com.mv.fetcher;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.RequestManager;
import com.mv.fetcher.json.JsonFetcher;
import com.mv.fetcher.json.JsonRequestManager;

public class MVFetcher {

    //================================================================================
    // Json Section
    //================================================================================

    public static JsonRequestManager json(Context context) {
        return JsonFetcher.with(context);
    }

    public static void setJsonCacheMaxSize(int maxSize) {
        JsonRequestManager.setCacheMaxSize(maxSize);
    }

    //================================================================================
    // Image Section
    //================================================================================

    public static RequestManager image(@NonNull Context context) {
        return Glide.with(context);
    }

    public static void setImageCacheCategory(@NonNull Context context, @NonNull MemoryCategory category) {
        Glide.get(context).setMemoryCategory(category);
    }
}
