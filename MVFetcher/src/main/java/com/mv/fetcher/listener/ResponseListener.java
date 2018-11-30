package com.mv.fetcher.listener;


public interface ResponseListener<T> {
    void onSuccess(T response);

    void onFailed(String cause);
}
