package com.parthdesai.myapplication.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Business model from the Yelp v3 API.
 * Update this file to include any fields you feel are missing.
 * @see <a href=https://www.yelp.ca/developers/documentation/v3/business_search>Yelp API Business Search</a>
 */
public class BusinessDto implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<CategoryDto> categories;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("rating")
    private Double rating;

    protected BusinessDto(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        categories = (List<CategoryDto>) in.readValue(CategoryDto.class.getClassLoader());
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
    }

    public static final Creator<BusinessDto> CREATOR = new Creator<BusinessDto>() {
        @Override
        public BusinessDto createFromParcel(Parcel in) {
            return new BusinessDto(in);
        }

        @Override
        public BusinessDto[] newArray(int size) {
            return new BusinessDto[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeValue(categories);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
    }
}
