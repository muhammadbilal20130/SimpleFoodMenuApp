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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sample);
        ArrayList<OrdersModel> ordersModelArrayList=new ArrayList<>();
        ordersModelArrayList.add(new OrdersModel(R.drawable.pizza,"Pizza","AC-11","425"));
        ordersModelArrayList.add(new OrdersModel(R.drawable.pizza,"Pizza","AC-11","425"));
        ordersModelArrayList.add(new OrdersModel(R.drawable.pizza,"Pizza","AC-11","425"));
        RecyclerView recyclerView=findViewById(R.id.OrderRecyclerView);
        OrdersSampleAdapter adapter=new OrdersSampleAdapter(ordersModelArrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}