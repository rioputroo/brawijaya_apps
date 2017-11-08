package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FoodDetailsActivity extends AppCompatActivity {
    ImageButton imbBack;
    String foodName, foodCategory, foodContent, foodDescription, foodStock, foodPict, foodId, mIdUser;
    int foodPrice;
    TextView tvFoodContentDetails, tvAddFood, tvFoodTitle, tvFoodNameDetails, tvFoodPriceDetails, tvFoodDescDetails, tvCancel;
    ImageView ivFoodDetails;
    NumberFormat rupiahFormat;
    ElegantNumberButton numberButton;
    Button tvOrder;
    String jumlah_order;
    private List<Cart> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        tvOrder = (Button) findViewById(R.id.tvOrder);
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
        foodId = intent.getStringExtra("foodId");
        foodName = intent.getStringExtra("foodName");
        foodPrice = Integer.parseInt(intent.getStringExtra("foodPrice"));
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
        String rupiah = rupiahFormat.format(Double.parseDouble(String.valueOf(foodPrice)));
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

        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah_order = numberButton.getNumber();
                final int total = Integer.parseInt(jumlah_order) * foodPrice;
                final String status = "cart";

                SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                mIdUser = sp.getString(Config.ID_SHARED_PREF, "Error getting id");
//                Toast.makeText(getApplicationContext(), String.valueOf(total), Toast.LENGTH_SHORT).show();
                StringRequest cartRequest = new StringRequest(Request.Method.POST, Config.ADD_CART_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respCartReq: ", response);
                        Toast.makeText(getApplicationContext(), "Order successfully added!", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Order not added. Please try again.", Toast.LENGTH_LONG).show();
                        Log.d("errRespCartReq: ", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id_detail_order", mIdUser);
                        params.put("id_food", foodId);
                        params.put("jumlah_order", jumlah_order);
                        params.put("total_order", String.valueOf(total));
                        params.put("status_order", status);

                        return params;
                    }
                };

                cartRequest.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 50000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 50000;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });

                RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                rq.add(cartRequest);

                tvAddFood.setVisibility(View.VISIBLE);
                numberButton.setVisibility(View.INVISIBLE);
                tvOrder.setVisibility(View.INVISIBLE);
                tvCancel.setVisibility(View.INVISIBLE);
            }
        });

        tvFoodDescDetails = (TextView) findViewById(R.id.tvFoodDescDetails);
        //tvFoodDescDetails.setText(foodDescription);
        tvFoodDescDetails.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");

        tvFoodContentDetails = (TextView) findViewById(R.id.tvFoodContentDetails);
        tvFoodContentDetails.append(foodContent);

    }


}
