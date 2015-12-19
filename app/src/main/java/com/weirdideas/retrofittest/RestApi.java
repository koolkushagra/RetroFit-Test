package com.weirdideas.retrofittest;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by welcome on 12/19/2015.
 */
public interface RestApi {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestion (@Query("tagged") String tags);
}
