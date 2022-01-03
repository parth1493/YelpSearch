package com.parthdesai.myapplication.data;

import androidx.annotation.NonNull;

import com.parthdesai.myapplication.BuildConfig;
import com.parthdesai.myapplication.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpRetrofit {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(new ApiKeyInterceptor())
                            .build())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static YelpAPI yelpAPI = retrofit.create(YelpAPI.class);

    public static YelpAPI getYelpAPI() {
        return yelpAPI;
    }

    private static class ApiKeyInterceptor implements Interceptor {

        @Override
        public @NonNull
        Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " + BuildConfig.API_KEY)
                    .build());
        }
    }
}
