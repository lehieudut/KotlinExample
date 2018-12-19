package jp.bizloco.kotlinexample.service.core;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import jp.bizloco.kotlinexample.service.ApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://maxwellvandervelde.com/blog/android-simulate-slow-network
 * Api client
 */
public class ApiClient {
    private static final String TAG = ApiClient.class.getSimpleName();
    private static final long TIME_OUT = 20000;
    private static final String AUTHORIZATION = "Apikey";
    private static final String AUTHORIZATION_TYPE = "Bearer ";
    private static ApiClient sInstance;
    private ApiService mApiService;

    /**
     * constructor
     */
    private ApiClient() {
        // no instance
    }

    /**
     * Api client
     *
     * @return
     */
    public synchronized static ApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new ApiClient();
        }
        return sInstance;
    }

    /**
     * Service
     *
     * @return
     */
    public synchronized static ApiService getService() {
        return getInstance().mApiService;
    }

    /**
     * init confirm url
     *
     */
    public void init() {
        // init
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        DoubleAdapter doubleAdapter = new DoubleAdapter();
        // init Gson
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .registerTypeAdapter(Double.class, doubleAdapter)
                .registerTypeAdapter(double.class, doubleAdapter)
                .setLenient()
                .disableHtmlEscaping()
                .create();
        // init OkHttpClient
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder();
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        // Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.interceptors().add(logInterceptor);

        // AUTHORIZATION
        Log.d(TAG, "intercept: " + Locale.getDefault().getISO3Language().substring(0, 2));
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept-Language", Locale.getDefault().getISO3Language().substring(0, 2))
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl("https://google.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mApiService = retrofit.create(ApiService.class);
    }
}
