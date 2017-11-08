package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends AppCompatActivity {
    ImageButton imbBack;
    RecyclerView recyclerView;
    String mId;
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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

        StringRequest onCartRequest = new StringRequest(Request.Method.POST, Config.GET_CART_LIST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ListCart: ", response);

                try {
                    JSONArray cartArr = new JSONArray(response);

                    for (int i = 0; i < cartArr.length(); i++) {
                        JSONObject object = cartArr.getJSONObject(i);
                        String foodName = object.getString("name_food");
                        String jumlahOrder = object.getString("jumlah_order");
                        String totalOrder = object.getString("total_order");
                        String foodPict = object.getString("pict_food");

                        Cart cart = new Cart(foodName, jumlahOrder, totalOrder, foodPict);
                        cartList.add(cart);
                    }
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
