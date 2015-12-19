package com.weirdideas.retrofittest;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by welcome on 12/20/2015.
 */
public class RestApiClient {
    private static RestApiClient ourInstance = new RestApiClient();


    public RestApi getApi() {
        return mApi;
    }

    public void setmApi(RestApi mApi) {
        this.mApi = mApi;
    }

    private RestApi mApi;

    Retrofit retrofit;

    public static RestApiClient getInstance() {
        return ourInstance;
    }

    private RestApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(RestApi.class);
    }
}
