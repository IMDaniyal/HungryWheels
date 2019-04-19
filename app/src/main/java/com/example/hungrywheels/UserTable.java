package com.example.hungrywheels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user",primaryKeys = "username")
public class UserTable {

    @NonNull
    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="password")
    private int password;

    @ColumnInfo(name="age")
    private int age;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String lastName;

    @ColumnInfo(name = "phone")
    private String phone;


    public int getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
