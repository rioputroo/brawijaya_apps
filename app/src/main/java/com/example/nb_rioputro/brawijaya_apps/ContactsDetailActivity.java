package com.example.nb_rioputro.brawijaya_apps;

import android.*;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;

public class ContactsDetailActivity extends BaseActivity implements SinchService.StartFailedListener {
    String caller, recipients, nama;
    ImageButton imbBack;

    TextView tvKontakDetailNama;
    CardView cvMessage, cvCall, cvVideoCall;
    private ProgressDialog mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //asking for permissions here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.READ_PHONE_STATE}, 100);
        }

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        caller = intent.getStringExtra("callerID");
        recipients = intent.getStringExtra("recipientID");
        nama = intent.getStringExtra("namaID");

        tvKontakDetailNama = (TextView) findViewById(R.id.tvKontakDetailNama);
        tvKontakDetailNama.setText(nama);

        cvMessage = (CardView) findViewById(R.id.cvMessage);
        cvCall = (CardView) findViewById(R.id.cvCall);
        cvVideoCall = (CardView) findViewById(R.id.cvVideoCall);

        cvVideoCall.setEnabled(false);
        cvVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoCall();
            }
        });


    }


    //this method is invoked when the connection is established with the SinchService
    @Override
    protected void onServiceConnected() {
        cvVideoCall.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);
    }

    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    //Invoked when just after the service is connected with Sinch
    @Override
    public void onStarted() {
        openPlaceCallActivity();
    }

    //Login is Clicked to manually to connect to the Sinch Service
    private void videoCall() {
        String userName = caller;

        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }

        if (!getSinchServiceInterface().isStarted()) {
            getSinchServiceInterface().startClient(userName);
            showSpinner();
        } else {
            openPlaceCallActivity();
        }
    }


    //Once the connection is made to the Sinch Service, It takes you to the next activity where you enter the name of the user to whom the call is to be placed
    private void openPlaceCallActivity() {
//        Intent placeActivity = new Intent(this, PlaceVideoCallActivity.class);
//        placeActivity.putExtra("callerID", caller);
//        placeActivity.putExtra("recipientID", recipients);
//        placeActivity.putExtra("namaID", nama);
//        startActivity(placeActivity);
//        Toast.makeText(getApplicationContext(), "First Phase", Toast.LENGTH_SHORT).show();
        String userName = recipients;
        if (userName.isEmpty()) {
            Toast.makeText(this, "No user to call", Toast.LENGTH_LONG).show();
            return;
        }

        Call call = getSinchServiceInterface().callUserVideo(userName);
        String callId = call.getCallId();


        //Toast.makeText(getApplicationContext(), "Phase 2", Toast.LENGTH_SHORT).show();
        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Setting up video call");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }

}
