package com.example.nb_rioputro.brawijaya_apps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WesternActivity extends AppCompatActivity {
    ImageButton imbBack;
    TextView tvTitle;
    RecyclerView rvWestern;
    private ProductsAdapter westernAdapter;
    private List<Products> westernList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_western);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        tvTitle = (TextView) findViewById(R.id.tvCategoryTitle);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("FoodTitle");
            tvTitle.setText(title);

        }

        rvWestern = (RecyclerView) findViewById(R.id.recycler_western);
        rvWestern.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvWestern.setLayoutManager(layoutManager);

        westernAdapter = new ProductsAdapter(westernList);
        rvWestern.setAdapter(westernAdapter);

//        Firebase.setAndroidContext(this);
//        String url = "https://brawijaya-be227.firebaseio.com/food";
//        Firebase ref = new Firebase(url);
//        final Query query = ref.orderByChild("foodcat").equalTo("western");
//
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot food : snapshot.getChildren()) {
//                    String foodName = String.valueOf(food.child("foodname").getValue());
//                    String foodPrice = String.valueOf(food.child("custprice").getValue());
//                    String foodImage = String.valueOf(food.child("foodimage").getValue());
//
//
//                    Makanan makanan = new Makanan(foodName, foodPrice, foodImage);
//                    westernList.add(makanan);
//                }
//                westernAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Log.d("firebase eggbread err: ", firebaseError.toString());
//            }
//
//        });
        StringRequest getWestern = new StringRequest(Request.Method.POST, Config.GET_FOOD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(EggBreadActivity.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray foodArray = new JSONArray(response);

                    for (int i = 0; i < foodArray.length(); i++) {
                        String foodId = foodArray
                                .getJSONObject(i)
                                .getString("id_asli_tera");

                        String foodName = foodArray
                                .getJSONObject(i)
                                .getString("nama_products");

                        String foodPrice = foodArray
                                .getJSONObject(i)
                                .getString("cust_price_products");

                        String foodImage = foodArray
                                .getJSONObject(i)
                                .getString("image_products");

//                        Makanan makanan = new Makanan(foodId, foodName, foodPrice, foodImage);
//                        westernList.add(makanan);
                        double foodPrices = Double.parseDouble(foodPrice);
                        Products products = new Products(foodId, foodName, foodPrices, foodImage);
                        westernList.add(products);
                    }
                    westernAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WesternActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", "3");

                return params;
            }
        };

        getWestern.setRetryPolicy(new RetryPolicy() {
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
        rQueue.add(getWestern);


    }

}
