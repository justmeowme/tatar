package com.example.tatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemStudy extends AppCompatActivity {

    Button mTranslate, mNext;
    TextView mWord, mSound;

    int mWordNumber = 0;
    String[] mWords, mSounds, mTranslates;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_study);

        mWord = findViewById(R.id.word);
        mSound = findViewById(R.id.word_sound);

        mWords = MainActivity.toArray(MainActivity.getDefaults("words", MemStudy.this));
        mSounds = MainActivity.toArray(MainActivity.getDefaults("sounds", MemStudy.this));
        mTranslates = MainActivity.toArray(MainActivity.getDefaults("translates", MemStudy.this));

        mWord.setText(mWords[mWordNumber]);
        mSound.setText(mSounds[mWordNumber]);

        mTranslate = findViewById(R.id.translate);
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0){
                    mWord.setText(mTranslates[mWordNumber]);
                    mSound.setText(" ");
                    flag = 1;
                } else{
                    mWord.setText(mWords[mWordNumber]);
                    mSound.setText(mSounds[mWordNumber]);
                    flag = 0;
                }
            }
        });

        mNext = findViewById(R.id.next);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWordNumber + 1 == mWords.length){
                    startActivity(new Intent(MemStudy.this, MemStudyTest.class));
                } else{
                    mWordNumber += 1;
                    mWord.setText(mWords[mWordNumber]);
                    mSound.setText(mSounds[mWordNumber]);
                }
            }
        });


    }
}