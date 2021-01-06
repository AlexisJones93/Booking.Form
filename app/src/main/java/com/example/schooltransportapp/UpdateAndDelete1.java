package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class UpdateAndDelete1 extends AppCompatActivity {

    EditText cName, cSchool;
    Button delButton, savButton;
    DatabaseReference dRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete1);

        cName = findViewById(R.id.childName);
        cSchool = findViewById(R.id.childSchool);
        delButton = findViewById(R.id.delete_btn);
        savButton = findViewById(R.id.save_btn);
        final String  key = getIntent().getStringExtra("Firstname");
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        final String UID = user1.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("user").child(UID);

        //cName.setText(getIntent().getStringExtra("Firstname"));
        //cSchool.setText(key);


        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref.child(key).removeValue();
                Intent deletecomplete = new Intent(UpdateAndDelete1.this,YourBookingActivity.class);
                startActivity(deletecomplete);
            }


        });


        ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user cuser = snapshot.getValue(user.class);
                cName.setText(cuser.getFirstname());
                cSchool.setText(cuser.getSchool());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        savButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nData = cName.getText().toString();
                String sData = cSchool.getText().toString();

                ref.child(key).child("firstname").setValue(nData);
                ref.child(key).child("school").setValue(sData);
            }
        });




    }


}