package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;

public class FoodOrderActivity extends AppCompatActivity {
    ImageButton imbBack;
    android.support.v7.widget.SearchView searchFood;
    CardView cvEggBread, cvWestern, cvIndonesian, cvSnack, cvDessert;
    CoordinatorLayout foodLayout;
    ImageView iv_eggbread, iv_western, iv_indonesian, iv_snack, iv_dessert;

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

        cvEggBread = (CardView) findViewById(R.id.cvEggBread);
        cvWestern = (CardView) findViewById(R.id.cvWestern);
        cvIndonesian = (CardView) findViewById(R.id.cvIndonesian);
        cvSnack = (CardView) findViewById(R.id.cvSnack);
        cvDessert = (CardView) findViewById(R.id.cvDessert);
        foodLayout = (CoordinatorLayout) findViewById(R.id.foodLayout);

        iv_eggbread = (ImageView) findViewById(R.id.iv_eggbread);
        iv_western = (ImageView) findViewById(R.id.iv_western);
        iv_indonesian = (ImageView) findViewById(R.id.iv_indonesian);
        iv_snack = (ImageView) findViewById(R.id.iv_snack);
        iv_dessert = (ImageView) findViewById(R.id.iv_dessert);

        iv_eggbread.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.eggbread_1, 100, 100));
        iv_western.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.western_1, 100, 100));
        iv_indonesian.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.indonesian_3, 100, 100));
        iv_snack.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.snack_1, 100, 100));
        iv_dessert.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.dessert_1, 100, 100));

        cvEggBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrderActivity.this,EggBreadActivity.class));
            }
        });

        cvWestern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrderActivity.this,WesternActivity.class));
            }
        });

        cvIndonesian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrderActivity.this,IndonesianActivity.class));
            }
        });

        cvSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrderActivity.this,SnackActivity.class));
            }
        });

        cvDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrderActivity.this,DessertActivity.class));
            }
        });


    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        //Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the requested height and width.

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
