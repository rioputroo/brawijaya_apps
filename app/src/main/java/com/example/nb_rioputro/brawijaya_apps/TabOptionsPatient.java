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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TabOptionsPatient extends Fragment{
    Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_options_patient, container, false);
        btnLogout = (Button)rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogout();
            }
        });



        return rootView;



    }

    private void userLogout() {
        AlertDialog.Builder adLogout = new AlertDialog.Builder(getActivity());
        adLogout.setMessage("Are you sure you want to logout? ");
        adLogout.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences spLogout = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spLogout.edit();

                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                editor.putString(Config.USERNAME_SHARED_PREF, "");
                editor.putString(Config.NAME_SHARED_PREF, "");
                editor.putString(Config.ROLE_SHARED_PREF, "");

                editor.commit();

                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
            }
        });

        adLogout.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = adLogout.create();
        alertDialog.show();
    }
}
