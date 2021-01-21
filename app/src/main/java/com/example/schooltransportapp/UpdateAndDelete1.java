package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class UpdateAndDelete1 extends AppCompatActivity {

    EditText cName, cSchool,cYear, cGender,cNum,collectionDate, collectionTime;
    Button delButton, savButton;
   // DatabaseReference dRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete1);

        cName = findViewById(R.id.childName);
        cSchool = findViewById(R.id.childSchool);
        cYear = findViewById(R.id.childYear);
        cGender = findViewById(R.id.childGen);
        cNum = findViewById(R.id.childNum);
        collectionDate = findViewById(R.id.colDate);
        collectionTime = findViewById(R.id.colTime);
        delButton = findViewById(R.id.delete_btn);
        savButton = findViewById(R.id.save_btn);


        final String  key = getIntent().getStringExtra("Booking");
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
                //removes the values at the users key from the database
                ref.child(key).removeValue();
                Intent deletecomplete = new Intent(UpdateAndDelete1.this,YourBookingActivity.class);
                Toast.makeText(UpdateAndDelete1.this,"Deleted",Toast.LENGTH_SHORT).show();
                startActivity(deletecomplete);
            }


        });


        ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               //fetches data from the key that its been given and sets in the edit text boxes.
                user cuser = snapshot.getValue(user.class);
                cName.setText(cuser.getFirstname());
                cSchool.setText(cuser.getSchool());
                cYear.setText(cuser.getYearGroup());
                cGender.setText(cuser.getGender());
                cNum.setText(cuser.getChildNumber());
                collectionDate.setText(cuser.getDate());
                collectionTime.setText(cuser.getTime());
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
                String yData = cYear.getText().toString();
                String gData = cGender.getText().toString();
                String numData = cNum.getText().toString();
                String colDate = collectionDate.getText().toString();
                String colTime = collectionTime.getText().toString();

                //sets the new values of the edited booking
                ref.child(key).child("firstname").setValue(nData);
                ref.child(key).child("school").setValue(sData);
                ref.child(key).child("yearGroup").setValue(yData);
                ref.child(key).child("gender").setValue(gData);
                ref.child(key).child("childNumber").setValue(numData);
                ref.child(key).child("date").setValue(colDate);
                ref.child(key).child("time").setValue(colTime);

                startActivity(new Intent(UpdateAndDelete1.this, YourBookingActivity.class));
                Toast.makeText(UpdateAndDelete1.this,"Update Saved",Toast.LENGTH_SHORT).show();

            }
        });

    }

}