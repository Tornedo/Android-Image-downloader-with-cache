package com.mv.fetcher.json;

import android.content.Context;

public class JsonFetcher {

    public static JsonRequestManager with(Context context) {
        return new JsonRequestManager(context);
    }
}
