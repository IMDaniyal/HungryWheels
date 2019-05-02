package com.example.hungrywheels;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeInterface extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username;
    ImageView image;
    TextView name;
    TextView email;
    int REQUEST_INVITE=10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_interface);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        Intent i=getIntent();
        username=i.getExtras().getString("username");



        ViewPager pager = findViewById(R.id.vp);

        name=headerView.findViewById(R.id.nav_user);
        email=headerView.findViewById(R.id.nav_email);
        image=headerView.findViewById(R.id.nav_imageview);
        new homeInterfaceNavigationUpdateThread(getApplicationContext(),name,email,image,username).execute();


        /*MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);*/

        GetUserThread getuser=new GetUserThread(this,getIntent().getExtras().getString("username"),getIntent(),pager,this);
        getuser.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent w= new Intent(getApplicationContext(),HomeInterface.class);
            w.putExtra("username",username);
            startActivity(w);
        }
        else if (id == R.id.nav_changepic) {
            Intent w= new Intent(getApplicationContext(),update_profile.class);
            w.putExtra("username",username);
            startActivity(w);

        }else if (id == R.id.nav_gallery) {
            Intent w= new Intent(getApplicationContext(),profile.class);
            w.putExtra("username",username);
            startActivity(w);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {
            Intent w= new Intent(getApplicationContext(),postOnFacebook.class);
            w.putExtra("username",username);
            startActivity(w);

        } else if (id == R.id.nav_share) {
            Intent intent = new AppInviteInvitation.IntentBuilder("INVITATION TO APP")
                    .setMessage("Hello, Join the app now.")
                    .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                    .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                    .setCallToActionText(getString(R.string.invitation_cta))
                    .build();

            startActivityForResult(intent, REQUEST_INVITE);


        } else if (id == R.id.nav_send) {
            Intent w= new Intent(getApplicationContext(),feedbackActivity.class);
            w.putExtra("username",username);
            startActivity(w);
        }
        else if (id == R.id.nav_logout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent w= new Intent(getApplicationContext(),login_activity.class);
            startActivity(w);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot order : dataSnapshot.getChildren())
                {
                    OrderListTable u=order.getValue(OrderListTable.class);
                    new firebasetoOrderRoomThread(getApplicationContext(),u).execute();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), " Not added",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("requestCode=",  requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d("sent invitation " , id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }
}
