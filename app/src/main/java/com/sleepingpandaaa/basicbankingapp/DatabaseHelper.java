package com.sleepingpandaaa.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9111234561644,'Yash Dubey',12000.00,'dyash9149@gmail.com','1234567890','ABC09876543')");
        db.execSQL("insert into user_table values(9111234563567,'Ankur',12382.67,'ankurpandey21.com','1234567891','BCA98765432')");
        db.execSQL("insert into user_table values(9111234568524,'Rohit',3359.73,'rohitpandey12@gmail.com','1234567892','CAB87654321')");
        db.execSQL("insert into user_table values(9111234562685,'Yash',2300.12,'yashsakpal14@gmail.com','1234567893','ABC76543210')");
        db.execSQL("insert into user_table values(9111234563473,'Shubham',2203.48,'dubeyshubhu08@gmail.com','1234567894','BCA65432109')");
        db.execSQL("insert into user_table values(9111234562567,'Pranay',9145.16,'chavanpranay@gmail.com','1234567895','CAB54321098')");
        db.execSQL("insert into user_table values(9111234562463,'Nikhil',1236.00,'pawarnikhil29@gmail.com','1234567896','ABC43210987')");
        db.execSQL("insert into user_table values(9111234563573,'Varun',8157.22,'nayakvarun@gmail.com','1234567897','BCA32109876')");
        db.execSQL("insert into user_table values(9111234563672,'Pratik',1398.46,'mhaskepratik@gmail.com','1234567898','CAB21098765')");
        db.execSQL("insert into user_table values(9111234563664,'Hardik',1273.90,'hardikpandya2@gmail.com','1234567899','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
