package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodmenuapp.adapter.MenuAdapter;
import com.example.foodmenuapp.model.MenuModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MenuModel> menuList=new ArrayList<>();
        menuList.add(new MenuModel(R.drawable.buger,"Burger","399","This is description"));
        menuList.add(new MenuModel(R.drawable.buger,"Piza","1299","This is description"));
        menuList.add(new MenuModel(R.drawable.buger,"Chciken Roast","699","This is description"));
        menuList.add(new MenuModel(R.drawable.buger,"Zinger","599","This is description"));
        menuList.add(new MenuModel(R.drawable.buger,"Steam Roast","899","This is description"));
        menuList.add(new MenuModel(R.drawable.buger,"Biryani","249","This is description"));
        //Setting Recycler View adatper
        RecyclerView recyclerView=findViewById(R.id.recylerView);
        MenuAdapter adapter=new MenuAdapter(menuList,this);
//      recyclerView.setLayoutManager(recyclerView.getLayoutManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}