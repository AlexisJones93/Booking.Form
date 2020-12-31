package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddNewUserActivity extends AppCompatActivity {

    EditText contact,parentname;
    Button saveButton;
    FirebaseAuth mAuth;
    DatabaseReference rootDatabase;
    String UID;
    userdetails UserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        contact = findViewById(R.id.contactNumber);
        parentname = findViewById(R.id.parentName);
        saveButton = findViewById(R.id.save);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        UID = user1.getUid();

        UserDetails = new userdetails();
        rootDatabase = FirebaseDatabase.getInstance().getReference().child("userdetails");

      saveButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String name = parentname.getText().toString();
              String contactnumber = contact.getText().toString();

              UserDetails.setUsersname(name);
              UserDetails.setUsercontactnumber(contactnumber);

              rootDatabase.child(UID).setValue(UserDetails);
          }
      });


    }
}