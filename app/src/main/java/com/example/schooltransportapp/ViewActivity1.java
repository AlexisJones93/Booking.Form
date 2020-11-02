package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewActivity1 extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser User;
    TextView cName,school;
    DatabaseReference reference;
    TextView txt1,txt2;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);


        txt1 = findViewById(R.id.txV1);
        txt2 = findViewById(R.id.txV2);




        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
        UID = User.getUid();
        /*
        reference = FirebaseDatabase.getInstance().getReference().child(User.getUid());
        */
        /*
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("user");
        */

       // DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("user");
      //  DatabaseReference zone1ref =zonesRef.child("UID");
/*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("https://schooltrasportapp.firebaseio.com/user");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // user mUser = dataSnapshot.getValue(user.class);
              //  txt1.setText(mUser.getFirstname());
               // txt2.setText(mUser.getSchool());
                user cuser = dataSnapshot.getValue(user.class);
                System.out.println(cuser);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }



        });
        */
}}