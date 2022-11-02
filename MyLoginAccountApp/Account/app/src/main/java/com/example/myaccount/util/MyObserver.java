package com.example.myaccount.util;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public class MyObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MyObserver(Handler handler) {
        super(handler);
    }

    @Override

    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

    }
}
