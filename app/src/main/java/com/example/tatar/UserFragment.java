package com.example.tatar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFragment extends Fragment {

    static ProgressBar mProgressBar1;

    FirebaseAuth mAuth;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        id = mAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String score = documentSnapshot.getString("score");
                    String user_nick = documentSnapshot.getString("nickname");
                    String user_name = documentSnapshot.getString("name");
                    String user_email = documentSnapshot.getString("email");
                    MainActivity.setDefaults("user_nick", user_nick, getContext());
                    MainActivity.setDefaults("user_name", user_name, getContext());
                    MainActivity.setDefaults("user_email", user_email, getContext());
                    MainActivity.setDefaults("user_score", score, getContext());
                }
            }
        });

        TextView mText = v.findViewById(R.id.text);
        if (MainActivity.getDefaults("user_nick", getActivity()) != null){
            mText.setText("Привет,                  " + MainActivity.getDefaults("user_nick", getActivity()) + "!");
        }

        Button mName = v.findViewById(R.id.name);
        Button mEmail = v.findViewById(R.id.email);
        if (MainActivity.getDefaults("user_name", getActivity()) != null){
            mName.setText(MainActivity.getDefaults("user_name", getActivity()));
        }
        if (MainActivity.getDefaults("user_email", getActivity()) != null){
            mEmail.setText(MainActivity.getDefaults("user_email", getActivity()));
        }


        mProgressBar1 = v.findViewById(R.id.progressBar);
        if (MainActivity.getDefaults("user_score", getActivity()) != null){
            int prog = Integer.valueOf(MainActivity.getDefaults("user_score", getActivity()));
            mProgressBar1.setProgress(prog);
        }

        Button mExit = v.findViewById(R.id.exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        TextView mSettings = v.findViewById(R.id.settings);
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Настраивать пока нечего", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}