package com.example.hungrywheels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginThread extends AsyncTask {

    Context c;
    Intent w;
    String username;
    String password;
    boolean flag = false;


    LoginThread(Context con, String username, String password) {
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
            //w.putExtra("username",username);
            //w.putExtra("password",password);
            c.startActivity(w);
        } else {
            Toast.makeText(c, "Enter Correct Credentials", Toast.LENGTH_SHORT).show();

        }
    }
}