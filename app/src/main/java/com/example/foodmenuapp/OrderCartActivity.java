package com.example.foodmenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderCartActivity extends AppCompatActivity {

    //From this activity user can place order and update order comming from Order Sample Activity

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
        orderNumbers=Integer.parseInt(foodQuantity.getText().toString());

        //increment and decrement food order quantity by plus and minus button clicks
        //for incredmenting food quantity
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderNumbers++;
                Log.v("ON in event",String.valueOf(orderNumbers));
                int temp=calculatePrice(orderNumbers);
//                Log.v("temp value ",String.valueOf(temp));
                foodPrice.setText(String.valueOf(temp));
                foodQuantity.setText(String.valueOf(orderNumbers));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(orderNumbers>0){
                    orderNumbers--;
                    String tmp =String.valueOf(orderNumbers);
                    foodPrice.setText(String.valueOf(calculatePrice(orderNumbers)));
                    foodQuantity.setText(tmp);
                }
            }
        });
        //saves new order in database
        if(getIntent().getIntExtra("updated",1)==1){

        //saves data in database

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int image=getIntent().getIntExtra("foodImage",0);
//              int price=Integer.parseInt(getIntent().getStringExtra("foodPrice"));
                int price=calculatePrice(orderNumbers);
                String name=getIntent().getStringExtra("foodName");
                String description=getIntent().getStringExtra("foodDescription");
                int quantity=Integer.parseInt(foodQuantity.getText().toString());
                foodImage.setImageResource(image);
                foodName.setText(name);
                foodDescription.setText(description);
                foodPrice.setText(String.format("%d",price));
                boolean isInserted=dBhelper.insertOrder(orderName.getText().toString(),
                        orderPhoneNumber.getText().toString(),quantity,price,image,description,name);
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
            final int image=cursor.getInt(5);//5
            foodImage.setImageResource(image);
            foodName.setText(cursor.getString(7));//7
            foodDescription.setText(cursor.getString(6));//6
            foodPrice.setText(String.format("%d",cursor.getInt(3)));//3
            foodQuantity.setText(String.valueOf(cursor.getInt(4)));//4
            orderNumbers=cursor.getInt(4);
            Log.v("This Price",String.valueOf(cursor.getInt(4)));
            Log.v("ordernumber = ",String.valueOf(orderNumbers));
            Log.v("This quantity",String.format("%d",cursor.getInt(3)));

            orderName.setText(cursor.getString(1));//1
            orderPhoneNumber.setText(cursor.getString(2));//2

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

    public int calculatePrice(int quantity){
        int setPrice=Integer.parseInt(getIntent().getStringExtra("foodPrice"))*quantity;
        return setPrice;
    }


}