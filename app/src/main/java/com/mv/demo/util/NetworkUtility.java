package com.mv.demo.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.mv.demo.application.MvApplication;


public class NetworkUtility {


    /**
     * @return check user is connected to internet or not
     */
    public static boolean isInternetAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MvApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager != null && manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }
}
