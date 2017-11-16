package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EggBreadActivity extends AppCompatActivity {
    ImageButton imbBack;
    TextView tvTitle;
    RecyclerView rvEggBread;
    private MakananAdapter eggbreadAdapter;
    private List<Makanan> eggbreadList = new ArrayList<>();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_bread);
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


        rvEggBread = (RecyclerView) findViewById(R.id.recycler_eggbread);
        rvEggBread.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvEggBread.setLayoutManager(layoutManager);

        eggbreadAdapter = new MakananAdapter(eggbreadList);
        rvEggBread.setAdapter(eggbreadAdapter);

        Firebase.setAndroidContext(this);
        String url = "https://brawijaya-be227.firebaseio.com/food";
        Firebase ref = new Firebase(url);
        final Query query = ref.orderByChild("foodcat").equalTo("eggbread");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot user : snapshot.getChildren()) {
//                    Firebase statusRef = user.child("password").getRef();
//                    statusRef.setValue("COMPLETED");
//                }
                //Makanan food = snapshot.getValue(Makanan.class);

                for (DataSnapshot food : snapshot.getChildren()) {
                    String foodName = String.valueOf(food.child("foodname").getValue());
                    String foodPrice = String.valueOf(food.child("custprice").getValue());
                    String foodImage = String.valueOf(food.child("foodimage").getValue());
                    String foodId = String.valueOf(food.child("foodid").getValue());



                    Makanan makanan = new Makanan(foodId, foodName, foodPrice, foodImage);
                    eggbreadList.add(makanan);
                    Log.d("eggbreadlist: ", makanan.getFoodname());
                    //Log.d("foodname: ", foodname);
//                    statusRef.setValue("COMPLETED");
                }
                eggbreadAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("firebase eggbread err: ", firebaseError.toString());
            }

        });
    }

}
