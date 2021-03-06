package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabOptionsPatient extends Fragment {
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    RecyclerView recyclerCart;
    String mId;
    CardView cardRowView;
    TextView tvEmptyOrder;
    Button btnOrderHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_options_patient, container, false);

//        btnOrderHistory = (Button)rootView.findViewById(R.id.btnOrderHistory);
//        btnOrderHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                orderHistory();
//            }
//        });



        return rootView;

    }

    private void orderHistory() {
        Intent orderHistory = new Intent(getActivity(), OrderHistoryActivity.class);
        startActivity(orderHistory);
    }


}
