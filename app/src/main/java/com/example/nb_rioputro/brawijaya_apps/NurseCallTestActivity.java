package com.example.nb_rioputro.brawijaya_apps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NurseCallTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_call_test);

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+6281294664293"));
        startActivity(intent);
    }
}
