package com.example.hungrywheels;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "orderlist")
public class OrderListTable {


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    @ColumnInfo(name="username")
    private String username;

    @NonNull
    @ColumnInfo(name = "ordername")
    private String ordername;


    @ColumnInfo(name = "droplocation")
    private String droplocation;


    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getOrdername() {
        return ordername;
    }

    public String getDroplocation() {
        return droplocation;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setOrdername(@NonNull String ordername) {
        this.ordername = ordername;
    }

    public void setDroplocation(String droplocation) {
        this.droplocation = droplocation;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
