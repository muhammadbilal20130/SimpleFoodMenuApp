package com.example.foodmenuapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
