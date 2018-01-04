package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sinch.android.rtc.calling.Call;

public class VideoCallScreen extends BaseActivity {
    String caller, recipients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_screen);

        Intent intent = getIntent();
        caller = intent.getStringExtra("caller");
        recipients = intent.getStringExtra("recipients");

        Call call = getSinchServiceInterface().callUserVideo(recipients);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);

    }
}
