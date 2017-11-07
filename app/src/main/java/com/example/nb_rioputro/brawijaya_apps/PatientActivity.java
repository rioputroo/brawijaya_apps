package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {

    String mNama;
    TabLayout tlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);


        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mNama = sp.getString(Config.NAME_SHARED_PREF, "Error getting name");

        tlMain = (TabLayout)findViewById(R.id.tabLayout);

    }


}
