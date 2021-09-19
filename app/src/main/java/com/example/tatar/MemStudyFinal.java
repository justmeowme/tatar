package com.example.tatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MemStudyFinal extends AppCompatActivity {

    FirebaseAuth mAuth;
    String id;
    int flag = 0;
    Button mTranslate, mNext;
    TextView mMemeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_study_final);

        ImageView mImage = findViewById(R.id.imageView4);
        mMemeText = findViewById(R.id.memetext);
        mMemeText.setText(MainActivity.getDefaults("meme", MemStudyFinal.this));

        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 1){
            mImage.setImageResource(R.drawable.meme1);
        }
        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 2){
            mImage.setImageResource(R.drawable.meme2);
        }
        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 3){
            mImage.setImageResource(R.drawable.meme3);
        }
        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 4){
            mImage.setImageResource(R.drawable.meme4);
        }
        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 5){
            mImage.setImageResource(R.drawable.meme5);
        }
        if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 6){
            mImage.setImageResource(R.drawable.molodec);
            startActivity(new Intent(MemStudyFinal.this, MenuActivity.class));
        }



        mTranslate = findViewById(R.id.translate);
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0){
                    mMemeText.setText(MainActivity.getDefaults("meme", MemStudyFinal.this));
                    flag = 1;
                } else{
                    mMemeText.setText(MainActivity.getDefaults("memetr", MemStudyFinal.this));
                    flag = 0;
                }

            }
        });

        mNext = findViewById(R.id.next);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 1){
                    MainActivity.setDefaults("done1", "yes", MemStudyFinal.this);
                }

                if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 2){
                    MainActivity.setDefaults("done2", "yes", MemStudyFinal.this);
                }

                if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 3){
                    MainActivity.setDefaults("done3", "yes", MemStudyFinal.this);
                }

                if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 4){
                    MainActivity.setDefaults("done4", "yes", MemStudyFinal.this);
                }

                if (MainActivity.getDefaults("number", MemStudyFinal.this).length() == 5){
                    MainActivity.setDefaults("done5", "yes", MemStudyFinal.this);
                    startActivity(new Intent(MemStudyFinal.this, MenuActivity.class));
                }


                id = mAuth.getInstance().getUid();

                FirebaseFirestore.getInstance().collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String score = documentSnapshot.getString("score");
                            int s1 = Integer.valueOf(score) + 20;
                            if (s1 > 100){
                                s1 = 100;
                            }
                            MainFragment.mProgressBar.setProgress(s1);
                            UserFragment.mProgressBar1.setProgress(s1);

                            FirebaseFirestore.getInstance().collection("users").document(id).update("score", String.valueOf(s1));

                        }
                    }
                });

                startActivity(new Intent(MemStudyFinal.this, MenuActivity.class));
            }
        });

    }
}