package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondSurveyFormActivity extends AppCompatActivity {
    ImageButton imbBack;
    String surveyAge, surveySex, surveyPayment, surveyOrigin, surveyTanggalDirawat, surveyNamaDokter, surveyRuangan;
    RadioGroup radioGroupParking,
            radioGroupSecurity,
            radioGroupAdmission,
            radioGroupBilling,
            radioGroupPharmacy,
            radioGroupLaboratory,
            radioGroupRadiology,
            radioGroupMedicRehab,
            radioGroupNutrition,
            radioGroupAmbulance,
            radioGroupRoomFacility,
            radioGroupCleaningService,
            radioGroupSpecialist,
            radioGroupGeneral,
            radioGroupNurse,
            radioGroupCafetaria;

    EditText etParking,
            etSecurity,
            etAdmission,
            etBilling,
            etPharmacy,
            etLaboratory,
            etRadiology,
            etMedicRehab,
            etNutrition,
            etAmbulance,
            etRoomFacility,
            etCleaningService,
            etSpecialist,
            etGeneral,
            etNurse,
            etCafetaria;
    Button btnNextSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_survey_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        surveyAge = intent.getStringExtra("surveyAge");
        surveySex = intent.getStringExtra("surveySex");
        surveyPayment = intent.getStringExtra("surveyPayment");
        surveyOrigin = intent.getStringExtra("surveyOrigin");
        surveyTanggalDirawat = intent.getStringExtra("surveyTanggalDirawat");
        surveyNamaDokter = intent.getStringExtra("surveyNamaDokter");
        surveyRuangan = intent.getStringExtra("surveyRuangan");

        radioGroupParking = (RadioGroup) findViewById(R.id.radioGroupParking);
        radioGroupSecurity = (RadioGroup) findViewById(R.id.radioGroupSecurity);
        radioGroupAdmission = (RadioGroup) findViewById(R.id.radioGroupAdmission);
        radioGroupBilling = (RadioGroup) findViewById(R.id.radioGroupBilling);
        radioGroupPharmacy = (RadioGroup) findViewById(R.id.radioGroupPharmacy);
        radioGroupLaboratory = (RadioGroup) findViewById(R.id.radioGroupLaboratory);
        radioGroupRadiology = (RadioGroup) findViewById(R.id.radioGroupRadiology);
        radioGroupMedicRehab = (RadioGroup) findViewById(R.id.radioGroupMedicRehab);
        radioGroupNutrition = (RadioGroup) findViewById(R.id.radioGroupNutrition);
        radioGroupAmbulance = (RadioGroup) findViewById(R.id.radioGroupAmbulance);
        radioGroupRoomFacility = (RadioGroup) findViewById(R.id.radioGroupRoomFacility);
        radioGroupCleaningService = (RadioGroup) findViewById(R.id.radioGroupCleaningService);
        radioGroupSpecialist = (RadioGroup) findViewById(R.id.radioGroupSpecialist);
        radioGroupGeneral = (RadioGroup) findViewById(R.id.radioGroupGeneral);
        radioGroupNurse = (RadioGroup) findViewById(R.id.radioGroupNurse);
        radioGroupCafetaria = (RadioGroup) findViewById(R.id.radioGroupCafetaria);

        etParking = (EditText) findViewById(R.id.etParking);
        etSecurity = (EditText) findViewById(R.id.etSecurity);
        etAdmission = (EditText) findViewById(R.id.etAdmission);
        etBilling = (EditText) findViewById(R.id.etBilling);
        etPharmacy = (EditText) findViewById(R.id.etPharmacy);
        etLaboratory = (EditText) findViewById(R.id.etLaboratory);
        etRadiology = (EditText) findViewById(R.id.etRadiology);
        etMedicRehab = (EditText) findViewById(R.id.etMedicRehab);
        etNutrition = (EditText) findViewById(R.id.etNutrition);
        etAmbulance = (EditText) findViewById(R.id.etAmbulance);
        etRoomFacility = (EditText) findViewById(R.id.etRoomFacility);
        etCleaningService = (EditText) findViewById(R.id.etCleaningService);
        etSpecialist = (EditText) findViewById(R.id.etSpecialist);
        etGeneral = (EditText) findViewById(R.id.etGeneral);
        etNurse = (EditText) findViewById(R.id.etNurse);
        etCafetaria = (EditText) findViewById(R.id.etCafetaria);

        btnNextSecond = (Button) findViewById(R.id.btnNextSecond);
        btnNextSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surveyReasonParking = etParking.getText().toString();
                String surveyReasonSecurity = etSecurity.getText().toString();
                String surveyReasonAdmission = etAdmission.getText().toString();
                String surveyReasonBilling = etBilling.getText().toString();
                String surveyReasonPharmacy = etPharmacy.getText().toString();
                String surveyReasonLaboratory = etLaboratory.getText().toString();
                String surveyReasonRadiology = etRadiology.getText().toString();
                String surveyReasonMedicRehab = etMedicRehab.getText().toString();
                String surveyReasonNutrition = etNutrition.getText().toString();
                String surveyReasonAmbulance = etAmbulance.getText().toString();
                String surveyReasonRoomFacility = etRoomFacility.getText().toString();
                String surveyReasonCleaningService = etCleaningService.getText().toString();
                String surveyReasonSpecialist = etSpecialist.getText().toString();
                String surveyReasonGeneral = etGeneral.getText().toString();
                String surveyReasonNurse = etNurse.getText().toString();
                String surveyReasonCafetaria = etCafetaria.getText().toString();

                int parking = radioGroupParking.getCheckedRadioButtonId();
                int security = radioGroupSecurity.getCheckedRadioButtonId();
                int admission = radioGroupAdmission.getCheckedRadioButtonId();
                int billing = radioGroupBilling.getCheckedRadioButtonId();
                int pharmacy = radioGroupPharmacy.getCheckedRadioButtonId();
                int laboratory = radioGroupLaboratory.getCheckedRadioButtonId();
                int radiology = radioGroupRadiology.getCheckedRadioButtonId();
                int medicRehab = radioGroupMedicRehab.getCheckedRadioButtonId();
                int nutrition = radioGroupNutrition.getCheckedRadioButtonId();
                int ambulance = radioGroupAmbulance.getCheckedRadioButtonId();
                int roomFacility = radioGroupRoomFacility.getCheckedRadioButtonId();
                int cleaningService = radioGroupCleaningService.getCheckedRadioButtonId();
                int specialist = radioGroupSpecialist.getCheckedRadioButtonId();
                int general = radioGroupGeneral.getCheckedRadioButtonId();
                int nurse = radioGroupNurse.getCheckedRadioButtonId();
                int cafetaria = radioGroupCafetaria.getCheckedRadioButtonId();

                RadioButton radioButtonParking = (RadioButton) findViewById(parking);
                RadioButton radioButtonSecurity = (RadioButton) findViewById(security);
                RadioButton radioButtonAdmission = (RadioButton) findViewById(admission);
                RadioButton radioButtonBilling = (RadioButton) findViewById(billing);
                RadioButton radioButtonPharmacy = (RadioButton) findViewById(pharmacy);
                RadioButton radioButtonLaboratory = (RadioButton) findViewById(laboratory);
                RadioButton radioButtonRadiology = (RadioButton) findViewById(radiology);
                RadioButton radioButtonMedicRehab = (RadioButton) findViewById(medicRehab);
                RadioButton radioButtonNutrition = (RadioButton) findViewById(nutrition);
                RadioButton radioButtonAmbulance = (RadioButton) findViewById(ambulance);
                RadioButton radioButtonRoomFacility = (RadioButton) findViewById(roomFacility);
                RadioButton radioButtonCleaningService = (RadioButton) findViewById(cleaningService);
                RadioButton radioButtonSpecialist = (RadioButton) findViewById(specialist);
                RadioButton radioButtonGeneral = (RadioButton) findViewById(general);
                RadioButton radioButtonNurse = (RadioButton) findViewById(nurse);
                RadioButton radioButtonCafetaria = (RadioButton) findViewById(cafetaria);

                if (parking != -1 && security != -1 && admission != -1 && billing != -1 && pharmacy != -1 && laboratory != -1 && radiology != -1 && medicRehab != -1 && nutrition != -1 && ambulance != -1 && roomFacility != -1 && cleaningService != -1 && specialist != -1 && general != -1 && nurse != -1 && cafetaria != -1) {
                    String surveyParking = String.valueOf(radioButtonParking.getText());
                    String surveySecurity = String.valueOf(radioButtonSecurity.getText());
                    String surveyAdmission = String.valueOf(radioButtonAdmission.getText());
                    String surveyBilling = String.valueOf(radioButtonBilling.getText());
                    String surveyPharmacy = String.valueOf(radioButtonPharmacy.getText());
                    String surveyLaboratory = String.valueOf(radioButtonLaboratory.getText());
                    String surveyRadiology = String.valueOf(radioButtonRadiology.getText());
                    String surveyMedicRehab = String.valueOf(radioButtonMedicRehab.getText());
                    String surveyNutrition = String.valueOf(radioButtonNutrition.getText());
                    String surveyAmbulance = String.valueOf(radioButtonAmbulance.getText());
                    String surveyRoomFacility = String.valueOf(radioButtonRoomFacility.getText());
                    String surveyCleaningService = String.valueOf(radioButtonCleaningService.getText());
                    String surveySpecialist = String.valueOf(radioButtonSpecialist.getText());
                    String surveyGeneral = String.valueOf(radioButtonGeneral.getText());
                    String surveyNurse = String.valueOf(radioButtonNurse.getText());
                    String surveyCafetaria = String.valueOf(radioButtonCafetaria.getText());

                    Intent thirdSurveyForm = new Intent(SecondSurveyFormActivity.this, ThirdSurveyFormActivity.class);
                    thirdSurveyForm.putExtra("surveyParking", surveyParking);
                    thirdSurveyForm.putExtra("surveySecurity", surveySecurity);
                    thirdSurveyForm.putExtra("surveyAdmission", surveyAdmission);
                    thirdSurveyForm.putExtra("surveyBilling", surveyBilling);
                    thirdSurveyForm.putExtra("surveyPharmacy", surveyPharmacy);
                    thirdSurveyForm.putExtra("surveyLaboratory", surveyLaboratory);
                    thirdSurveyForm.putExtra("surveyRadiology", surveyRadiology);
                    thirdSurveyForm.putExtra("surveyMedicRehab", surveyMedicRehab);
                    thirdSurveyForm.putExtra("surveyNutrition", surveyNutrition);
                    thirdSurveyForm.putExtra("surveyAmbulance", surveyAmbulance);
                    thirdSurveyForm.putExtra("surveyRoomFacility", surveyRoomFacility);
                    thirdSurveyForm.putExtra("surveyCleaningService", surveyCleaningService);
                    thirdSurveyForm.putExtra("surveySpecialist", surveySpecialist);
                    thirdSurveyForm.putExtra("surveyGeneral", surveyGeneral);
                    thirdSurveyForm.putExtra("surveyNurse", surveyNurse);
                    thirdSurveyForm.putExtra("surveyCafetaria", surveyCafetaria);
                    thirdSurveyForm.putExtra("surveyAge", surveyAge);
                    thirdSurveyForm.putExtra("surveySex", surveySex);
                    thirdSurveyForm.putExtra("surveyPayment", surveyPayment);
                    thirdSurveyForm.putExtra("surveyOrigin", surveyOrigin);
                    thirdSurveyForm.putExtra("surveyTanggalDirawat", surveyTanggalDirawat);
                    thirdSurveyForm.putExtra("surveyNamaDokter", surveyNamaDokter);
                    thirdSurveyForm.putExtra("surveyRuangan", surveyRuangan);
                    //alasan
                    thirdSurveyForm.putExtra("surveyReasonParking", surveyReasonParking);
                    thirdSurveyForm.putExtra("surveyReasonSecurity", surveyReasonSecurity);
                    thirdSurveyForm.putExtra("surveyReasonAdmission", surveyReasonAdmission);
                    thirdSurveyForm.putExtra("surveyReasonBilling", surveyReasonBilling);
                    thirdSurveyForm.putExtra("surveyReasonPharmacy", surveyReasonPharmacy);
                    thirdSurveyForm.putExtra("surveyReasonLaboratory", surveyReasonLaboratory);
                    thirdSurveyForm.putExtra("surveyReasonRadiology", surveyReasonRadiology);
                    thirdSurveyForm.putExtra("surveyReasonMedicRehab", surveyReasonMedicRehab);
                    thirdSurveyForm.putExtra("surveyReasonNutrition", surveyReasonNutrition);
                    thirdSurveyForm.putExtra("surveyReasonAmbulance", surveyReasonAmbulance);
                    thirdSurveyForm.putExtra("surveyReasonRoomFacility", surveyReasonRoomFacility);
                    thirdSurveyForm.putExtra("surveyReasonCleaningService", surveyReasonCleaningService);
                    thirdSurveyForm.putExtra("surveyReasonSpecialist", surveyReasonSpecialist);
                    thirdSurveyForm.putExtra("surveyReasonGeneral", surveyReasonGeneral);
                    thirdSurveyForm.putExtra("surveyReasonNurse", surveyReasonNurse);
                    thirdSurveyForm.putExtra("surveyReasonCafetaria", surveyReasonCafetaria);
                    startActivity(thirdSurveyForm);


                } else {
                    Toast.makeText(SecondSurveyFormActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

}
