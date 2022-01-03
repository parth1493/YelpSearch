package com.parthdesai.myapplication.data;

import com.parthdesai.myapplication.data.models.SearchResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {

    @GET("businesses/search")
    Call<SearchResponseDto> search(
            @Query("term") String term,
            @Query("location") String location
    );
}
