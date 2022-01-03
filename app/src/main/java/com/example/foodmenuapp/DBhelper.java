package com.example.foodmenuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodmenuapp.model.OrdersModel;

import java.util.ArrayList;

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
        /* Here is indexing in database helps in reading data from cursor class
        id > 0
        Username > 1
        Userphone > 2
        price > 3
        quantity > 4
        image > 5
        description > 6
        foodName > 7
        * */
        //add query here
        db.execSQL("create table orders (id integer primary key autoincrement," +
                "Username text," +
                "Userphone text," +
                "price text," +
                "quantity integer," +
                "image integer," +
                "description text," +
                "foodName text)");
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

    //a function that get all orders from database and shows in order Sample activities by calling function
    ArrayList<OrdersModel> getOrdersFromDB(){

        ArrayList<OrdersModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodName,Price,image from orders",null);
        if (cursor.moveToFirst()){
            do{
                OrdersModel ordersModel=new OrdersModel();
                ordersModel.setOrderDishID(cursor.getInt(0)+"");
                ordersModel.setOrderDishName(cursor.getString(1));
                ordersModel.setOrderDishPrice(cursor.getInt(2)+"");
                ordersModel.setImage(cursor.getInt(3));
                orders.add(ordersModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return orders;
    }
    public Cursor getOrderByID(int id){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from orders where id ="+id,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    //Update order
    public boolean updareOrder(String name,String phoneNumber,int quantity,int price,int image,String desc,String foodName,int userID){
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


        long id=sqLiteDatabase.update("orders",values,"id="+userID,null);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }
    //delete orders by 1 item
    public int deleteOrder(String id){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.delete("orders","id="+id,null);
    }
}
