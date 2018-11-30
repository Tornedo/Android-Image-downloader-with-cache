package com.mv.demo.presenter;


public interface BasePresenter<T> {

    void setView(T view);

    void start();
}
