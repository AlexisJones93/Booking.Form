package com.example.schooltransportapp;

public class user {
    private String Firstname;
    private String School;
    private String UID;
  // private String key;
    public user (){

    }

    public user (String Firstname, String School) {
        this.Firstname = Firstname;
        this.School =School;
    }

    public String toString(){
      return "Child's name: " + this.Firstname + "        Child's School: " + this.School;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public void setSchool(String school) {
        this.School = school;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

  //  public void setKey(String key) {
    //    this.key = key;
   // }

    public String getFirstname() {
        return Firstname;
    }

    public String getSchool() {
        return School;
    }
/*
    public user(String Firstname, String School, String UID, String key) {
      //  this.UID = getUID();
      //  this.key = getKey();
        this.Firstname = Firstname;
        this.School = School;
        }
 */


    public String getUID() {
        return UID;
    }




    //  public String getKey() {
  //      return getKey();

  //  }
}



