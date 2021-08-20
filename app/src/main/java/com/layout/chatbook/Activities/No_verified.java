package com.layout.chatbook.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.layout.chatbook.databinding.ActivityNoVerifiedBinding;

public class No_verified extends AppCompatActivity {

    ActivityNoVerifiedBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoVerifiedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.phoneNo.requestFocus();

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){

            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }


        binding.continueNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Otp_verified.class);
                intent.putExtra("phoneNumber",binding.phoneNo.getText().toString());
                startActivity(intent);
            }
        });
    }
}