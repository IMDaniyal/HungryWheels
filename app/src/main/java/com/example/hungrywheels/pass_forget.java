package com.example.hungrywheels;

import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class pass_forget extends AppCompatActivity {

    private String verify_id;
    private Button btn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_forget);


        String phone = getIntent().getStringExtra("phone");
        String user = getIntent().getStringExtra("username");
        mAuth=FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        editText= (EditText)findViewById(R.id.editTextCode);

        sendCode(phone);

        btn=(Button)findViewById(R.id.buttonSignIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=editText.getText().toString();
                if(code.isEmpty() || code.length()<6)
                {
                    editText.setError("Enter Valid Code");
                    editText.requestFocus();
                    return;
                }


                verifyCode(code);

            }
        });


        Toast.makeText(getApplicationContext(), "Dear " + user + ", a code has been sent on " + phone + " number", Toast.LENGTH_LONG).show();
    }

    private void verifyCode(String code_received)
    {

        PhoneAuthCredential p=PhoneAuthProvider.getCredential(verify_id,code_received);
        signInWithCredential(p);
    }

    private void signInWithCredential(PhoneAuthCredential p) {

        mAuth.signInWithCredential(p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    Intent intent=new Intent(pass_forget.this,login_fragment.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 30, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallBack);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verify_id=s;


        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String  code=phoneAuthCredential.getSmsCode();
            if(code!=null)
            {
                editText.setText(code);
                signInWithCredential(phoneAuthCredential);

            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

}
