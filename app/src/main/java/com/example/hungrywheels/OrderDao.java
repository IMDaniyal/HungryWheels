package com.example.hungrywheels;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM orderlist")
    List<OrderListTable> getAll();

    @Query("SELECT * FROM orderlist where username LIKE  :username AND ordername LIKE :order")
    OrderListTable findByName(String username, String order);

    @Query("SELECT COUNT(*) from orderlist where username LIKE:username")
    int countorders(String username);

    @Insert
    void insertAll(OrderListTable... entry);

    @Delete
    void delete(OrderListTable entry);


}
