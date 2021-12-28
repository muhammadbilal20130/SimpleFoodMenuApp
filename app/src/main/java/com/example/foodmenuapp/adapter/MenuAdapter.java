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
import com.example.foodmenuapp.model.MenuModel;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    ArrayList<MenuModel> menuList;
    Context context;

    public MenuAdapter(ArrayList<MenuModel> menuList,Context context) {
        this.menuList = menuList;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.menulayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  MenuModel menuModel=menuList.get(position);
        holder.IVfoodPic.setImageResource(menuModel.getImage());
        holder.TVfoodName.setText(menuModel.getName());
        holder.TVprice.setText(menuModel.getPrice());
        holder.TVdescription.setText(menuModel.getDesciption());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView TVfoodName,TVprice,TVdescription;
        ImageView IVfoodPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IVfoodPic=itemView.findViewById(R.id.IVfood);
            TVfoodName=itemView.findViewById(R.id.TVfoodName);
            TVprice=itemView.findViewById(R.id.TVprice);
            TVdescription=itemView.findViewById(R.id.TVdescription);
        }
    }
}
