package com.example.zomato;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.viewHolder> {

    private ArrayList<ModelRestaurant> arrayList;
    Context context;

    public RestaurantAdapter(ArrayList<ModelRestaurant> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public RestaurantAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant, parent, false);
        return new RestaurantAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantAdapter.viewHolder holder, int position) {
        final ModelRestaurant modelRestaurant = arrayList.get(position);
        holder.imageView.setImageResource(modelRestaurant.getImage());
        holder.name.setText(modelRestaurant.getName());
        holder.address.setText(modelRestaurant.getAddress());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RestaurantDetails.class);
                intent.putExtra("url",modelRestaurant.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,address;
        LinearLayout linearLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);

            linearLayout=itemView.findViewById(R.id.restLinear);

        }


    }

}
