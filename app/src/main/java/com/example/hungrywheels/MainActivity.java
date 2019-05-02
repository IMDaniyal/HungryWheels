package com.example.hungrywheels;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView txtUser;
    private TextView txtEmail;
    private ImageView imgProfile;
    private LoginButton logoutButton;
    private LoginButton loginButton;
    SignInButton b;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN=2;

    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i( "printHashKey()", "Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);


        printHashKey(this);


        b=findViewById(R.id.sign_in_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.btnFacebookIn);
        logoutButton = (LoginButton) findViewById(R.id.btnFacebookOut);
        logoutButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        loginButton.setReadPermissions("email", "public_profile", "user_friends","user_birthday");//user_status, publish_actions..
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Sign In canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(MainActivity.this, "Something bad happened", Toast.LENGTH_SHORT).show();
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (AccessToken.getCurrentAccessToken() != null)
                    Toast.makeText(MainActivity.this, AccessToken.getCurrentAccessToken().getExpires().toString(), Toast.LENGTH_SHORT).show();
                if (user != null) {
                    String email = user.getEmail();
                    String userName = user.getDisplayName();
                    String id=user.getUid();
                    String phone=user.getPhoneNumber();
                    UserTable u=new UserTable();
                    u.setFacebook("1");
                    u.setUsername(id);
                    u.setEmail(email);
                    u.setTwitter("0");
                    u.setPicflag("0");
                    u.setAge("-1"); //default
                    u.setPhone("-1"); //default
                    u.setPassword("-1"); //default
                    if(phone != null) {
                        u.setPhone(phone);
                    }
                    u.setName(userName);
                    facebookLoginThread fbt= new facebookLoginThread(getApplicationContext(),u);
                    fbt.execute();

                } else {
                    Log.d("TG", "SIGNED OUT");
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                }
            }
        };
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "signInWithCredential:success");

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String email = user.getEmail();
                        String userName = user.getDisplayName();
                        String id=user.getUid();
                        String phone=user.getPhoneNumber();
                        UserTable u=new UserTable();
                        u.setFacebook("0");
                        u.setUsername(id);
                        u.setEmail(email);
                        u.setTwitter("0");
                        u.setPicflag("0");
                        u.setAge("-1"); //default
                        u.setPhone("-1"); //default
                        u.setPassword("-1"); //default
                        if(phone != null) {
                            u.setPhone(phone);
                        }
                        u.setName(userName);
                        facebookLoginThread fbt= new facebookLoginThread(getApplicationContext(),u);
                        fbt.execute();


                    } else {


                        Log.w("TAG", "signInWithCredential:failure", task.getException());

                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        //firebaseAuth.addAuthStateListener(mAuthListener);
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        mGoogleSignInClient.signOut();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken token)
    {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (!task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount user = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            String email = user.getEmail();
            String userName = user.getDisplayName();
            String id=user.getId();
            String phone;
            UserTable u=new UserTable();
            u.setFacebook("0");
            u.setUsername(id);
            u.setEmail(email);
            u.setTwitter("0");
            u.setPicflag("0");
            u.setAge("-1"); //default
            u.setPhone("-1"); //default
            u.setPassword("-1"); //default
            u.setName(userName);
            facebookLoginThread fbt= new facebookLoginThread(getApplicationContext(),u);
            fbt.execute();


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut();
    }

    @Override
    public void onClick(View v) {
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}
