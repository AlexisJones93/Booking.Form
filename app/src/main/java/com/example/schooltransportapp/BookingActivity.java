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
user user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        cFirstName = findViewById(R.id.childFirstName);
        cLastName = findViewById(R.id.childSecondName);
        submit = findViewById(R.id.btnSubmit);

       /// FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        /*Working code block

       mAuth = FirebaseAuth.getInstance();
       final FirebaseUser user = mAuth.getCurrentUser();
       UID = user.getUid();
        final DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://schooltrasportapp.firebaseio.com/user");


         */
        //Test code
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        UID = user1.getUid();

        user = new user();
        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fdata = cFirstName.getText().toString();
                String sdata = cLastName.getText().toString();

                user.setFirstname(fdata);
                user.setSchool(sdata);

             rootDatabase.child(UID).push().setValue(user);










             // String fdata = cFirstName.getText().toString();
            //  String sdata = cLastName.getText().toString();

              // Redundant code - Good for pushing new bookings but doesn't specify table column names.
              // FirebaseUser cuser = FirebaseAuth.getInstance().getCurrentUser();
            // rootDatabase.child(cuser.getUid()).push().setValue(fdata,sdata);




            // able to push and give column names but created a new id for each item submitted not good.
          // DatabaseReference user1 = myRootRef.child("user");

                //below is the code I need

             //   mAuth = FirebaseAuth.getInstance();
            //    FirebaseUser user = mAuth.getCurrentUser();
            //    UID = user.getUid();
            //    final DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://schooltrasportapp.firebaseio.com/user");


            //   DatabaseReference user1 = myRootRef.child(UID).push();
         //  user1.child("Firstname").setValue(fdata);
         //  user1.child("School").setValue(sdata);



            }
        });





    }
}
