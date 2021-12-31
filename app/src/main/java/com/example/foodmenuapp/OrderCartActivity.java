package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderCartActivity extends AppCompatActivity {

    ImageView foodImage;
    TextView foodName,foodDescription,foodPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);
        foodImage=findViewById(R.id.TVorderFoodImage);
        foodName=findViewById(R.id.TVorderFoodName);
        foodDescription=findViewById(R.id.TVorderFoodDescription);
        foodPrice=findViewById(R.id.TVorderFoodPrice);

        int image=getIntent().getIntExtra("foodImage",0);
        int price=Integer.parseInt(getIntent().getStringExtra("foodPrice"));
        String name=getIntent().getStringExtra("foodName");
        String description=getIntent().getStringExtra("foodDescription");

       foodImage.setImageResource(image);
        foodName.setText(name);
        foodDescription.setText(description);
        foodPrice.setText(String.format("%d",price));

    }
}