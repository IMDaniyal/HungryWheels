package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class picsaveThread extends AsyncTask {

    Context c;
    String path;
    String username;
    UserTable u;

    public picsaveThread(Context c, String path, String username) {
        this.c = c;
        this.path = path;
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String id = myRef.push().getKey();

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        u=myDb.userDao().findByUsername(username);
        u.setPicflag("1");
        u.setPic(path);
        myDb.userDao().delete(u);
        myDb.userDao().insertAll(u);
        myRef.child(id).setValue(u);



        return null;
    }
}
