package com.example.tatar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class MenuActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    BottomNavigationView mNav;
    FirebaseAuth mAuth;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mNav = findViewById(R.id.menu);

        id = mAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String score = documentSnapshot.getString("score");
                    String user_nick = documentSnapshot.getString("nickname");
                    String user_name = documentSnapshot.getString("name");
                    String user_email = documentSnapshot.getString("email");
                    MainActivity.setDefaults("user_nick", user_nick, MenuActivity.this);
                    MainActivity.setDefaults("user_name", user_name, MenuActivity.this);
                    MainActivity.setDefaults("user_email", user_email, MenuActivity.this);
                    MainActivity.setDefaults("user_score", score, MenuActivity.this);
                }
            }
        });

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new MainFragment());
        fragmentTransaction.commit();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.user:
                        fragment = new UserFragment();
                        if (fragment!=getSupportFragmentManager().findFragmentById(R.id.container)){
                            loadFragment(fragment);
                            return true;
                        };
                    case R.id.main:
                        fragment = new MainFragment();
                        if (fragment!=getSupportFragmentManager().findFragmentById(R.id.container)){
                            loadFragment(fragment);
                            return true;
                        };
                    case R.id.friends:
                        fragment = new FriendsFragment();
                        if (fragment!=getSupportFragmentManager().findFragmentById(R.id.container)){
                            loadFragment(fragment);
                            return true;
                        };
                }
                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}