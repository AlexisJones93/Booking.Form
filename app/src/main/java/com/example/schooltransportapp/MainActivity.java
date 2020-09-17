package com.example.schooltransportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText emailId, passWord,firstName, surnameName, phoneNumber;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailAddress);
        passWord = findViewById(R.id.pWord);
        btnSignUp = findViewById(R.id.register);
        signIn = findViewById(R.id.btnSignIn);
        //firstName = findViewById(R.id.fName);
       // surnameName = findViewById(R.id.sName);
       // phoneNumber = findViewById(R.id.PNumber);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity. this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = passWord.getText().toString();
               // final String fNames = firstName.getText().toString();
               // final String sNames = surnameName.getText().toString();
              //  final String phone = phoneNumber.getText().toString();


               // if(fNames.isEmpty()){
              //      firstName.setError("Please enter your first name");
              //      firstName.requestFocus();

             //   }
              //  else if(sNames.isEmpty()) {
              //      surnameName.setError("Please enter your email address");
              //      surnameName.requestFocus();
             //   }
                if(email.isEmpty()) {
                    emailId.setError("Please enter your email address");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    passWord.setError("Please enter a password");
                    passWord.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Both Fields are required",Toast.LENGTH_SHORT).show();
                }

                else{
                    mAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Authentication successful.",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                    }
                                    else {
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(MainActivity.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }



                            });

                }


            }
        });
    }
}
