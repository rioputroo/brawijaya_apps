package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PharmacyOrderActivity extends AppCompatActivity {

    ImageButton imbBack;
    RecyclerView rvPharmacy;
    private ProductsAdapter pharmacyAdapter;
    private List<Products> pharmacyList = new ArrayList<Products>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rvPharmacy = (RecyclerView) findViewById(R.id.recycler_pharmacy);
        rvPharmacy.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvPharmacy.setLayoutManager(layoutManager);

        pharmacyAdapter = new ProductsAdapter(pharmacyList);
        rvPharmacy.setAdapter(pharmacyAdapter);

        StringRequest getPharmacy = new StringRequest(Request.Method.GET, Config.GET_PHARMACY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(PharmacyOrderActivity.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray obatArray = new JSONArray(response);

                    for (int i = 0; i < obatArray.length(); i++) {
                        String foodId = obatArray
                                .getJSONObject(i)
                                .getString("id_asli_tera");

                        String foodName = obatArray
                                .getJSONObject(i)
                                .getString("nama_products");

                        String foodPrice = obatArray
                                .getJSONObject(i)
                                .getString("cust_price_products");

                        String foodImage = obatArray
                                .getJSONObject(i)
                                .getString("image_products");

//                        Makanan makanan = new Makanan(foodId, foodName, foodPrice, foodImage);
//                        eggbreadList.add(makanan);
                        double foodPrices = Double.parseDouble(foodPrice);
                        Products products = new Products(foodId, foodName, foodPrices, foodImage);
                        pharmacyList.add(products);
                    }
                    pharmacyAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PharmacyOrderActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        getPharmacy.setRetryPolicy(new RetryPolicy() {
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

        RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
        rQueue.add(getPharmacy);


    }

}
