package com.example.foodmenuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        menuList.add(new MenuModel(R.drawable.pizza,"Piza","1299","This is description"));
        menuList.add(new MenuModel(R.drawable.chicken_roast,"Chciken Roast","699","This is description"));
        menuList.add(new MenuModel(R.drawable.zinger_burger,"Zinger","599","This is description"));
        menuList.add(new MenuModel(R.drawable.steam_roast,"Steam Roast","899","This is description"));
        menuList.add(new MenuModel(R.drawable.biryani,"Biryani","249","This is description"));
        //Setting Recycler View adatper
        RecyclerView recyclerView=findViewById(R.id.recylerView);
        MenuAdapter adapter=new MenuAdapter(menuList,this);
//      recyclerView.setLayoutManager(recyclerView.getLayoutManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    //creating menu xml resourse menu and inflating menu in onCreateOptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=new Intent(this,OrderSample.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}