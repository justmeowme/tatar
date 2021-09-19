package com.example.tatar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MemStudyTest extends AppCompatActivity {

    Button mCheck, mButton1, mButton2, mButton3, mButton4;
    TextView mWord;

    String[] mCorrectWords;
    String[] mIncorrectWords;
    String[] mTatarWords;

    int f1 = 0, f2 = 0, f3 = 0, f4 = 0;
    int a, b, c, d;
    int mWordNumber = 0;
    int choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_study_test);

        mWord = findViewById(R.id.word);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);

        mTatarWords = MainActivity.toArray(MainActivity.getDefaults("words", MemStudyTest.this));
        mCorrectWords = MainActivity.toArray(MainActivity.getDefaults("translates", MemStudyTest.this));
        mIncorrectWords = MainActivity.toArray(MainActivity.getDefaults("incorrects", MemStudyTest.this));

        mWord.setText(mTatarWords[mWordNumber]);

        final Random random = new Random();
        a = random.nextInt(4);
        b = random.nextInt(mIncorrectWords.length);

        while (c == b){
            c = random.nextInt(mIncorrectWords.length);
        }
        while (d == b || d == c){
            d = random.nextInt(mIncorrectWords.length);
        }

        if (a==0){
            mButton1.setText(mCorrectWords[mWordNumber]);
            mButton2.setText(mIncorrectWords[b]);
            mButton3.setText(mIncorrectWords[c]);
            mButton4.setText(mIncorrectWords[d]);

        }
        if (a==1){
            mButton2.setText(mCorrectWords[mWordNumber]);
            mButton1.setText(mIncorrectWords[b]);
            mButton3.setText(mIncorrectWords[c]);
            mButton4.setText(mIncorrectWords[d]);
        }
        if (a==2){
            mButton3.setText(mCorrectWords[mWordNumber]);
            mButton1.setText(mIncorrectWords[c]);
            mButton2.setText(mIncorrectWords[b]);
            mButton4.setText(mIncorrectWords[d]);
        }
        if (a==3){
            mButton4.setText(mCorrectWords[mWordNumber]);
            mButton1.setText(mIncorrectWords[d]);
            mButton2.setText(mIncorrectWords[b]);
            mButton3.setText(mIncorrectWords[c]);
        }

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_buttons(mButton1, mButton2, mButton3, mButton4);
                choosen = 0;
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_buttons(mButton2, mButton1, mButton3, mButton4);
                choosen = 1;
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_buttons(mButton3, mButton1, mButton2, mButton4);
                choosen = 2;
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_buttons(mButton4, mButton1, mButton2, mButton3);
                choosen = 3;
            }
        });

        TextView mTextAsk;
        mTextAsk = findViewById(R.id.textAsk);

        mCheck = findViewById(R.id.check);
        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f1 == 0){
                    if (a==choosen){
                        mTextAsk.setText("Правильный ответ!");
                        mTextAsk.setTextColor(Color.parseColor("#00BFA6"));
                        mCheck.setText("Далее");
                        f1 = 1;
                    } else{
                        mTextAsk.setText("Неправильно. Попробуй снова");
                        mTextAsk.setTextColor(Color.parseColor("#ee6381"));
                    }
                } else{
                    f1 = 0;
                    mTextAsk.setText("Выберите правильный перевод: ");
                    mTextAsk.setTextColor(Color.BLACK);
                    mButton1.setBackgroundResource(R.drawable.transparent_button);
                    mButton1.setTextColor(Color.BLACK);
                    mButton2.setBackgroundResource(R.drawable.transparent_button);
                    mButton2.setTextColor(Color.BLACK);
                    mButton3.setBackgroundResource(R.drawable.transparent_button);
                    mButton3.setTextColor(Color.BLACK);
                    mButton4.setBackgroundResource(R.drawable.transparent_button);
                    mButton4.setTextColor(Color.BLACK);

                    mWordNumber += 1;

                    if (mWordNumber == mTatarWords.length){
                        startActivity(new Intent(MemStudyTest.this, MemStudyFinal.class));
                    } else{
                        mCheck.setText("Проверить");
                        mWord.setText(mTatarWords[mWordNumber]);

                        final Random random = new Random();
                        a = random.nextInt(4);
                        b = random.nextInt(mIncorrectWords.length);

                        while (c == b){
                            c = random.nextInt(mIncorrectWords.length);
                        }
                        while (d == b || d == c){
                            d = random.nextInt(mIncorrectWords.length);
                        }

                        if (a==0){
                            mButton1.setText(mCorrectWords[mWordNumber]);
                            mButton2.setText(mIncorrectWords[b]);
                            mButton3.setText(mIncorrectWords[c]);
                            mButton4.setText(mIncorrectWords[d]);

                        }
                        if (a==1){
                            mButton2.setText(mCorrectWords[mWordNumber]);
                            mButton1.setText(mIncorrectWords[b]);
                            mButton3.setText(mIncorrectWords[c]);
                            mButton4.setText(mIncorrectWords[d]);
                        }
                        if (a==2){
                            mButton3.setText(mCorrectWords[mWordNumber]);
                            mButton1.setText(mIncorrectWords[c]);
                            mButton2.setText(mIncorrectWords[b]);
                            mButton4.setText(mIncorrectWords[d]);
                        }
                        if (a==3){
                            mButton4.setText(mCorrectWords[mWordNumber]);
                            mButton1.setText(mIncorrectWords[d]);
                            mButton2.setText(mIncorrectWords[b]);
                            mButton3.setText(mIncorrectWords[c]);
                        }

                    }


                }
            }
        });


    }

    public void change_buttons(Button purple, Button w1, Button w2, Button w3){
        purple.setBackgroundResource(R.drawable.purple_button);
        purple.setTextColor(Color.WHITE);
        w1.setBackgroundResource(R.drawable.transparent_button);
        w1.setTextColor(Color.BLACK);
        w2.setBackgroundResource(R.drawable.transparent_button);
        w2.setTextColor(Color.BLACK);
        w3.setBackgroundResource(R.drawable.transparent_button);
        w3.setTextColor(Color.BLACK);
    }

}