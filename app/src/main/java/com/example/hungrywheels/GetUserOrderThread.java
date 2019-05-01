package com.example.hungrywheels;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetUserOrderThread extends AsyncTask {


    Context c;
    List<OrderListTable> alpha;
    RecyclerView beta;
    UserOrders ob;
    GetUserOrderThread(Context con,RecyclerView alpha1,UserOrders ob) {
        c = con;
        this.beta=alpha1;
        this.ob=ob;

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        alpha=myDb.orderDao().GetAllOrders(ob.getActivity().getIntent().getExtras().getString("username"));

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(alpha!=null){


            beta.setLayoutManager(new LinearLayoutManager(c));

            beta.addOnItemTouchListener(ob);
            beta.setItemAnimator(new DefaultItemAnimator());
            OrderAdapter adapter=new OrderAdapter(c,alpha);
            beta.setAdapter(adapter);
        }

    }
}
