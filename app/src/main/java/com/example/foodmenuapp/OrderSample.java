package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodmenuapp.adapter.MenuAdapter;
import com.example.foodmenuapp.adapter.OrdersSampleAdapter;
import com.example.foodmenuapp.model.OrdersModel;

import java.util.ArrayList;

public class OrderSample extends AppCompatActivity {
//    This activity shows orders that are placed by user and it shows orders from database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sample);

        DBhelper dBhelper=new DBhelper(this);
        ArrayList<OrdersModel> ordersModelArrayList=dBhelper.getOrdersFromDB();
        RecyclerView recyclerView=findViewById(R.id.OrderRecyclerView);
        OrdersSampleAdapter adapter=new OrdersSampleAdapter(ordersModelArrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}