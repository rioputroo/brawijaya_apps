package com.example.nb_rioputro.brawijaya_apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyOrderActivity extends AppCompatActivity {
    ImageButton imbBack;
    RecyclerView recyclerView;
    String mId;
    TextView tvRealTotal;
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    NumberFormat rupiahFormat;
    int realTotal = 0;
    CoordinatorLayout orderLayout;
    String rupiah;
    Button btnCheckout;
    int id;
    ProgressDialog progressDialog;
    CardView cvTotal;
    TextView tvEmptyOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        tvRealTotal = (TextView) findViewById(R.id.tvRealTotal);
        cvTotal = (CardView) findViewById(R.id.cvTotal);

        orderLayout = (CoordinatorLayout) findViewById(R.id.orderLayout);
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mId = sp.getString(Config.ID_SHARED_PREF, "error getting id");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartList);
        recyclerView.setAdapter(cartAdapter);

        getListOrder();



    }


    private void checkout() {
        StringRequest checkoutRequest = new StringRequest(Request.Method.POST, Config.CHECKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseORder: ", response);
                Snackbar snackbar = Snackbar.make(orderLayout, "Order successfully added!", Snackbar.LENGTH_LONG);
                snackbar.show();
                Intent successOrderIntent = new Intent(MyOrderActivity.this, HomePatientActivity.class);
                startActivity(successOrderIntent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", mId);
                params.put("id_detail_order", String.valueOf(id));
                params.put("total_price", String.valueOf(realTotal));
                params.put("status", "ordered");

                return params;
            }
        };

        checkoutRequest.setRetryPolicy(new RetryPolicy() {
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
        rq.add(checkoutRequest);

        Snackbar snackbar = Snackbar.make(orderLayout, "Ordering...", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void getListOrder() {
        StringRequest onCartRequest = new StringRequest(Request.Method.POST, Config.GET_CART_LIST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ListCart: ", response);

                try {
                    JSONArray cartArr = new JSONArray(response);

                    for (int i = 0; i < cartArr.length(); i++) {
                        JSONObject object = cartArr.getJSONObject(i);
                        id = object.getInt("id");
                        String foodName = object.getString("name_food");
                        int jumlahOrder = Integer.parseInt(object.getString("jumlah_order"));
                        int totalOrder = Integer.parseInt(object.getString("total_order"));
                        String foodPict = object.getString("pict_food");
                        int price_food = object.getInt("price_food");

                        Cart cart = new Cart(foodName, jumlahOrder, totalOrder, foodPict, id, price_food);
                        cartList.add(cart);

                        realTotal = realTotal + totalOrder;


                    }
                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
                    rupiah = rupiahFormat.format(realTotal);
                    tvRealTotal.setText("IDR " + rupiah);
                    Log.d("RUPIAH MY ORDER: ", rupiah);


                    Log.d("Total: ", String.valueOf(realTotal));
                    cartAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ListCartErr: ", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", mId);

                return params;
            }
        };

        onCartRequest.setRetryPolicy(new RetryPolicy() {
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
        rq.add(onCartRequest);
    }


}
