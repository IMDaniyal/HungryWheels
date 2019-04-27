package com.example.hungrywheels;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {


    @Query("SELECT * FROM restaurant")
    List<RestaurantTable> getAll();

    @Query("SELECT * FROM restaurant where ordername LIKE  :ordername")
    RestaurantTable findByOrdername(String ordername);

    @Query("SELECT COUNT(*) from restaurant where restaurantname LIKE:name")
    int countordersofRestaurant(String name);

    @Query("SELECT COUNT(*) from restaurant")
    int countfood();

    @Insert
    void insertAll(RestaurantTable... entry);

    @Delete
    void delete(RestaurantTable entry);
}

