package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

public class firebasetoRoomThread extends AsyncTask {
    Context c;
    UserTable u;

    public firebasetoRoomThread(Context c, UserTable u) {
        this.c = c;
        this.u = u;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if (myDb.userDao().search(u.getUsername(), u.getPassword()) == 0 && myDb.userDao().facebookSearch(u.getEmail(),u.getFacebook())==0)
        {
            myDb.userDao().insertAll(u);
        }
        return null;
    }
}
