package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ThirdSurveyFormActivity extends AppCompatActivity {
    ImageButton imbBack;
    RadioGroup radioGroupPrayingRoom, radioGroupTarif, radioGroupKomentar1, radioGroupKomentar2;
    String surveyAge,
            surveySex,
            surveyPayment,
            surveyOrigin,
            surveyTanggalDirawat,
            surveyNamaDokter,
            surveyRuangan,
            surveyParking,
            surveySecurity,
            surveyAdmission,
            surveyBilling,
            surveyPharmacy,
            surveyLaboratory,
            surveyRadiology,
            surveyMedicRehab,
            surveyNutrition,
            surveyAmbulance,
            surveyRoomFacility,
            surveyCleaningService,
            surveySpecialist,
            surveyGeneral,
            surveyNurse,
            surveyCafetaria,
            surveyReasonParking,
            surveyReasonSecurity,
            surveyReasonAdmission,
            surveyReasonBilling,
            surveyReasonPharmacy,
            surveyReasonLaboratory,
            surveyReasonRadiology,
            surveyReasonMedicRehab,
            surveyReasonNutrition,
            surveyReasonAmbulance,
            surveyReasonRoomFacility,
            surveyReasonCleaningService,
            surveyReasonSpecialist,
            surveyReasonGeneral,
            surveyReasonNurse,
            surveyReasonCafetaria,
            surveyReasonPrayingRoom,
            surveyReasonTarif,
            surveyReasonLainnya,
            surveyKomentar,
            checkboxDoctor = "0",
            checkboxKualitas = "0",
            checkboxKemudahan = "0",
            checkboxAsuransi = "0",
            checkboxHarga = "0",
            checkboxKeluarga = "0",
            checkboxMedsos = "0",
            surveyPrayingRoom,
            surveyTarif,
            surveyKomentar1,
            surveyKomentar2,
            id_user;

    EditText etPrayingRoom, etTarif, etLainnya, etKomentar;
    CheckBox cbDoctor, cbKualitas, cbKemudahan, cbAsuransi, cbHarga, cbKeluarga, cbMedsos;
    Button btnSubmitSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_survey_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences spIdUser = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id_user = spIdUser.getString(Config.ID_SHARED_PREF, "cant getting user id");

        Intent intent = getIntent();
        surveyParking = intent.getStringExtra("surveyParking");
        surveySecurity = intent.getStringExtra("surveySecurity");
        surveyAdmission = intent.getStringExtra("surveyAdmission");
        surveyBilling = intent.getStringExtra("surveyBilling");
        surveyPharmacy = intent.getStringExtra("surveyPharmacy");
        surveyLaboratory = intent.getStringExtra("surveyLaboratory");
        surveyRadiology = intent.getStringExtra("surveyRadiology");
        surveyMedicRehab = intent.getStringExtra("surveyMedicRehab");
        surveyNutrition = intent.getStringExtra("surveyNutrition");
        surveyAmbulance = intent.getStringExtra("surveyAmbulance");
        surveyRoomFacility = intent.getStringExtra("surveyRoomFacility");
        surveyCleaningService = intent.getStringExtra("surveyCleaningService");
        surveySpecialist = intent.getStringExtra("surveySpecialist");
        surveyGeneral = intent.getStringExtra("surveyGeneral");
        surveyNurse = intent.getStringExtra("surveyNurse");
        surveyCafetaria = intent.getStringExtra("surveyCafetaria");
        surveyAge = intent.getStringExtra("surveyAge");
        surveySex = intent.getStringExtra("surveySex");
        surveyPayment = intent.getStringExtra("surveyPayment");
        surveyOrigin = intent.getStringExtra("surveyOrigin");
        surveyTanggalDirawat = intent.getStringExtra("surveyTanggalDirawat");
        surveyNamaDokter = intent.getStringExtra("surveyNamaDokter");
        surveyRuangan = intent.getStringExtra("surveyRuangan");
        //alasan
        surveyReasonParking = intent.getStringExtra("surveyReasonParking");
        surveyReasonSecurity = intent.getStringExtra("surveyReasonSecurity");
        surveyReasonAdmission = intent.getStringExtra("surveyReasonAdmission");
        surveyReasonBilling = intent.getStringExtra("surveyReasonBilling");
        surveyReasonPharmacy = intent.getStringExtra("surveyReasonPharmacy");
        surveyReasonLaboratory = intent.getStringExtra("surveyReasonLaboratory");
        surveyReasonRadiology = intent.getStringExtra("surveyReasonRadiology");
        surveyReasonMedicRehab = intent.getStringExtra("surveyReasonMedicRehab");
        surveyReasonNutrition = intent.getStringExtra("surveyReasonNutrition");
        surveyReasonAmbulance = intent.getStringExtra("surveyReasonAmbulance");
        surveyReasonRoomFacility = intent.getStringExtra("surveyReasonRoomFacility");
        surveyReasonCleaningService = intent.getStringExtra("surveyReasonCleaningService");
        surveyReasonSpecialist = intent.getStringExtra("surveyReasonSpecialist");
        surveyReasonGeneral = intent.getStringExtra("surveyReasonGeneral");
        surveyReasonNurse = intent.getStringExtra("surveyReasonNurse");
        surveyReasonCafetaria = intent.getStringExtra("surveyReasonCafetaria");

        radioGroupPrayingRoom = (RadioGroup) findViewById(R.id.radioGroupPrayingRoom);
        radioGroupTarif = (RadioGroup) findViewById(R.id.radioGroupTarif);
        radioGroupKomentar1 = (RadioGroup) findViewById(R.id.radioGroupKomentar1);
        radioGroupKomentar2 = (RadioGroup) findViewById(R.id.radioGroupKomentar2);

        etPrayingRoom = (EditText) findViewById(R.id.etPrayingRoom);
        etTarif = (EditText) findViewById(R.id.etTarif);
        etLainnya = (EditText) findViewById(R.id.etLainnya);
        etKomentar = (EditText) findViewById(R.id.etKomentar);

        cbDoctor = (CheckBox) findViewById(R.id.cbDoctor);
        cbKualitas = (CheckBox) findViewById(R.id.cbKualitas);
        cbKemudahan = (CheckBox) findViewById(R.id.cbKemudahan);
        cbAsuransi = (CheckBox) findViewById(R.id.cbAsuransi);
        cbHarga = (CheckBox) findViewById(R.id.cbHarga);
        cbKeluarga = (CheckBox) findViewById(R.id.cbKeluarga);
        cbMedsos = (CheckBox) findViewById(R.id.cbMedsos);


        btnSubmitSurvey = (Button) findViewById(R.id.btnSubmitSurvey);
        btnSubmitSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyReasonPrayingRoom = etPrayingRoom.getText().toString();
                surveyReasonTarif = etTarif.getText().toString();
                surveyReasonLainnya = etLainnya.getText().toString();
                surveyKomentar = etKomentar.getText().toString();

                int prayingRoom = radioGroupPrayingRoom.getCheckedRadioButtonId();
                int tarif = radioGroupTarif.getCheckedRadioButtonId();
                int komentar1 = radioGroupKomentar1.getCheckedRadioButtonId();
                int komentar2 = radioGroupKomentar2.getCheckedRadioButtonId();

                RadioButton radioButtonPrayingRoom = (RadioButton) findViewById(prayingRoom);
                RadioButton radioButtonTarif = (RadioButton) findViewById(tarif);
                RadioButton radioButtonKomentar1 = (RadioButton) findViewById(komentar1);
                RadioButton radioButtonKomentar2 = (RadioButton) findViewById(komentar2);

                if (cbDoctor.isChecked()) {
                    checkboxDoctor = cbDoctor.getText().toString();
                } else if (cbKualitas.isChecked()) {
                    checkboxKualitas = cbKualitas.getText().toString();
                } else if (cbKemudahan.isChecked()) {
                    checkboxKemudahan = cbKemudahan.getText().toString();
                } else if (cbAsuransi.isChecked()) {
                    checkboxAsuransi = cbAsuransi.getText().toString();
                } else if (cbHarga.isChecked()) {
                    checkboxHarga = cbHarga.getText().toString();
                } else if (cbKeluarga.isChecked()) {
                    checkboxKeluarga = cbKeluarga.getText().toString();
                } else if (cbMedsos.isChecked()) {
                    checkboxMedsos = cbMedsos.getText().toString();
                }

                if (prayingRoom != -1 && tarif != -1 && komentar1 != -1 && komentar2 != -1) {
                    surveyPrayingRoom = radioButtonPrayingRoom.getText().toString();
                    surveyTarif = radioButtonTarif.getText().toString();
                    surveyKomentar1 = radioButtonKomentar1.getText().toString();
                    surveyKomentar2 = radioButtonKomentar2.getText().toString();

                    sendSurvey();
                } else {
                    Toast.makeText(ThirdSurveyFormActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void onCheckboxClicked(View view) {
        //is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        //check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cbDoctor:
                if (checked) {
                    checkboxDoctor = cbDoctor.getText().toString();
                } else {
                    checkboxDoctor = "Tidak dipilih";
                }
                break;

            case R.id.cbKualitas:
                if (checked) {
                    checkboxKualitas = cbKualitas.getText().toString();
                } else {
                    checkboxKualitas = "Tidak dipilih";
                }
                break;

            case R.id.cbKemudahan:
                if (checked) {
                    checkboxKemudahan = cbKemudahan.getText().toString();
                } else {
                    checkboxKemudahan = "Tidak dipilih";
                }
                break;

            case R.id.cbAsuransi:
                if (checked) {
                    checkboxAsuransi = cbAsuransi.getText().toString();
                } else {
                    checkboxAsuransi = "Tidak dipilih";
                }
                break;

            case R.id.cbHarga:
                if (checked) {
                    checkboxHarga = cbHarga.getText().toString();
                } else {
                    checkboxHarga = "Tidak dipilih";
                }
                break;

            case R.id.cbKeluarga:
                if (checked) {
                    checkboxKeluarga = cbKeluarga.getText().toString();
                } else {
                    checkboxKeluarga = "Tidak dipilih";
                }
                break;

            case R.id.cbMedsos:
                if (checked) {
                    checkboxMedsos = cbMedsos.getText().toString();
                } else {
                    checkboxMedsos = "Tidak dipilih";
                }
                break;
        }
    }

    private void sendSurvey() {
        StringRequest sendSurvey = new StringRequest(Request.Method.POST, Config.SEND_SURVEY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ThirdSurveyFormActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                Intent success = new Intent(ThirdSurveyFormActivity.this, MainActivity.class);
                startActivity(success);
                Log.d("INIRESPONSE: ", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThirdSurveyFormActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("surveyParking", surveyParking);
                params.put("surveySecurity", surveySecurity);
                params.put("surveyAdmission", surveyAdmission);
                params.put("surveySecurity", surveySecurity);
                params.put("surveyBilling", surveyBilling);
                params.put("surveyPharmacy", surveyPharmacy);
                params.put("surveyLaboratory", surveyLaboratory);
                params.put("surveyRadiology", surveyRadiology);
                params.put("surveyMedicRehab", surveyMedicRehab);
                params.put("surveyNutrition", surveyNutrition);
                params.put("surveyAmbulance", surveyAmbulance);
                params.put("surveyRoomFacility", surveyRoomFacility);
                params.put("surveyCleaningService", surveyCleaningService);
                params.put("surveySpecialist", surveySpecialist);
                params.put("surveyGeneral", surveyGeneral);
                params.put("surveyNurse", surveyNurse);
                params.put("surveyCafetaria", surveyCafetaria);
                params.put("surveyAge", surveyAge);
                params.put("surveySex", surveySex);
                params.put("surveyPayment", surveyPayment);
                params.put("surveyOrigin", surveyOrigin);
                params.put("surveyTanggalDirawat", surveyTanggalDirawat);
                params.put("surveyNamaDokter", surveyNamaDokter);
                params.put("surveyRuangan", surveyRuangan);
                params.put("surveyPrayingRoom", surveyPrayingRoom);
                params.put("surveyTarif", surveyTarif);
                params.put("surveyKomentar1", surveyKomentar1);
                params.put("surveyKomentar2", surveyKomentar2);
                params.put("surveyKomentar", surveyKomentar);

                params.put("surveyReasonParking", surveyReasonParking);
                params.put("surveyReasonSecurity", surveyReasonSecurity);
                params.put("surveyReasonAdmission", surveyReasonAdmission);
                params.put("surveySecurity", surveySecurity);
                params.put("surveyReasonBilling", surveyReasonBilling);
                params.put("surveyReasonPharmacy", surveyReasonPharmacy);
                params.put("surveyReasonLaboratory", surveyReasonLaboratory);
                params.put("surveyReasonRadiology", surveyReasonRadiology);
                params.put("surveyReasonMedicRehab", surveyReasonMedicRehab);
                params.put("surveyReasonNutrition", surveyReasonNutrition);
                params.put("surveyReasonAmbulance", surveyReasonAmbulance);
                params.put("surveyReasonRoomFacility", surveyReasonRoomFacility);
                params.put("surveyReasonCleaningService", surveyReasonCleaningService);
                params.put("surveyReasonSpecialist", surveyReasonSpecialist);
                params.put("surveyReasonGeneral", surveyReasonGeneral);
                params.put("surveyReasonNurse", surveyReasonNurse);
                params.put("surveyReasonCafetaria", surveyReasonCafetaria);
                params.put("surveyReasonPrayingRoom", surveyReasonPrayingRoom);
                params.put("surveyReasonTarif", surveyReasonTarif);
                params.put("surveyReasonLainnya", surveyReasonLainnya);

                params.put("checkboxDoctor", checkboxDoctor);
                params.put("checkboxKualitas", checkboxKualitas);
                params.put("checkboxKemudahan", checkboxKemudahan);
                params.put("checkboxAsuransi", checkboxAsuransi);
                params.put("checkboxHarga", checkboxHarga);
                params.put("checkboxKeluarga", checkboxKeluarga);
                params.put("checkboxMedsos", checkboxMedsos);

                params.put("id_user", id_user);

//                params.put("surveyParking", "4");
//                params.put("surveySecurity", "3");
//                params.put("surveyAdmission", "4");
//                params.put("surveySecurity", "4");
//                params.put("surveyBilling", "4");
//                params.put("surveyPharmacy", "4");
//                params.put("surveyLaboratory", "3");
//                params.put("surveyRadiology", "3");
//                params.put("surveyMedicRehab", "4");
//                params.put("surveyNutrition", "4");
//                params.put("surveyAmbulance", "3");
//                params.put("surveyRoomFacility", "4");
//                params.put("surveyCleaningService", "4");
//                params.put("surveySpecialist", "3");
//                params.put("surveyGeneral", "3");
//                params.put("surveyNurse", "4");
//                params.put("surveyCafetaria", "4");
//                params.put("surveyAge", "15-24 th");
//                params.put("surveySex", "Laki-laki / Male");
//                params.put("surveyPayment", "Pribadi / Private");
//                params.put("surveyOrigin", "Admission / Admission");
//                params.put("surveyTanggalDirawat", "14/12/2017");
//                params.put("surveyNamaDokter", "Budi");
//                params.put("surveyRuangan", "VIP 1");
//                params.put("surveyPrayingRoom", "4");
//                params.put("surveyTarif", "Mahal / expensive");
//                params.put("surveyKomentar1", "Ya / Yes");
//                params.put("surveykomentar2", "Ya / Yes");
//                params.put("surveykomentar", "test");
//
//                params.put("surveyReasonParking", "a");
//                params.put("surveyReasonSecurity", "b");
//                params.put("surveyReasonAdmission", "c");
//                params.put("surveySecurity", "d");
//                params.put("surveyReasonBilling", "e");
//                params.put("surveyReasonPharmacy", "f");
//                params.put("surveyReasonLaboratory", "g");
//                params.put("surveyReasonRadiology", "h");
//                params.put("surveyReasonMedicRehab", "i");
//                params.put("surveyReasonNutrition", "j");
//                params.put("surveyReasonAmbulance", "k");
//                params.put("surveyReasonRoomFacility", "l");
//                params.put("surveyReasonCleaningService", "m");
//                params.put("surveyReasonSpecialist", "n");
//                params.put("surveyReasonGeneral", "o");
//                params.put("surveyReasonNurse", "p");
//                params.put("surveyReasonCafetaria", "q");
//                params.put("surveyReasonPrayingRoom", "r");
//                params.put("surveyReasonTarif", "s");
//                params.put("surveyReasonLainnya", "t");
//
//                params.put("checkboxDoctor", "Dokter (Doctor)");
//                params.put("checkboxKualitas", "null");
//                params.put("checkboxKemudahan", "Kemudahan Akses (Accessibility)");
//                params.put("checkboxAsuransi", "null");
//                params.put("checkboxHarga", "null");
//                params.put("checkboxKeluarga", "null");
//                params.put("checkboxMedsos", "null");
//
//                params.put("id_user", "1");


                Log.d("params", params.toString());

                return params;
            }
        };

        sendSurvey.setRetryPolicy(new RetryPolicy() {
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
        rq.add(sendSurvey);
    }

}
