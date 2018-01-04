package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.calling.Call;

public class PlaceVideoCallActivity extends BaseActivity {
    String caller, recipients, nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_video_call);

        Intent intent = getIntent();
        caller = intent.getStringExtra("callerID");
        recipients = intent.getStringExtra("recipientID");
        nama = intent.getStringExtra("namaID");

        callButtonClicked();


    }

    // invoked when the connection with SinchServer is established
    @Override
    protected void onServiceConnected() {
//        TextView userName = (TextView) findViewById(R.id.loggedInName);
//        userName.setText(getSinchServiceInterface().getUserName());
//        mCallButton.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        super.onDestroy();
    }

    //to kill the current session of SinchService
    private void stopButtonClicked() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        finish();
    }

    //to place the call to the entered name
    private void callButtonClicked() {
        String userName = recipients;
        if (userName.isEmpty()) {
            Toast.makeText(this, "No user to call", Toast.LENGTH_LONG).show();
            return;
        }

        Call call = getSinchServiceInterface().callUserVideo(userName);
        String callId = call.getCallId();

        Toast.makeText(getApplicationContext(), "Phase 2", Toast.LENGTH_SHORT).show();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);
    }


//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.callButton:
//                    callButtonClicked();
//                    break;
//
//                case R.id.stopButton:
//                    stopButtonClicked();
//                    break;
//
//            }
//        }
//    };
}
