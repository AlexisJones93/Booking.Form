package com.example.schooltransportapp;

//creates a user for the database and passes information through getter and setters
public class user {
    private String Firstname;
    private String School;

    public String getChildNumber() {
        return ChildNumber;
    }

    public void setChildNumber(String childNumber) {
        ChildNumber = childNumber;
    }

    private String ChildNumber;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private String Date;
    private String Time;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    private String Gender;

    public String getYearGroup() {
        return YearGroup;
    }

    public void setYearGroup(String yearGroup) {
        YearGroup = yearGroup;
    }

    private String YearGroup;
    private String UID;
  // private String key;
    public user (){

    }

    public user (String Firstname, String School, String yearGroup, String gender, String date, String time, String childNumber) {
        this.Firstname = Firstname;
        this.School =School;
        this.YearGroup=yearGroup;
        this.Gender=gender;
        this.Date=date;
        this.Time = time;
        this.ChildNumber = childNumber;
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



