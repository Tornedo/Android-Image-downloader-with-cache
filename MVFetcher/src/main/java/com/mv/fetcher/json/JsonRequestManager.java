package com.mv.fetcher.json;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.reflect.TypeToken;
import com.mv.fetcher.listener.ResponseAdapter;
import com.mv.fetcher.listener.ResponseListener;
import com.mv.fetcher.utils.RequestCache;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class JsonRequestManager {

    private static final int DEFAULT_MAX_SIZE = 250;
    private static final LruCache<String, Object> cache = new LruCache<>(DEFAULT_MAX_SIZE);

    private int method = Request.Method.GET;
    private JSONObject requestJsonObject;
    private JSONArray requestJsonArray;

    private final Context context;
    private final RequestCache requestCache;

    public JsonRequestManager(Context context) {
        this.context = context;
        this.requestCache = RequestCache.getInstance(context);
    }

    public static void setCacheMaxSize(int maxSize) {
        cache.resize(maxSize);
    }

    @NonNull
    public JsonRequestManager setRequestJsonObject(JSONObject requestJsonObject) {
        this.requestJsonObject = requestJsonObject;
        return this;
    }

    @NonNull
    public JsonRequestManager setRequestJsonArray(JSONArray requestJsonArray) {
        this.requestJsonArray = requestJsonArray;
        return this;
    }

    @NonNull
    public JsonRequestManager setRequestMethod(int method) {
        this.method = method;
        return this;
    }

    public <E> void loadJsonObject(@NonNull final String url, Class<E> classOf, @NonNull final ResponseListener<E> listener) {
        E cacheResponse = (E) cache.get(url);

        if (cacheResponse != null) {
            listener.onSuccess(cacheResponse);
            return;
        }

        Type type = TypeToken.get(classOf).getType();

        ResponseAdapter<JSONObject, E> adapter = new ResponseAdapter<>(type, new ResponseListener<E>() {
            @Override
            public void onSuccess(@NonNull E response) {
                cache.put(url, response);
                listener.onSuccess(response);
            }

            @Override
            public void onFailed(String cause) {
                listener.onFailed(cause);
            }
        });

        JsonRequest jsonRequest = new JsonObjectRequest(
                method,
                url,
                requestJsonObject,
                adapter,
                adapter
        );

        requestCache.addToRequestQueue(context, jsonRequest);
    }


    @SuppressWarnings("unchecked")
    public <E> void loadJsonArray(@NonNull final String url, Class<E> classOf, @NonNull final ResponseListener<List<E>> listener) {
        List<E> cacheResponse = (List<E>) cache.get(url);

        if (cacheResponse != null) {
            listener.onSuccess(cacheResponse);
            return;
        }

        Type listType = TypeToken.getParameterized(List.class, classOf).getType();

        ResponseAdapter<JSONArray, List<E>> adapter = new ResponseAdapter<>(listType, new ResponseListener<List<E>>() {

            @Override
            public void onSuccess(@NonNull List<E> response) {
                cache.put(url, response);
                listener.onSuccess(response);
            }

            @Override
            public void onFailed(String cause) {
                listener.onFailed(cause);
            }
        });

        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                method,
                url,
                requestJsonArray,
                adapter,
                adapter
        );

        requestCache.addToRequestQueue(context, jsonRequest);
    }
}
