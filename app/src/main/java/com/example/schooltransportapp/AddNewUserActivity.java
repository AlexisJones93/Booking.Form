package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddNewUserActivity extends AppCompatActivity {

    EditText contact,parentname,cschool;
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
        cschool = findViewById(R.id.childschools);
        saveButton = findViewById(R.id.save);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        UID = user1.getUid();




      saveButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
               addNewUser();
              startActivity(new Intent(AddNewUserActivity. this, HomeActivity.class));
          }
      });


    }

    public void addNewUser(){
        if(parentname.getText().toString().equals("") || contact.getText().toString().equals("")){
            Toast.makeText(AddNewUserActivity.this,"Please make sure both fields are filled",Toast.LENGTH_SHORT).show();
        }
        else{
            UserDetails = new userdetails();
            rootDatabase = FirebaseDatabase.getInstance().getReference().child("userdetails");

            String name = parentname.getText().toString();
            String contactnumber = contact.getText().toString();
            String school = cschool.getText().toString();
            UserDetails.setUsersname(name);
            UserDetails.setUsercontactnumber(contactnumber);
            UserDetails.setUsercontactnumber(school);
            rootDatabase.child(UID).setValue(UserDetails);
        }
    }
}