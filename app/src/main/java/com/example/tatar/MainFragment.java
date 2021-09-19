package com.example.tatar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    Button l11, l12, l13, l14, l15;
    Button l21, l22, l23, l24, l25;
    Button l31, l32, l33, l34, l35;
    static ProgressBar mProgressBar;
    static TextView mMotivate;
    int flagCheck = 0;

    FirebaseAuth mAuth;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        id = mAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String new_id = document.getId();
                        FirebaseFirestore.getInstance().collection("users").document(new_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()){
                                    ArrayList<String> mArray = new ArrayList<>();
                                    mArray.add(documentSnapshot.getString("nickname"));
                                    mArray.add(documentSnapshot.getString("score"));
                                    if (!(FriendsFragment.array_names.contains(documentSnapshot.getString("nickname")))){
                                        FriendsFragment.array.add(mArray);
                                        FriendsFragment.array_names.add(documentSnapshot.getString("nickname"));
                                        FriendsFragment.array_scores.add(Integer.valueOf(documentSnapshot.getString("score")));
                                    }
                                }
                            }
                        });
                    }

                }
            }
        });


        id = mAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String score = documentSnapshot.getString("score");
                    MainActivity.setDefaults("user_score", score, getContext());
                }
            }
        });

        mProgressBar = v.findViewById(R.id.progressBar);
        if (MainActivity.getDefaults("user_score", getActivity()) != null){
            int prog = Integer.valueOf(MainActivity.getDefaults("user_score", getActivity()));
            mProgressBar.setProgress(prog);
        }

        mMotivate = v.findViewById(R.id.text_motivate);
        int a = mProgressBar.getProgress();
        if (a < 30){
            mMotivate.setText("Продолжай заниматься, и все получится!");
        } else{
            if (a < 60){
                mMotivate.setText("Ты делаешь успехи! Не останавливайся");
            } else{
                if (a < 90){
                    mMotivate.setText("Ты почти выполнил ежедневное задание!");
                } else{
                    mMotivate.setText("Ежедневное задание выполнено! Молодец!");
                }
            }
        }

        l11 = v.findViewById(R.id.button_meme_11);
        l12 = v.findViewById(R.id.button_meme_12);
        l13 = v.findViewById(R.id.button_meme_13);
        l14 = v.findViewById(R.id.button_meme_14);
        l15 = v.findViewById(R.id.button_meme_15);

        l21 = v.findViewById(R.id.button_meme_6);
        l22 = v.findViewById(R.id.button_meme_2);
        l23 = v.findViewById(R.id.button_meme_3);
        l24 = v.findViewById(R.id.button_meme_4);
        l25 = v.findViewById(R.id.button_meme_5);

        l31 = v.findViewById(R.id.button_meme_31);
        l32 = v.findViewById(R.id.button_meme_32);
        l33 = v.findViewById(R.id.button_meme_33);
        l34 = v.findViewById(R.id.button_meme_34);
        l35 = v.findViewById(R.id.button_meme_35);

        if (MainActivity.getDefaults("done1", getContext()) != null){
            l11.setText("✓");
            l12.setText("💫");
            l12.setEnabled(true);
        }

        if (MainActivity.getDefaults("done2", getContext()) != null){
            l12.setText("✓");
            l13.setText("⭐️");
            l13.setEnabled(true);
        }

        if (MainActivity.getDefaults("done3", getContext()) != null){
            l13.setText("✓");
            l14.setText("⭐️");
            l14.setEnabled(true);
        }

        if (MainActivity.getDefaults("done4", getContext()) != null){
            l14.setText("✓");
            l15.setText("⭐️");
            l15.setEnabled(true);
        }

        if (MainActivity.getDefaults("done5", getContext()) != null){
            l15.setText("✓");
            flagCheck = 1;
        }

        Button mCheck1 = v.findViewById(R.id.check1);
        mCheck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagCheck==0){
                    Toast.makeText(getActivity(), "Чтобы открыть эту возможность, необходимо пройти все уроки уровня", Toast.LENGTH_SHORT).show();
                } else{
                    String massWords = "Акча,Сатып алырга,Кирэк,"+"Миңа,Ундүрт,"+"Мин,Соңгы,Кәтлит";
                    String massTranslate = "Деньги,Купить,Надо,"+"Мне,Четырнадцать,"+"Я,Последний,Котлета";
                    String massSound = "[акча],[сотып алырга],[кирэк],"+"[мина],[ундурт],"+"[мин],[сонго],[кэтлит]";
                    String massIncorrect = "Дерево,Чайка,Лапша,Подсмотреть,Никак,Поздно,Жизнь,Лошадь,Есть,Красивый,Видимо,"+"Солнце,Десять,Твое,Тридцать,Четыре,Кока-кола,Торнадо,Труба,Картина";
                    String memeText = "Акча сатып алырга кирэк,"+"Миңа ундүрт,"+"Миңа соңгы кәтлит";
                    String memeTranslate = "Надо купить денег,"+"Мне четырнадцать,"+"Я и последняя котлета";

                    MainActivity.setDefaults("words", massWords, getContext());
                    MainActivity.setDefaults("translates", massTranslate, getContext());
                    MainActivity.setDefaults("sounds", massSound, getContext());
                    MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                    MainActivity.setDefaults("meme", memeText, getContext());
                    MainActivity.setDefaults("memetr", memeTranslate, getContext());
                    MainActivity.setDefaults("number", "111111", getContext());

                    startActivity(new Intent(getActivity(), MemStudy.class));
                }
            }
        });

        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String massWords = "Ул,Син,Белән,Татар,Сөйләшергә,Башларга";
                String massTranslate = "Он,Ты,С,Татарский,Говорить,Начать";
                String massSound = "[ул],[син],[бэлэн],[татар],[сойлэшэргэ],[башларга] ";
                String massIncorrect = "Я, Они, Вместе, За, После, Английский, Русский, Видеть, Слушать, Хотеть";
                String memeText = "Ул синең белән татарча сөйләшә башлагач";
                String memeTranslate = "Когда он начал говорить с тобой на татарском";

                MainActivity.setDefaults("words", massWords, getContext());
                MainActivity.setDefaults("translates", massTranslate, getContext());
                MainActivity.setDefaults("sounds", massSound, getContext());
                MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                MainActivity.setDefaults("meme", memeText, getContext());
                MainActivity.setDefaults("memetr", memeTranslate, getContext());
                MainActivity.setDefaults("number", "1", getContext());

                startActivity(new Intent(getActivity(), MemStudy.class));
            }
        });

        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String massWords = "Миңа,Ундүрт";
                String massTranslate = "Мне,Четырнадцать";
                String massSound = "[мина],[ундурт]";
                String massIncorrect = "Солнце,Десять,Твое,Тридцать,Четыре,Кока-кола,Торнадо,Труба,Картина";
                String memeText = "Миңа ундүрт";
                String memeTranslate = "Мне четырнадцать";

                MainActivity.setDefaults("words", massWords, getContext());
                MainActivity.setDefaults("translates", massTranslate, getContext());
                MainActivity.setDefaults("sounds", massSound, getContext());
                MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                MainActivity.setDefaults("meme", memeText, getContext());
                MainActivity.setDefaults("memetr", memeTranslate, getContext());
                MainActivity.setDefaults("number", "11", getContext());

                startActivity(new Intent(getActivity(), MemStudy.class));
            }
        });

        l13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String massWords = "Мин, Соңгы, Кәтлит";
                String massTranslate = "Я,Последний,Котлета";
                String massSound = "[мин],[сонго],[кэтлит]";
                String massIncorrect = "Мы,Ты,Вы,Она,Они,Котлета,Пюре,Мясо,Салат,Шаурма,Пицца,Первый";
                String memeText = "Мин соңгы кәтлит";
                String memeTranslate = "Я и последняя котлета";

                MainActivity.setDefaults("words", massWords, getContext());
                MainActivity.setDefaults("translates", massTranslate, getContext());
                MainActivity.setDefaults("sounds", massSound, getContext());
                MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                MainActivity.setDefaults("meme", memeText, getContext());
                MainActivity.setDefaults("memetr", memeTranslate, getContext());
                MainActivity.setDefaults("number", "111", getContext());

                startActivity(new Intent(getActivity(), MemStudy.class));
            }
        });

        l14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String massWords = "Мин, Йокы, Килә";
                String massTranslate = "Я,Сон,Хочется";
                String massSound = "[мин],[йоко],[килә]";
                String massIncorrect = "Мы,Ты,Она,Они,Свет,Ночь,Мечта,Видеть,Слышать,Бежать";
                String memeText = "Минем йоко килә";
                String memeTranslate = "Хочу спать";

                MainActivity.setDefaults("words", massWords, getContext());
                MainActivity.setDefaults("translates", massTranslate, getContext());
                MainActivity.setDefaults("sounds", massSound, getContext());
                MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                MainActivity.setDefaults("meme", memeText, getContext());
                MainActivity.setDefaults("memetr", memeTranslate, getContext());
                MainActivity.setDefaults("number", "1111", getContext());

                startActivity(new Intent(getActivity(), MemStudy.class));
            }
        });

        l15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String massWords = "Ник";
                String massTranslate = "Зачем";
                String massSound = "[ник]";
                String massIncorrect = "Почему,Как,Конь,Для чего,Где,Когда,Во сколько,С кем";
                String memeText = "Ник";
                String memeTranslate = "Зачем";

                MainActivity.setDefaults("words", massWords, getContext());
                MainActivity.setDefaults("translates", massTranslate, getContext());
                MainActivity.setDefaults("sounds", massSound, getContext());
                MainActivity.setDefaults("incorrects", massIncorrect, getContext());
                MainActivity.setDefaults("meme", memeText, getContext());
                MainActivity.setDefaults("memetr", memeTranslate, getContext());
                MainActivity.setDefaults("number", "11111", getContext());

                startActivity(new Intent(getActivity(), MemStudy.class));
            }
        });

        return v;
    }
}