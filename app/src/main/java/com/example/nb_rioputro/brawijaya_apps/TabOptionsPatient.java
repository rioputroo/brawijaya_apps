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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_options_patient, container, false);

        recyclerCart = (RecyclerView) rootView.findViewById(R.id.recycler_cart);
        recyclerCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerCart.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartList);
        recyclerCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        SharedPreferences sp = this.getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mId = sp.getString(Config.ID_SHARED_PREF, "error getting id");

        cardRowView = (CardView) rootView.findViewById(R.id.cardRowView);

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

        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(onCartRequest);


        return rootView;

    }


}
