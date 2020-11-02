package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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


    String UID,key;
    FirebaseAuth mAuth;
    //TextView txt1,txt2;
    ListView listView1;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_booking);

        /* use with working one set of data code
        txt1 = findViewById(R.id.dFirstname);
        txt2 = findViewById(R.id.dSchool);
         */

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user1 = mAuth.getCurrentUser();
        UID = user1.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user").child(UID);

      //  key = ref.child(UID).getKey();

       listView1 = findViewById(R.id.listViewtxt);
       arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
       listView1.setAdapter(arrayAdapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
             String value = snapshot.getValue(user.class).toString();
             arrayList.add(value);
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

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        });
    }
}