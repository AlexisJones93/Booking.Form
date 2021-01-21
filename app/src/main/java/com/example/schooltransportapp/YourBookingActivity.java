package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;

public class YourBookingActivity extends AppCompatActivity {


    String UID, key;
    FirebaseAuth mAuth;
    //TextView txt1,txt2;
    ListView listView1;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> keysList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    user user;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_booking);

        /* use with working one set of data code
        txt1 = findViewById(R.id.dFirstname);
        txt2 = findViewById(R.id.dSchool);
         */
        drawerLayout = findViewById(R.id.drawer_layout);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        String UID = user1.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("user").child(UID);

         //final String key = ref.child("user").getKey();

        listView1 = findViewById(R.id.listViewtxt);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView1.setAdapter(arrayAdapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String name = snapshot.getValue(user.class).getFirstname();
                String school = snapshot.getValue(user.class).getSchool();
                String contactNo = snapshot.getValue(user.class).getChildNumber();
                String gender = snapshot.getValue(user.class).getGender();
                String yeargroup = snapshot.getValue(user.class).getYearGroup();
                String date = snapshot.getValue(user.class).getDate();
                String time = snapshot.getValue(user.class).getTime();
                arrayList.add("Booking"+ "\n" + name + "\n" + school + "\n" + contactNo + "\n" + gender + "\n" + yeargroup + "\n" + date + "\n" + time);
                keysList.add(snapshot.getKey());
                arrayAdapter.notifyDataSetChanged();


                /* Working text for one data set to be displayed
                    user cuser = snapshot.getValue(user.class);
                    txt1.setText(cuser.getFirstname());
                    txt2.setText(cuser.getSchool());
                */
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String string = snapshot.getValue(user.class).toString();

                arrayList.remove(string);
                keysList.remove(snapshot.getKey());

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Intent updateanddelete = new Intent(YourBookingActivity.this,UpdateAndDelete1.class);
                // user u = (user) adapterView.getItemAtPosition(i);
                updateanddelete.putExtra("Firstname",keysList.get(i));
                //updateanddelete.putExtra("School" , u);
                // updateanddelete.putExtra("user", u.getUID());
                startActivity(updateanddelete);


                //*************************Redundant code****************************


                //  module.setgValue_id(arrayList.get(i));
            //  module.setgValue_Name(arrayList.get(i));
               // module .setgValue_id(arrayList.get(i));
                //String item = arrayAdapter.getItem(i);

                /*
                String item = arrayList.get(i);
                arrayList.remove(item);
                arrayAdapter.notifyDataSetChanged();
                ref.removeValue();
                */

                //Working delete code
               // String key = keysList.get(i);
            //    ref.child(key).removeValue();



            }
        });


        //dltBtn.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
               // final String str = module.getgValue_id().substring(0,10);
               // ref.child(str).removeValue();
               // DatabaseReference dRecord =FirebaseDatabase.getInstance().getReference("users").child(UID);
               // dRecord.removeValue();




               /* final String str = module.getgValue_id().substring(0,10);
                if (str==""){
                    Toast.makeText(YourBookingActivity.this,"Please select data before deleting",Toast.LENGTH_SHORT).show();
                }
                else{
                    ref.child("user").child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ref.child(str).removeValue();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Toast.makeText(YourBookingActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),YourBookingActivity.class);
                    startActivity(intent);
                }
*/

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
        YourBookingActivity.redirectActivity(this,HomeActivity.class);
    }
    public void ClickBooking(View view){
        YourBookingActivity.redirectActivity(this,BookingActivity.class);
    }

    public void ClickViewBooking(View view){
        recreate();
    }


    public void ClickLogout(View view){
        logout(this);
    }
    private void logout(Activity activity){
        FirebaseAuth.getInstance().signOut();
        YourBookingActivity.redirectActivity(this,LoginActivity.class);
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