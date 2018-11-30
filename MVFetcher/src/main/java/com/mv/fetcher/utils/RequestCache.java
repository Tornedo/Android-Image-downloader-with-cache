package com.mv.fetcher.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestCache {
    private static RequestCache mInstance;

    private RequestQueue mRequestQueue;

    private RequestCache(@NonNull Context context) {
        mRequestQueue = getRequestQueue(context);
    }

    public static synchronized RequestCache getInstance(@NonNull Context context) {
        if (mInstance == null) {
            mInstance = new RequestCache(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue(@NonNull Context context) {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(@NonNull Context context, @NonNull Request<T> request) {
        getRequestQueue(context).add(request);
    }
}
