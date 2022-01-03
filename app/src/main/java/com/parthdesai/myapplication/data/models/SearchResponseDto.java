package com.parthdesai.myapplication.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponseDto {
    @SerializedName("businesses")
    private List<BusinessDto> businesses;

    public List<BusinessDto> getBusinesses() {
        return businesses;
    }

    @Override
    public String toString() {
        return "SearchResponseDto{" +
                "businesses=" + businesses +
                '}';
    }
}
