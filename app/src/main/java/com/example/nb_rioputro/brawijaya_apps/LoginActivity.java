package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnSignUp;
    ScrollView mLoginLayout;
    AVLoadingIndicatorView loadingIndicator;
    ProgressBar progressBar;
    FirebaseAuth auth;

    String mUsername, mPassword, username, name, role, id;

    private boolean loggedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mLoginLayout = (ScrollView) findViewById(R.id.scrollView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_signin);


//        btnSignUp = (Button) findViewById(R.id.btnSignUp);
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
//                startActivity(signupIntent);
//            }
//        });
//
        //loadingIndicator.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = etUsername.getText().toString();
                mPassword = etPassword.getText().toString();


                if (mUsername.equalsIgnoreCase("")) {
                    Snackbar userError = Snackbar.make(mLoginLayout, "Masukkan Username", Snackbar.LENGTH_LONG);
                    userError.show();
                } else if (mPassword.equalsIgnoreCase("")) {
                    Snackbar passError = Snackbar.make(mLoginLayout, "Masukkan Password", Snackbar.LENGTH_LONG);
                    passError.show();
                } else {
//                    loadingIndicator.setVisibility(View.VISIBLE);
                    userLogin();
//                    login();
                }
            }
        });
    }

//    private void login() {
//        progressBar.setVisibility(View.VISIBLE);
//
//        //authenticate user
//        auth.signInWithEmailAndPassword(mUsername, mPassword)
//                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressBar.setVisibility(View.GONE);
//                        if (!task.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_LONG).show();
//                        } else {
//                            startActivity(new Intent(LoginActivity.this,HomePatientActivity.class));
//                            finish();
//                        }
//                    }
//                });
//    }

    private void userLogin() {
        StringRequest loginRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respLogin: ", response);
                if (response.equalsIgnoreCase("")) {
                    Snackbar loginErr = Snackbar.make(mLoginLayout, "Login Failed, please check your username and password", Snackbar.LENGTH_LONG);
                    loginErr.show();
                } else {
                    try {
                        JSONObject userObj = new JSONObject(response);
                        username = userObj.getString("username_user");
                        name = userObj.getString("nama_user");
                        role = userObj.getString("role_user");
                        id = userObj.getString("id_user");

                        SharedPreferences spLogin = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor spEditor = spLogin.edit();

                        spEditor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                        spEditor.putString(Config.USERNAME_SHARED_PREF, username);
                        spEditor.putString(Config.NAME_SHARED_PREF, name);
                        spEditor.putString(Config.ROLE_SHARED_PREF, role);
                        spEditor.putString(Config.ID_SHARED_PREF, id);

                        spEditor.commit();

                        switch (role) {
                            case "Patient":
                                Intent patientIntent = new Intent(LoginActivity.this, HomePatientActivity.class);
                                patientIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(patientIntent);
                                break;
//                            case "Nurse":
//                                Intent nurseIntent = new Intent(LoginActivity.this, NurseActivity.class);
//                                nurseIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(nurseIntent);
//                                break;
//                            case "Chef":
//                                Intent chefIntent = new Intent(LoginActivity.this, ChefActivity.class);
//                                chefIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(chefIntent);
//                                break;
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar volleyErr = Snackbar.make(mLoginLayout, error.toString(), Snackbar.LENGTH_LONG);
                volleyErr.show();
                Log.d("errLogin: ", error.toString());
                //loadingIndicator.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", mUsername);
                params.put("password", mPassword);

                return params;
            }
        };

        loginRequest.setRetryPolicy(new RetryPolicy() {
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
        rq.add(loginRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedin = sp.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        if (loggedin) {
            Intent intent = new Intent(LoginActivity.this, HomePatientActivity.class);
            startActivity(intent);
        }
    }
}
