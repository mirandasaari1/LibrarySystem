package com.example.mirandasaari.librarysystem;

/*
Abstract:Java file to store all the books information
 */

public class Books {

    //variables

    private String _title;
    private String _author;
    private String _isbn;
    private String _hourlyFee;
    private String _onHold;

    //empty constructor
    public Books(){
    }


    //constructor
    public Books(String book_title, String author, String isbn, String hourly_fee){
        this._title=book_title;
        this._author=author;
        this._isbn=isbn;
        this._onHold="Available";
        this._hourlyFee=hourly_fee;
    }


    // getting title
    public String getTitle(){
        return this._title;
    }

    // setting title
    public void setTitle(String book_title){
        this._title= book_title;
    }

    //getting author
    public String getAuthor(){
        return this._author;
    }

    //setting author
    public void setAuthor(String author) {
        this._author = author;
    }

    //getting ISBN
    public String getIsbn(){return this._isbn;}

    //setting ISBN
    public void setIsbn(String isbn){
        this._isbn=isbn;
    }

    //setting onHold
    public void setOnHold(String onHold){
        this._onHold=onHold;
    }

    //getting onHold
    public String getOnHold(){return this._onHold;}

    //getting hourly fee
    public String getHourlyFee(){
        return this._hourlyFee;
    }

    //setting hourly fee
    public void setHourlyFee(String hourly_fee){
        this._hourlyFee=hourly_fee;
    }

    // Adding new book
    public void addBook(Books book) {}


    // Deleting single book
    public void deleteBook(Books book) {}

    public String toString(){
        return ("Title: " + _title +" Author: " + _author + " ISBN:  " + _isbn + " Fee per Hour: $" + _hourlyFee);
    }

}
