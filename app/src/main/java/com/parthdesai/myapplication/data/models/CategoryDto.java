package com.parthdesai.myapplication.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryDto implements Parcelable {

    @SerializedName("title")
    private String title;

    protected CategoryDto(Parcel in) {
        title = in.readString();
    }

    public static final Creator<CategoryDto> CREATOR = new Creator<CategoryDto>() {
        @Override
        public CategoryDto createFromParcel(Parcel in) {
            return new CategoryDto(in);
        }

        @Override
        public CategoryDto[] newArray(int size) {
            return new CategoryDto[size];
        }
    };

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
    }
}
