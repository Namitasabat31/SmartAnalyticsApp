package smartanalytics.diksha.com.smartanalytics.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.Constants;

/**
 * created by b0089798
 */

public class VolleySingleton {

    private static VolleySingleton sInstance=null;
    private RequestQueue mRequestQueue;
    private static RetryPolicy mDefaultRetryPolicy = null;
    private static RetryPolicy mLongRetryPolicy    = null;
    private ImageLoader mImageLoader;
  //  private InputStream keyStore;

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    private VolleySingleton(){

        mRequestQueue= Volley.newRequestQueue(SmartAppication.getAppContext());
      //  keyStore = PromoterApplication.getAppContext().getResources().openRawResource(R.raw.retailerapp);

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }
    public static VolleySingleton getInstance(){
        if(sInstance==null)
        {
            sInstance=new VolleySingleton();
        }
        return sInstance;
    }
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public static RetryPolicy getShortRetryPolicy() {
        if (mDefaultRetryPolicy == null) {
            mDefaultRetryPolicy = new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(Constants.QUICK_TIMEOUT),
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        return mDefaultRetryPolicy;
    }

    public static RetryPolicy getLongRetryPolicy() {
        if (mLongRetryPolicy == null) {
            mLongRetryPolicy = new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(Constants.DEFAULT_TIMEOUT),
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        return mLongRetryPolicy;
    }
}
