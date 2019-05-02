package com.example.hungrywheels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "restaurant",primaryKeys = "ordername")
public class RestaurantTable implements Serializable {


    @NonNull
    @ColumnInfo(name="restaurantname")
    private String restaurantname;

    @NonNull
    @ColumnInfo(name = "ordername")
    private String ordername;


    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "review")
    private String review;


    @NonNull
    public String getRestaurantname() {
        return restaurantname;
    }

    @NonNull
    public String getOrdername() {
        return ordername;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public String getReview() {
        return review;
    }


    public void setRestaurantname(@NonNull String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public void setOrdername(@NonNull String ordername) {
        this.ordername = ordername;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
