package com.example.mirandasaari.librarysystem;

/*
Abstract:java file which hold the transaction class/table so new ones can be added
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transactions {
    private String _stud_username;
    private String _transaction_type;
    private String _title;
    private String _pickup_date;
    private String _return_date;
    private String _total_charge;
    private String _trans_time_stamp;
    private String _pickup_time;
    private String _return_time;

    public Transactions(){

    }

    public Transactions(String studUsername, String transType, String title, String pickupDate, String pickupTime, String returnDate, String returnTime, String charge){
        this._stud_username=studUsername;
        this._transaction_type=transType;
        this._title=title;
        this._pickup_date=pickupDate;
        this._pickup_time=pickupTime;
        this._return_date=returnDate;
        this._return_time=returnTime;
        this._total_charge=charge;
        this._trans_time_stamp=getTransTimeStamp();;
    }

    public Transactions(String studUsername, String transType){
        this._stud_username=studUsername;
        this._transaction_type=transType;
        this._title=null;
        this._pickup_date=null;
        this._pickup_time=null;
        this._return_date=null;
        this._return_time=null;
        this._total_charge=null;
        this._trans_time_stamp=getTransTimeStamp();

    }

    public Transactions(String studUsername, String transType, String title, String pickupDate, String pickupTime, String returnDate, String returnTime){
        this._stud_username=studUsername;
        this._transaction_type=transType;
        this._title=title;
        this._pickup_date=pickupDate;
        this._pickup_time=pickupTime;
        this._return_date=returnDate;
        this._return_time=returnTime;
        this._total_charge="0.00";
        this._trans_time_stamp=getDateTime();
    }





    //get current time
    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date= new Date();
        return dateFormat.format(date);
    }



    public String getStudUsername(){
        return this._stud_username;
    }

    public void setStudUsername(String stud_username){
        this._stud_username=stud_username;
    }

    public String getTransType(){
        return this._transaction_type;
    }

    public void setTransType(String transaction_type){
        this._transaction_type=transaction_type;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String title){
        this._title=title;
    }

    public String getPickupDate(){
        return this._pickup_date;
    }

    public void setPickupDate(String pickupDate){
        this._pickup_date=pickupDate;
    }

    public String getReturnDate(){
        return this._return_date;
    }

    public void setReturnDate(String returnDate){
        this._return_date=returnDate;
    }

    public String getTotalCharge(){
        return this._total_charge;
    }

    public void setTotalCharge(String totalCharge){
        this._total_charge=totalCharge;
    }

    public String getTransTimeStamp()
    {
        return this._trans_time_stamp;
    }

    public final void setTransTimeStamp(String transTimeStamp){

        this._trans_time_stamp=getDateTime();
    }

    public String getPickupTime(){
        return this._pickup_time;
    }

    public void setPickupTime(String pickupTime){this._pickup_time=pickupTime; }





    public String getReturnTime(){
        return this._return_time;
    }

    public void setReturnTime(String returnTime){
        this._return_time=returnTime;}


    public String toString()
    {
        if(_transaction_type.equals("New Account")){
            return ("Transaction Type: " + _transaction_type + "\n" +
                    "Username: " + _stud_username + "\n" +
                    "Transaction Time: " + _trans_time_stamp + "\n");
        }
        else {
            return ("Transaction Type: " + _transaction_type + "\n" +
                    "Username: " + _stud_username + "\n" +
                    "Pickup Date: " + _pickup_date + "\n" +
                    "Pickup Time: " + _pickup_time + "\n" +
                    "Return Date: " + _return_date + "\n" +
                    "Return Time: " + _return_time + "\n" +
                    "Book Title: " + _title + "\n" +
                    "Transaction Time: " + _trans_time_stamp + "\n");
        }
    }

    }





