package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;

public class FoodOrderActivity extends AppCompatActivity {
    ImageButton imbBack;
    android.support.v7.widget.SearchView searchFood;
    CardView cvRices;
    CoordinatorLayout foodLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        searchFood = (SearchView) findViewById(R.id.searchFood);
//        searchFood.onActionViewExpanded();
//        searchFood.setIconified(false);

        cvRices = (CardView) findViewById(R.id.cvRices);
        foodLayout = (CoordinatorLayout) findViewById(R.id.foodLayout);
        cvRices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent riceIntent = new Intent(FoodOrderActivity.this, RicesMenu.class);
                startActivity(riceIntent);
            }
        });

    }

}
