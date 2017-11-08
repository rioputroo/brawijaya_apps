package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by nb-rioputro on 11/3/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Food> foodsList;
    NumberFormat rupiahFormat;
    int pos = 1;
    Context context;

    public DataAdapter(Context context) {
        this.context = context;
    }

    public DataAdapter(List<Food> jobsList) {
        this.foodsList = jobsList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Food food = foodsList.get(position);

        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        String rupiah = rupiahFormat.format(Double.parseDouble(food.getFoodPrice()));

        final String foodId = food.getFoodId();
        final String foodName = food.getFoodName();
        final String foodPrice = food.getFoodPrice();
        final String foodCategory = food.getFoodCategory();
        final String foodContent = food.getFoodContent();
        final String foodDescription = food.getFoodDescription();
        final String foodStock = food.getFoodStock();
        final String foodPict = food.getFoodPict();

        holder.food_name.setText(food.getFoodName());
        holder.food_price.append(rupiah);

        holder.btnDetailsFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent = new Intent(view.getContext(), FoodDetailsActivity.class);
                detailsIntent.putExtra("foodId", foodId);
                detailsIntent.putExtra("foodName", foodName);
                detailsIntent.putExtra("foodPrice", foodPrice);
                detailsIntent.putExtra("foodCategory", foodCategory);
                detailsIntent.putExtra("foodContent", foodContent);
                detailsIntent.putExtra("foodDescription", foodDescription);
                detailsIntent.putExtra("foodStock", foodStock);
                detailsIntent.putExtra("foodPict", foodPict);
                view.getContext().startActivity(detailsIntent);
            }
        });

        Glide.with(holder.food_image.getContext()).load(foodPict).into(holder.food_image);


    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView food_image;
        private TextView food_name;
        private TextView food_price;
        Button btnDetailsFood;


        public ViewHolder(View itemView) {
            super(itemView);

            food_image = (ImageView) itemView.findViewById(R.id.food_image);
            food_name = (TextView) itemView.findViewById(R.id.food_name);
            food_price = (TextView) itemView.findViewById(R.id.food_price);
            btnDetailsFood = (Button) itemView.findViewById(R.id.btnFoodDetails);

        }
    }
}
