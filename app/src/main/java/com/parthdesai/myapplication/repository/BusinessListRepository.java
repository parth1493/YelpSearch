package com.parthdesai.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parthdesai.myapplication.data.YelpAPI;
import com.parthdesai.myapplication.data.YelpRetrofit;
import com.parthdesai.myapplication.data.models.BusinessDto;
import com.parthdesai.myapplication.data.models.SearchResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BusinessListRepository {

    private static BusinessListRepository businessListRepository;
    private MutableLiveData<List<BusinessDto>> businesses;

    public static BusinessListRepository getInstance() {
        if (businessListRepository == null) {
            businessListRepository = new BusinessListRepository();
        }
        return businessListRepository;
    }

    private final YelpAPI yelpApi;

    public BusinessListRepository() {
        businesses = new MutableLiveData<>();
        yelpApi = YelpRetrofit.getYelpAPI();
    }

    public LiveData<List<BusinessDto>> getBusinesses() {
        return businesses;
    }

    public void fetchData(
            String term,
            String location
    ) {
        yelpApi.search(
                term,
                location
        ).enqueue(new Callback<SearchResponseDto>() {
            @Override
            public void onResponse(
                    @NonNull Call<SearchResponseDto> call,
                    @NonNull Response<SearchResponseDto> response
            ) {
                if (response.code() == 200) {
                    if(response.body().getBusinesses() != null) {
                        businesses.postValue(response.body().getBusinesses());
                        Timber.d("Response %s", response.body().getBusinesses());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponseDto> call, Throwable t) {
                businesses.postValue(null);
            }
        });
    }
}
