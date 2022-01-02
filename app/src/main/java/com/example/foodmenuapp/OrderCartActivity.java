package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderCartActivity extends AppCompatActivity {

    ImageView foodImage;
    TextView foodName,foodDescription,foodPrice,foodQuantity;
    Button orderBtn;
    EditText orderName,orderPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);
        foodImage=findViewById(R.id.TVorderFoodImage);
        foodName=findViewById(R.id.TVorderFoodName);
        foodDescription=findViewById(R.id.TVorderFoodDescription);
        foodPrice=findViewById(R.id.TVorderFoodPrice);
        orderBtn=findViewById(R.id.orderBtn);
        orderName=findViewById(R.id.ETorderName);
        orderPhoneNumber=findViewById(R.id.ETorderPhoneNumber);
        foodQuantity=findViewById(R.id.orderFoodQuantity);
        int foodQ=Integer.parseInt(foodQuantity.getText().toString()); //here food Quantity converted to integer

        int image=getIntent().getIntExtra("foodImage",0);
        int price=Integer.parseInt(getIntent().getStringExtra("foodPrice"));
        String name=getIntent().getStringExtra("foodName");
        String description=getIntent().getStringExtra("foodDescription");

        foodImage.setImageResource(image);
        foodName.setText(name);
        foodDescription.setText(description);
        foodPrice.setText(String.format("%d",price));


        final DBhelper dBhelper=new DBhelper(this);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=dBhelper.insertOrder(orderName.getText().toString(),
                        orderPhoneNumber.getText().toString(),foodQ,price,image,description,name);
                if(isInserted){
                    Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Data  not inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}