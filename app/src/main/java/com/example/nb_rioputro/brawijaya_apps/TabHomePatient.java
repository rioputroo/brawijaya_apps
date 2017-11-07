package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TabHomePatient extends Fragment {
    Button btnFoodOrder;
    String mId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_home_patient, container, false);

        btnFoodOrder = (Button) rootView.findViewById(R.id.btnFoodOrder);
        btnFoodOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderFood();
            }
        });

        return rootView;
    }

    private void orderFood() {
        Intent orderIntent = new Intent(getActivity(), FoodOrderActivity.class);
        startActivity(orderIntent);

    }
}
