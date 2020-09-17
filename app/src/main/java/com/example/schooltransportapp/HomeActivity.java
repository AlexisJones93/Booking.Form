package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {

    Button newBooking,signOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        newBooking = findViewById(R.id.btnBooking);
        signOut = findViewById(R.id.signOutbtn);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });


        newBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity. this, BookingActivity.class));
            }
        });



    }
}
