package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file which handles the library systems database
 */

        import java.util.ArrayList;
        import java.util.List;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 23;

    // Database Name
    private static final String DATABASE_NAME = "LibrarySystem";


    // table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_TRANSACTIONS = "transactions";

    //  Columns names
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TYPE = "transaction_type";
    private static final String KEY_CURRENT_TIME = "current_time";

    private static final String KEY_BOOK_TITLE = "book_title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_HOURLY_FEE = "hourly_fee";
    private static final String KEY_ON_HOLD= "on_hold";

    private static final String KEY_TRANS_TYPE = "transaction_type";
    private static final String KEY_STUD_USERNAME= "stud_username";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PICKUP_DATE = "pickup_date";
    private static final String KEY_PICKUP_TIME = "pickup_time";
    private static final String KEY_RETURN_DATE = "return_date";
    private static final String KEY_RETURN_TIME= "return_time";
    private static final String KEY_CHARGE = "charge";
    private static final String KEY_TRANS_TIME_STAMP = "trans_time_stamp";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("enter", "enter");
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "( "
                + "username TEXT, "
                + "password TEXT, "
                + "transaction_type TEXT, "
                + "current_time TEXT)";


        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "( "
                + "book_title TEXT, "
                + "author TEXT, "
                + "isbn TEXT, "
                + "on_hold TEXT, "
                + "hourly_fee TEXT)";

        String CREATE_TRANSACTIONS_TABLE="CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + "transaction_type TEXT, "
                + "stud_username TEXT, "
                +  "title TEXT, "
                + " pickup_date TEXT, "
                + " pickup_time TEXT, "
                + "return_date TEXT, "
                + "return_time TEXT, "
                + "charge TEXT, "
                + "trans_time_stamp TEXT)";


        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_BOOKS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
        Log.d("end", "end");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);

        // Create tables again
        onCreate(db);
    }


    //user table functions
    // Adding new user
    public void addUser(User user) {
        Log.d("object", user.toString());
        Log.d("enter1", "enter1");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername()); // username
        values.put(KEY_PASSWORD, user.getPassword()); // password
        values.put(KEY_TYPE, "New Account"); // account type
        values.put(KEY_CURRENT_TIME, user.getTimeStamp()); // time stamp
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
        Log.d("end1", "end1");
    }

    //getting a user
    public boolean getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String Query = "SELECT  * FROM " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = " + username;
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_USERS + " WHERE username = '" + username + "'", null);
        boolean exist=(res.getCount()>0);
        res.close();
        return exist;
    }

    public List<User> getAllUsers() {
        List<User> UsersList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User users = new User();
                users.setUsername(cursor.getString(0));
                users.setPassword(cursor.getString(1));
                users.setTransactionType(cursor.getString(2));
                users.setTimeStamp(cursor.getString(3));
                // Adding transaction to list
                UsersList.add(users);
            } while (cursor.moveToNext());
        }
        return UsersList;
    }

    //book table functions
    // Getting All Books
    //use for place hold function
    public List<Books> getAllBooks() {
        List<Books> bookList = new ArrayList<Books>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Books books = new Books();
                books.setTitle(cursor.getString(0));
                books.setAuthor(cursor.getString(1));
                books.setIsbn(cursor.getString(2));
                books.setOnHold(cursor.getString(3));
                books.setHourlyFee(cursor.getString(4)); //do thisor get normal?
                // Adding book to list
                bookList.add(books);
            } while (cursor.moveToNext());
        }
        return bookList;
    }

    //add new book
    public void addBook(Books books) {
        Log.d("enter1", "enter1");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOOK_TITLE, books.getTitle()); // book title
        values.put(KEY_AUTHOR, books.getAuthor()); // author
        values.put(KEY_ISBN, books.getIsbn()); // isbn
        values.put(KEY_ON_HOLD, books.getOnHold());//on hold
        values.put(KEY_HOURLY_FEE, books.getHourlyFee()); // hourly fee
        // Inserting Row
        db.insert(TABLE_BOOKS, null, values);
        db.close(); // Closing database connection
        Log.d("end1", "end1");
    }



    //transaction tables functions
    //get all transactions
    public List<Transactions> getAllTransactions() {
        List<Transactions> transactionsList = new ArrayList<Transactions>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transactions transactions = new Transactions();
                transactions.setTransType(cursor.getString(0));
                transactions.setStudUsername(cursor.getString(1));
                transactions.setTitle(cursor.getString(2));
                transactions.setPickupDate(cursor.getString(3));
                transactions.setPickupTime(cursor.getString(4));
                transactions.setReturnDate(cursor.getString(5));
                transactions.setReturnTime(cursor.getString(6));
                transactions.setTotalCharge(cursor.getString(7));
                transactions.setTransTimeStamp(cursor.getString(8));
                // Adding transaction to list
                transactionsList.add(transactions);
            } while (cursor.moveToNext());
        }
        return transactionsList;
    }


    //get single users transactions
    public List<Transactions> getUserTransactions(String username) {
        List<Transactions> userTransactionsList = new ArrayList<Transactions>();
        // Select All Query
        //CHECK QUERY
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS + " WHERE stud_username = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transactions transactions = new Transactions();
                transactions.setTransType(cursor.getString(0));
                transactions.setStudUsername(cursor.getString(1));
                transactions.setTitle(cursor.getString(2));
                transactions.setPickupDate(cursor.getString(3));
                transactions.setPickupDate(cursor.getString(4));
                transactions.setReturnDate(cursor.getString(5));
                transactions.setReturnTime(cursor.getString(6));
                transactions.setTotalCharge(cursor.getString(7));
                transactions.setTransTimeStamp(cursor.getString(8));
                // Adding transaction to list
                userTransactionsList.add(transactions);
            } while (cursor.moveToNext());
        }
        return userTransactionsList;
    }

    // Adding new transaction
    public void addTransaction(Transactions transactions) {
        Log.d("enter1", "enter1");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRANS_TYPE, transactions.getTransType()); // trans type
        values.put(KEY_STUD_USERNAME, transactions.getStudUsername()); // username
        values.put(KEY_TITLE,transactions.getTitle()); // book title
        values.put(KEY_PICKUP_DATE, transactions.getPickupDate()); // pickup date
        values.put(KEY_PICKUP_TIME, transactions.getPickupTime()); // pickup date
        values.put(KEY_RETURN_DATE, transactions.getReturnDate()); // return date
        values.put(KEY_RETURN_TIME, transactions.getReturnTime()); // return date
        values.put(KEY_CHARGE,transactions.getTotalCharge()); // total charge
        values.put(KEY_TRANS_TIME_STAMP, "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"); // time stamp???
        // Inserting Row
        db.insert(TABLE_TRANSACTIONS, null, values);
        db.close(); // Closing database connection
        Log.d("end1", "end1");
    }
}
