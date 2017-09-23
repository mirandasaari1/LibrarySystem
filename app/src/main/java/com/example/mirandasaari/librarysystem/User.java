package com.example.mirandasaari.librarysystem;

/*
Abstract:java file which hold the users class/table so new ones can be added
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class User {
    //variables
    private String _username;
    private String _password;
    private String _transactionType;
    private String _timeStamp;

    //empty constructor
    public User(){
    }


    //constructor
//    public User(String username, String password, String transaction_type, String time_stamp){
//        this._username=username;
//        this._password=password;
//        this._transactionType=transaction_type;
//        this._timeStamp=time_stamp;
//    }

    //constructor
    public User(String username, String password){
        this._username=username;
        this._password=password;
        this._transactionType="New Account";
        this._timeStamp=getDateTime();
    }

    //get current time
    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date= new Date();
        return dateFormat.format(date);
    }


    // getting username
    public String getUsername(){

        return this._username;
    }

    // setting username
    public void setUsername(String username){

        this._username= username;
    }

    //getting password
    public String getPassword(){
        return this._password;
    }

    //setting password
    public void setPassword(String password)
    {
        this._password = password;
    }

    //getting transaction type
    public String getTransactionType(){
        return this._transactionType;
    }

    //setting transaction type
    public void setTransactionType(String transaction_type){
        this._transactionType=transaction_type;
    }

    //getting time stamps
    public String getTimeStamp(){

        return this._timeStamp;
    }

    //setting timeStamp
    public void setTimeStamp(String current_time){

        this._timeStamp=getDateTime();
    }

    // Adding new user
    public void addUser(User user) {}


    // Deleting single user
    public void deleteUser(User user) {}

    public String toString(){
        return ("username" + _username +"password " + _password + "trans type " + _transactionType + "current time " + _timeStamp);
    }

}
