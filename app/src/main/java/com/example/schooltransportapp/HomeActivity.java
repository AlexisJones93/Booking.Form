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


public class HomeActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    TextView uName, uemail, ucontact, uSchool;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        
        drawerLayout = findViewById(R.id.drawer_layout);
        uName = findViewById(R.id.userName);
        uemail = findViewById(R.id.useremail);
        ucontact = findViewById(R.id.usercontact);
        uSchool = findViewById(R.id.userschool);


        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        String UID = user1.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("userdetails").child(UID);


            // listener used to listen when a user is signed in.
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   FirebaseUser user = mAuth.getCurrentUser();
                   userdetails cuser = snapshot.getValue(userdetails.class);
                   //if statement checking user exists
                   if(cuser!=null){

                       //if they do then their details will display and they will be welcomed.
                       uName.setText("Welcome back to your account " + cuser.getUsersname() + "!");
                       uemail.setText(user.getEmail());
                       ucontact.setText(cuser.getUsercontactnumber());
                       uSchool.setText(cuser.getChildschool());
                      // Toast.makeText(HomeActivity.this," User",Toast.LENGTH_SHORT).show();
                   }
                   else{
                       // if not then they will be sent back to the login page.
                       FirebaseAuth.getInstance().signOut();
                       startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                   }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            
    }

    //NAV Drawer code

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
                        recreate();
                }
                public void ClickBooking(View view){
                        HomeActivity.redirectActivity(this,BookingActivity.class);
                }

                public void ClickViewBooking(View view){
                        HomeActivity.redirectActivity(this,YourBookingActivity.class);
                }


            public void ClickLogout(View view){
                    logout(this);
            }

            private void logout(Activity activity){
                FirebaseAuth.getInstance().signOut();
                HomeActivity.redirectActivity(this,LoginActivity.class);
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

