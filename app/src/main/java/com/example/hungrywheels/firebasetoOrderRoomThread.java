package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;

class firebasetoOrderRoomThread extends AsyncTask {

    Context c;
    OrderListTable u;
    public firebasetoOrderRoomThread(Context c, OrderListTable u) {
        this.c=c;
        this.u=u;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if (myDb.orderDao().countorders(u.getUsername(),u.getOrdername())>0)
        {
            myDb.orderDao().delete(u);
            myDb.orderDao().insertAll(u);
        }
        else
        {
            myDb.orderDao().insertAll(u);
        }
        return null;
    }
}
