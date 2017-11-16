package com.example.nb_rioputro.brawijaya_apps;

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
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WesternActivity extends AppCompatActivity {
    ImageButton imbBack;
    TextView tvTitle;
    RecyclerView rvWestern;
    private MakananAdapter westernAdapter;
    private List<Makanan> westernList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_western);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        tvTitle = (TextView)findViewById(R.id.tvCategoryTitle);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String title = bundle.getString("FoodTitle");
            tvTitle.setText(title);

        }

        rvWestern = (RecyclerView) findViewById(R.id.recycler_western);
        rvWestern.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvWestern.setLayoutManager(layoutManager);

        westernAdapter = new MakananAdapter(westernList);
        rvWestern.setAdapter(westernAdapter);

        Firebase.setAndroidContext(this);
        String url = "https://brawijaya-be227.firebaseio.com/food";
        Firebase ref = new Firebase(url);
        final Query query = ref.orderByChild("foodcat").equalTo("western");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot food : snapshot.getChildren()) {
                    String foodName = String.valueOf(food.child("foodname").getValue());
                    String foodPrice = String.valueOf(food.child("custprice").getValue());
                    String foodImage = String.valueOf(food.child("foodimage").getValue());


                    Makanan makanan = new Makanan(foodName, foodPrice, foodImage);
                    westernList.add(makanan);
                }
                westernAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("firebase eggbread err: ", firebaseError.toString());
            }

        });


    }

}
