package com.mv.fetcher.listener;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;


public class ResponseAdapter<T, E> implements com.android.volley.Response.Listener<T>, com.android.volley.Response.ErrorListener {


    private static final String TAG = ResponseAdapter.class.getSimpleName();

    private final Gson gson;
    private final Type typeOf;
    private final ResponseListener<E> listener;

    public ResponseAdapter(Type typeOf, ResponseListener<E> listener) {
        this.gson = new Gson();
        this.typeOf = typeOf;
        this.listener = listener;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse", error);
        String cause;

        if (error instanceof NoConnectionError) {
            cause = "No Connection";
        } else {
            cause = error.getMessage();
        }

        Log.e(TAG, "cause : " + cause);
        listener.onFailed(cause);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onResponse(@NonNull T jsonResponse) {
        Log.d(TAG, jsonResponse.toString());

        try {
            if (typeOf == JSONObject.class || typeOf == JSONArray.class) {
                listener.onSuccess((E) jsonResponse);
            } else {
                listener.onSuccess((E) gson.fromJson(jsonResponse.toString(), typeOf));
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponse", e);
            onErrorResponse(new VolleyError(e));
        }
    }
}
