package com.example.hungrywheels;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserTable> getAll();

    @Query("SELECT * FROM user where username LIKE  :name")
    UserTable findByUsername(String name);

    @Query("SELECT COUNT(*) from user WHERE username = :usrmail")
    int countUsers(String usrmail);

    @Query("SELECT COUNT(*) from user WHERE email = :email")
    int emailSearch(String email);

    @Query("SELECT COUNT(*) from user WHERE email = :email AND fblogin = :facebook")
    int facebookSearch(String email,String facebook);

    @Query("UPDATE user SET fblogin = :facebook WHERE email = :email")
    int updateFB(String email, String facebook);

    @Query("SELECT COUNT(*) from user WHERE username = :usrmail AND password = :pas")
    int search(String usrmail, String pas);

    @Insert
    void insertAll(UserTable... users);

    @Delete
    void delete(UserTable user);

}
