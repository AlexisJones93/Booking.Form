package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity {

DatabaseReference rootDatabase;
String UID;
FirebaseAuth mAuth;
EditText cFirstName, cLastName;
Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        cFirstName = findViewById(R.id.childFirstName);
        cLastName = findViewById(R.id.childSecondName);
        submit = findViewById(R.id.btnSubmit);


       /// FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    FirebaseUser cuser = FirebaseAuth.getInstance().getCurrentUser();
              String fdata = cFirstName.getText().toString();
              String sdata = cLastName.getText().toString();
            //   rootDatabase.child(cuser.getUid()).push().setValue(fdata,sdata);


              //  mAuth = FirebaseAuth.getInstance();
               // FirebaseUser user = ((FirebaseAuth) mAuth).getCurrentUser();

              // UID = user.getUid();
              //  final DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://schooltrasportapp.firebaseio.com/user");

               // DatabaseReference user1 = myRootRef.child("User");
                rootDatabase.child("Firstname").setValue(fdata);
                rootDatabase.child("Lastname").setValue(sdata);



            }
        });





    }
}
