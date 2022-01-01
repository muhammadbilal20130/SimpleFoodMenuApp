package com.example.foodmenuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    final static String DBname="foodmenuDB.db";
    final static int DBversion=2;
    //make constructor
    public DBhelper(@Nullable Context context) {
        super(context, DBname, null, DBversion);
    }
    //override methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        //add query here
        db.execSQL("create table orders (id integer primary key autoincrement," +
                "Username text,Userphone text,price text,quantity integer,image integer,description text,foodName text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //when db is updated add code here
        db.execSQL("Drop table if exists orders");
        onCreate(db);
    }
    //a function that insert values in database creaated in onCreate function
    public boolean insertOrder(String name,String phoneNumber,int quantity,int price,int image,String desc,String foodName){
        //create objects for SQLiteDatabase and ContentValues class
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put("Username",name);
        values.put("Userphone",phoneNumber);
        values.put("quantity",quantity);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);

        //insert function is a bool function it must return a value either 0 or 1 so that
        // why i used id variable and it also help to check weather data is added or not into database
        long id=sqLiteDatabase.insert("orders",null,values);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }
}
