package com.example.schooltransportapp;

import android.widget.EditText;

public class User {
    public String userID,fNames,sNames;

    public User(){

    }

    public User (String fNames, String sNames, String userID){
        this.fNames = fNames;
        this.sNames = sNames;
        this.userID = userID;
    }

    public void fnames(EditText cFirstName) {
    }
}

