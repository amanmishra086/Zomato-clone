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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.viewHolder> {

    private ArrayList<ModelCity> arrayList;
    Context context;

    public CityAdapter(ArrayList<ModelCity> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public CityAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cities, parent, false);
        return new CityAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityAdapter.viewHolder holder, int position) {
        final ModelCity modelCity = arrayList.get(position);
        //holder.imageView.setImageResource(Integer.parseInt(modelCity.getUrl()));
        String imageUri=modelCity.getUrl();
        Picasso.with(context).load(imageUri).fit().into(holder.imageView);
        holder.city.setText(modelCity.getCity());
        holder.country.setText(modelCity.getCountry());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=modelCity.getId();
                Intent intent=new Intent(context,RestaurantActivity.class);
                intent.putExtra("id",id);
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
        TextView city,country;
        LinearLayout linearLayout;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.flagImage);
            city=itemView.findViewById(R.id.city);
            country=itemView.findViewById(R.id.country);
            linearLayout=itemView.findViewById(R.id.cityLinearlayout);


        }


    }

}