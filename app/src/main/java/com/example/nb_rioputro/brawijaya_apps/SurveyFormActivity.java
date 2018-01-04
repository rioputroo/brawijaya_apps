package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SurveyFormActivity extends AppCompatActivity {
    ImageButton imbBack;
    Button btnNextFirst;
    RadioGroup radioGroupAge, radioGroupSex, radioGroupPayment, radioGroupOrigin;
    EditText etSurveyNamaDokter, etSurveyRuangan;
    DatePicker datePickerDirawat;
    LinearLayout layoutSurveyForm;
    TextView tvCompleteForm;
    boolean isFormComplete = false;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        layoutSurveyForm = (LinearLayout) findViewById(R.id.layout_survey_form);
        tvCompleteForm = (TextView) findViewById(R.id.tvCompleteForm);

        SharedPreferences spGetId = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id_user = spGetId.getString(Config.ID_SHARED_PREF, "Error getting id user");
        Toast.makeText(getApplicationContext(), id_user, Toast.LENGTH_SHORT).show();

//        checkFormComplete();
//        Toast.makeText(this, String.valueOf(isFormComplete), Toast.LENGTH_SHORT).show();
//
        StringRequest getCompleteForm = new StringRequest(Request.Method.POST, Config.GET_SURVEY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //
                if (response.equalsIgnoreCase("true")) {
                    //isFormComplete = true;
                    //Toast.makeText(SurveyFormActivity.this, "true", Toast.LENGTH_SHORT).show();
                    layoutSurveyForm.setVisibility(View.GONE);
                    tvCompleteForm.setVisibility(View.VISIBLE);
                } else {
                    //sFormComplete = false;
                    //Toast.makeText(SurveyFormActivity.this, "false", Toast.LENGTH_SHORT).show();
                    radioGroupAge = (RadioGroup) findViewById(R.id.radioGroupAge);
                    radioGroupSex = (RadioGroup) findViewById(R.id.radioGroupSex);
                    radioGroupPayment = (RadioGroup) findViewById(R.id.radioGroupPayment);
                    radioGroupOrigin = (RadioGroup) findViewById(R.id.radioGroupOrigin);

                    etSurveyNamaDokter = (EditText) findViewById(R.id.etSurveyNamaDokter);
                    etSurveyRuangan = (EditText) findViewById(R.id.etSurveyRuangan);

                    datePickerDirawat = (DatePicker) findViewById(R.id.datePickerDirawat);

                    btnNextFirst = (Button) findViewById(R.id.btnNextFirst);
                    btnNextFirst.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String surveyNamaDokter = etSurveyNamaDokter.getText().toString();
                            String surveyRuangan = etSurveyRuangan.getText().toString();

                            int age = radioGroupAge.getCheckedRadioButtonId();
                            RadioButton radioButtonAge = (RadioButton) findViewById(age);

                            int sex = radioGroupSex.getCheckedRadioButtonId();
                            RadioButton radioButtonSex = (RadioButton) findViewById(sex);

                            int payment = radioGroupPayment.getCheckedRadioButtonId();
                            RadioButton radioButtonPayment = (RadioButton) findViewById(payment);

                            int origin = radioGroupOrigin.getCheckedRadioButtonId();
                            RadioButton radioButtonOrigin = (RadioButton) findViewById(origin);

                            int day = datePickerDirawat.getDayOfMonth();
                            int month = datePickerDirawat.getMonth() + 1;
                            int year = datePickerDirawat.getYear();

                            if (age != -1 && sex != -1 && payment != -1 && origin != -1 && !surveyNamaDokter.isEmpty() && !surveyRuangan.isEmpty()) {
                                String surveyAge = String.valueOf(radioButtonAge.getText());
                                String surveySex = String.valueOf(radioButtonSex.getText());
                                String surveyPayment = String.valueOf(radioButtonPayment.getText());
                                String surveyOrigin = String.valueOf(radioButtonOrigin.getText());
                                String surveyTanggalDirawat = day + "/" + month + "/" + year;

                                Intent secondSurveyForm = new Intent(SurveyFormActivity.this, SecondSurveyFormActivity.class);
                                secondSurveyForm.putExtra("surveyAge", surveyAge);
                                secondSurveyForm.putExtra("surveySex", surveySex);
                                secondSurveyForm.putExtra("surveyPayment", surveyPayment);
                                secondSurveyForm.putExtra("surveyOrigin", surveyOrigin);
                                secondSurveyForm.putExtra("surveyTanggalDirawat", surveyTanggalDirawat);
                                secondSurveyForm.putExtra("surveyNamaDokter", surveyNamaDokter);
                                secondSurveyForm.putExtra("surveyRuangan", surveyRuangan);
                                startActivity(secondSurveyForm);


                            } else {
                                Toast.makeText(SurveyFormActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SurveyFormActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);

                return params;
            }
        };

        getCompleteForm.setRetryPolicy(new RetryPolicy() {
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

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(getCompleteForm);


    }

//    private void checkFormComplete() {
////        StringRequest getCompleteForm = new StringRequest(Request.Method.POST, Config.GET_SURVEY_URL, new Response.Listener<String>() {
////            @Override
////            public void onResponse(String response) {
////                //
////                if (response.equalsIgnoreCase("true")) {
////                    isFormComplete = true;
////                    //Toast.makeText(SurveyFormActivity.this, "true", Toast.LENGTH_SHORT).show();
////                } else {
////                    isFormComplete = false;
////                    //Toast.makeText(SurveyFormActivity.this, "false", Toast.LENGTH_SHORT).show();
////                }
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(SurveyFormActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
////            }
////        }) {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String, String> params = new HashMap<>();
////                params.put("id_user", id_user);
////
////                return params;
////            }
////        };
////
////        getCompleteForm.setRetryPolicy(new RetryPolicy() {
////            @Override
////            public int getCurrentTimeout() {
////                return 50000;
////            }
////
////            @Override
////            public int getCurrentRetryCount() {
////                return 50000;
////            }
////
////            @Override
////            public void retry(VolleyError error) throws VolleyError {
////
////            }
////        });
////
////        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
////        rq.add(getCompleteForm);
//    }


}
