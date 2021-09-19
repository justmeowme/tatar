package com.example.tatar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String email, password, name, nickname, userID;
    EditText mEmail, mPassword, mName, mNickname;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mName = findViewById(R.id.editTextName);
        mNickname = findViewById(R.id.editTextNickname);

        mNext = findViewById(R.id.buttonnext);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                name = mName.getText().toString().trim();
                nickname = mNickname.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("name", name);
                            user.put("nickname", nickname);
                            user.put("score", "0");

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Maybe someday I will write something here.....
                                }
                            });

                            Intent intent = new Intent(RegistrationActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                        else{
                            if (password.length()<6){
                                Toast.makeText(RegistrationActivity.this, "Пароль должен быть больше 6 символов", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(RegistrationActivity.this, "Проблемы с соединением. Попробуйте снова", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

    }
}