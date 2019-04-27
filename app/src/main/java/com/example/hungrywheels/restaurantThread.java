package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class restaurantThread extends AsyncTask {
    RestaurantTable rt;
    Context c;
    FragmentManager manager;

    public restaurantThread(RestaurantTable rt, Context c, FragmentManager manager) {
        this.rt = rt;
        this.manager=manager;
        this.c = c;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("restaurants");
        String id = myRef.push().getKey();

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        myDb.restaurantDao().insertAll(rt);
        myRef.child(id).setValue(rt);

        return null;
    }
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        login_fragment bdf = new login_fragment();
        manager.beginTransaction().replace(R.id.placeholder,bdf).commit();
    }
}
