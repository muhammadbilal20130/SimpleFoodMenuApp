package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderCartActivity extends AppCompatActivity {

    ImageView foodImage,plusBtn,minusBtn;
    TextView foodName,foodDescription,foodPrice,foodQuantity;
    Button orderBtn;
    EditText orderName,orderPhoneNumber;
    int orderNumbers;
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
        plusBtn=findViewById(R.id.addOrderQuantityBtn);
        minusBtn=findViewById(R.id.decreaseOderQuantityBtn);
        final DBhelper dBhelper=new DBhelper(this);

        //increment and decrement food order quantity by plus and minus button clicks
        //for incredmenting food quantity
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumbers=Integer.parseInt(foodQuantity.getText().toString());
                orderNumbers++;
                String tmp = String.valueOf(orderNumbers);
                foodQuantity.setText(tmp);
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumbers=Integer.parseInt(foodQuantity.getText().toString());
                if(orderNumbers>0){
                    orderNumbers--;
                    String tmp = String.valueOf(orderNumbers);
                    foodQuantity.setText(tmp);
                }
            }
        });
        //saves new order in database
        if(getIntent().getIntExtra("updated",0)==1){
        int image=getIntent().getIntExtra("foodImage",0);
        int price=Integer.parseInt(getIntent().getStringExtra("foodPrice"));
        String name=getIntent().getStringExtra("foodName");
        String description=getIntent().getStringExtra("foodDescription");

        foodImage.setImageResource(image);
        foodName.setText(name);
        foodDescription.setText(description);
        foodPrice.setText(String.format("%d",price));
        //saves data in database

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
//updates in database
    }else{
        int id=getIntent().getIntExtra("id",0);
        Cursor cursor=dBhelper.getOrderByID(id);
            Toast.makeText(this, "Qty"+cursor.getInt(4), Toast.LENGTH_LONG).show();
            final int image=cursor.getInt(5);
            foodImage.setImageResource(image);
            foodName.setText(cursor.getString(7));
            foodDescription.setText(cursor.getString(6));
            foodPrice.setText(String.format("%d",cursor.getInt(3)));
            foodQuantity.setText(String.valueOf(cursor.getInt(4)));
            orderName.setText(cursor.getString(1));
            orderPhoneNumber.setText(cursor.getString(2));

            orderBtn.setText("Update Order");
            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isUpdated=dBhelper.updareOrder(
                            orderName.getText().toString(),
                            orderPhoneNumber.getText().toString(),
                            Integer.parseInt(foodQuantity.getText().toString()),
                            Integer.parseInt(foodPrice.getText().toString()),
                            image,
                            foodDescription.getText().toString(),
                            foodName.getText().toString(),
                            id);
                    if(isUpdated){
                        Toast.makeText(getApplicationContext(),"Order Updated",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Order not Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}