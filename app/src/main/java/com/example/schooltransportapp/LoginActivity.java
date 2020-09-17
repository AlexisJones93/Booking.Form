package com.example.schooltransportapp;

import androidx.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity {

    EditText signInEmail, signInPword;
    Button signInButton;
    FirebaseAuth mAuth;
    TextView RegButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.eAddress);
        signInPword = findViewById(R.id.pWord);
        signInButton = findViewById(R.id.signButton);
        RegButton = findViewById(R.id.RegButton);
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }



        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity. this, MainActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sEmail = signInEmail.getText().toString();
                String sPwd = signInPword.getText().toString();

                if(sEmail.isEmpty() && sPwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Both Fields are required",Toast.LENGTH_SHORT).show();
                }
                else if(sEmail.isEmpty()) {
                    signInEmail.setError("Please enter your email address");
                    signInEmail.requestFocus();
                }
                else if(sPwd.isEmpty()){
                    signInPword.setError("Please enter a password");
                    signInPword.requestFocus();
                }
                mAuth.signInWithEmailAndPassword(sEmail, sPwd)
                        .addOnCompleteListener(LoginActivity. this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, activity changed to home.
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
});
    }
}