package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.NumberFormat;
import java.util.Locale;

public class FoodDetailsActivity extends AppCompatActivity {
    ImageButton imbBack;
    String foodName, foodPrice, foodCategory, foodContent, foodDescription, foodStock, foodPict;
    TextView tvFoodContentDetails, tvAddFood, tvFoodTitle, tvFoodNameDetails, tvFoodPriceDetails, tvFoodDescDetails, tvOrder, tvCancel;
    ImageView ivFoodDetails;
    NumberFormat rupiahFormat;
    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        tvOrder = (TextView) findViewById(R.id.tvOrder);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        numberButton.setVisibility(View.GONE);
        tvOrder.setVisibility(View.GONE);
        tvCancel.setVisibility(View.GONE);


        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        foodName = intent.getStringExtra("foodName");
        foodPrice = intent.getStringExtra("foodPrice");
        foodCategory = intent.getStringExtra("foodCategory");
        foodContent = intent.getStringExtra("foodContent");
        foodDescription = intent.getStringExtra("foodDescription");
        foodStock = intent.getStringExtra("foodStock");
        foodPict = intent.getStringExtra("foodPict");

        tvFoodTitle = (TextView) findViewById(R.id.tvFoodTitle);
        tvFoodTitle.setText(foodName);

        ivFoodDetails = (ImageView) findViewById(R.id.ivFoodDetails);
        Glide.with(this).load(foodPict).into(ivFoodDetails);

        tvFoodNameDetails = (TextView) findViewById(R.id.tvFoodNameDetails);
        tvFoodNameDetails.setText(foodName);

        tvFoodPriceDetails = (TextView) findViewById(R.id.tvFoodPriceDetails);
        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        String rupiah = rupiahFormat.format(Double.parseDouble(foodPrice));
        tvFoodPriceDetails.append(rupiah);

        tvAddFood = (TextView) findViewById(R.id.tvAddFood);

        tvAddFood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvAddFood.setVisibility(View.INVISIBLE);
                numberButton.setVisibility(View.VISIBLE);
                tvOrder.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.VISIBLE);

                return false;
            }
        });

        tvCancel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvAddFood.setVisibility(View.VISIBLE);
                numberButton.setVisibility(View.INVISIBLE);
                tvOrder.setVisibility(View.INVISIBLE);
                tvCancel.setVisibility(View.INVISIBLE);

                return false;
            }
        });

        tvFoodDescDetails = (TextView) findViewById(R.id.tvFoodDescDetails);
        //tvFoodDescDetails.setText(foodDescription);
        tvFoodDescDetails.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");

        tvFoodContentDetails = (TextView) findViewById(R.id.tvFoodContentDetails);
        tvFoodContentDetails.append(foodContent);

    }


}
