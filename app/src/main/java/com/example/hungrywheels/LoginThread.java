package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginThread extends AsyncTask {

    Context c;
    Intent w;
    String username;
    String password;

    boolean flag = false;


    LoginThread(Context con, String username, String password, TextView txt) {
        c = con;

        this.username = username;
        this.password = password;

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        if (myDb.userDao().search(username, password) > 0) {

            flag = true;
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (flag) {
            Toast.makeText(c, "LOGIN Succesfully", Toast.LENGTH_SHORT).show();
            Intent w= new Intent(c,exampleActivity.class);
            w.putExtra("username",username);

            c.startActivity(w);
        } else {
            Toast.makeText(c, "Enter Correct Credentials", Toast.LENGTH_SHORT).show();

        }
    }
}