package com.example.schooltransportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateAndDelete1 extends AppCompatActivity {

    EditText cName, cSchool;
    Button delButton, savButton;
    DatabaseReference dRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete1);

        cName = findViewById(R.id.childName);
        cSchool = findViewById(R.id.childSchool);
        delButton = findViewById(R.id.delete_btn);
        savButton = findViewById(R.id.save_btn);
        //String  key = getIntent().getExtras().get("user").toString();
        dRef = FirebaseDatabase.getInstance().getReference();

        cName.setText(getIntent().getStringExtra("Firstname"));
        cSchool.setText(getIntent().getStringExtra("School"));
    }
}