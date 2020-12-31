package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
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
DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        drawerLayout = findViewById(R.id.drawer_layout);
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

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        BookingActivity.redirectActivity(this,HomeActivity.class);
    }
    public void ClickBooking(View view){
        BookingActivity.redirectActivity(this,BookingActivity.class);
        recreate();
    }
/*
public void ClickAboutUs(View view){
        redirectActivity(this,);
}
*/

    public void ClickLogout(View view){
        logout(this);
    }
    private void logout(Activity activity){
        FirebaseAuth.getInstance().signOut();
        BookingActivity.redirectActivity(this,LoginActivity.class);
    }


    private static void redirectActivity(Activity activity,Class aClass) {
        Intent intent = new Intent (activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }

}
