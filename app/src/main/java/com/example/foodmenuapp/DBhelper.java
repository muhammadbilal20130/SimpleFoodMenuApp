package com.example.foodmenuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    final static String DBname="foodmenuDB.db";
    final static int DBversion=1;
    public DBhelper(@Nullable Context context) {
        super(context, DBname, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders (id int primary key autoincrement," +
                "Username text,Userphone text,price text,quantity int,image int,description text,foodName text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists orders");
        onCreate(db);
    }
    public boolean insertOrder(String name,String phoneNumber,int price,int image,String desc,String foodName){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("Username",name);
        values.put("Userphone",phoneNumber);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        long id=sqLiteDatabase.insert("orders",null,values);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }
}
