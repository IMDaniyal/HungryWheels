package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class facebookLoginThread extends AsyncTask {
    UserTable u;
    Context c;

    public facebookLoginThread( Context c,UserTable u) {
        this.u = u;
        this.c = c;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Intent i= new Intent(c, exampleActivity.class);
        i.putExtra("username",u.getUsername());
        i.putExtra("email",u.getEmail());
        c.startActivity(i);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if(myDb.userDao().facebookSearch(u.getEmail(),u.getFacebook())>0)
        {

        }
        else if(myDb.userDao().emailSearch(u.getEmail())>0)
        {
            myDb.userDao().updateFB(u.getEmail(),"1");
        }
        else{
            myDb.userDao().insertAll(u);
        }


        return null;
    }
}
