package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;

public class GetUserThread extends AsyncTask {
    Context c;
    String username;
    UserTable user;
    Intent w;
    ViewPager pager;
    HomeInterface k;

    GetUserThread(Context con, String username, Intent w, ViewPager pager,HomeInterface k) {
        c = con;
        this.username=username;
        this.w=w;
        this.pager=pager;
        this.k=k;

    }


    @Override
    protected Object doInBackground(Object[] objects) {


        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        user=myDb.userDao().findByUsername(username);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if(user!=null){

            w.putExtra("user",user);

            MyPagerAdapter adapter = new MyPagerAdapter(k.getSupportFragmentManager());
            pager.setAdapter(adapter);


        }


    }
}
