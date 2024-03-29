package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
        if (myDb.userDao().countUsers(u.getUsername())>0)
        {
            myDb.userDao().delete(u);
            myDb.userDao().insertAll(u);
        }
        else
        {
            myDb.userDao().insertAll(u);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }
}
