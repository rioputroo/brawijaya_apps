package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabProfilePatient extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_profile_patient, container, false);
        return rootView;
    }
}
