package com.example.foodmenuapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenuapp.DBhelper;
import com.example.foodmenuapp.OrderCartActivity;
import com.example.foodmenuapp.R;
import com.example.foodmenuapp.model.OrdersModel;

import java.util.ArrayList;

public class OrdersSampleAdapter extends RecyclerView.Adapter<OrdersSampleAdapter.viewHolder> {
    ArrayList<OrdersModel> ordersModelArrayList;
    Context context;

    public OrdersSampleAdapter(ArrayList<OrdersModel> ordersModelArrayList, Context context) {
        this.ordersModelArrayList = ordersModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final OrdersModel ordersModel=ordersModelArrayList.get(position);
        holder.orderDishImage.setImageResource(ordersModel.getImage());
        holder.OrderDishName.setText(ordersModel.getOrderDishName());
        holder.OrderDishID.setText(ordersModel.getOrderDishID());
        holder.OrderDishPrice.setText(ordersModel.getOrderDishPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,OrderCartActivity.class);
                i.putExtra("id",Integer.parseInt(ordersModel.getOrderDishID()));
                //for updating value put 2 value as a flag
                i.putExtra("updated",2);
                context.startActivity(i);
            }
        });
        //onlong press function will delete order
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).
                        setTitle("Delete order").
                        setMessage("Do you want to delete order? ").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBhelper dBhelper=new DBhelper(context);
                                if(dBhelper.deleteOrder(ordersModel.getOrderDishID())>0){
                                    Toast.makeText(context,"Order deleted",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersModelArrayList.size();
    }

    public static class  viewHolder extends RecyclerView.ViewHolder{
        ImageView orderDishImage;
        TextView OrderDishName,OrderDishID,OrderDishPrice;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderDishImage=itemView.findViewById(R.id.IVorderImage);
            OrderDishName=itemView.findViewById(R.id.TVOrderDishName);
            OrderDishID=itemView.findViewById(R.id.TVorderDishId);
            OrderDishPrice=itemView.findViewById(R.id.TVorderDishPrice);
        }
    }
}
