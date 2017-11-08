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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TabOptionsPatient extends Fragment {
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    RecyclerView recyclerCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_options_patient, container, false);

        recyclerCart = (RecyclerView) rootView.findViewById(R.id.recycler_cart);
        recyclerCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerCart.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartList);
        recyclerCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();



        return rootView;

    }


}
