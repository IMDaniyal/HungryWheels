package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FillOrderThread extends AsyncTask {


    Context c;
    String user;
    String food;
    Intent w;


    FillOrderThread(Context con, String user, String food, Intent w) {
        c = con;
        this.food=food;
        this.user=user;
        this.w=w;


    }


    @Override
    protected Object doInBackground(Object[] objects) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders");
        String id = myRef.push().getKey();
        OrderListTable alpha=new OrderListTable();
        alpha.setOrdername(food);
        alpha.setUsername(user);
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        myDb.orderDao().insertAll(alpha);
        myRef.child(id).setValue(alpha);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        c.getApplicationContext().startActivity(w);




    }


}
