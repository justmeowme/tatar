package com.example.tatar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EnterActivity extends AppCompatActivity {

    TextView toRegistration;
    Button mLogin;
    FirebaseAuth mAuth;
    String email, password;
    EditText mTextLogin, mTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mAuth = FirebaseAuth.getInstance();
        mTextLogin = findViewById(R.id.editTextEmail);
        mTextPassword = findViewById(R.id.editTextPassword);

        mLogin = findViewById(R.id.enter);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mTextLogin.getText().toString().trim();
                password = mTextPassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            ProgressBar mRound = findViewById(R.id.progressBarRound);
                            mRound.setVisibility(View.VISIBLE);
                            startActivity(new Intent(EnterActivity.this, MenuActivity.class));
                        } else{
                            Toast.makeText(EnterActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}