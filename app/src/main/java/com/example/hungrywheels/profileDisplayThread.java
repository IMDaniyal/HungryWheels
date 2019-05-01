package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileDisplayThread extends AsyncTask {
    Context c;
    TextView name;
    TextView email,phone,username;
    ImageView image;
    String user;
    UserTable u;
    boolean f=false;

    public profileDisplayThread(Context c, TextView name, TextView email, TextView phone, TextView username, ImageView image, String user) {
        this.c = c;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.image = image;
        this.user = user;
        u=new UserTable();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        u=myDb.userDao().findByUsername(user);
        if(u!=null)
        {
         f=true;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(f==true) {
            name.setText(u.getName());
            username.setText(u.getUsername());
            email.setText(u.getEmail());
            phone.setText(u.getPhone());
            Intent w= new Intent(c,HomeInterface.class);
            w.putExtra("username",user);
            c.startActivity(w);
        }
    }
}
