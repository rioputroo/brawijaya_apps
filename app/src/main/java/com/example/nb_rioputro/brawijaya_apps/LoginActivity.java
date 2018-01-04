package com.example.nb_rioputro.brawijaya_apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.sinch.android.rtc.SinchError;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends BaseActivity implements SinchService.StartFailedListener {

    EditText etUsername, etPassword;
    Button btnLogin, btnSignUp;
    ScrollView mLoginLayout;
    AVLoadingIndicatorView loadingIndicator;
    ProgressBar progressBar;
    FirebaseAuth auth;
    Context context;
    ImageView iv_homeLogin;
    private ProgressDialog mSpinner;


    String mUsername, mPassword, username, name, role, id;

    private boolean loggedin = false;
    boolean isExist = false;
    boolean patientExists = false;

    String status;

    String mrnPasien, namaPasien, regidPasien, roomPasien;

    JSONObject userObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        fetchUsers();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mLoginLayout = (ScrollView) findViewById(R.id.scrollView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_signin);
        iv_homeLogin = (ImageView) findViewById(R.id.iv_homelogin);

        iv_homeLogin.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.logo_baru_brawi, 100, 100));


//
//        btnSignUp = (Button) findViewById(R.id.btnSignUp);
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
//                startActivity(signupIntent);
//            }
//        });

        //loadingIndicator.setVisibility(View.GONE);
        btnLogin.setEnabled(false);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginClicked();
            }
        });
    }




    private void testLogin() {
        //Log.d("userObject", userObject.toString());
        progressBar.setVisibility(View.VISIBLE);
        Iterator<String> keys = userObject.keys();
        Log.d("mUsername", mUsername);


        while (keys.hasNext()) {
            String key = (String) keys.next();
            try {
                JSONObject user = new JSONObject(userObject.get(key).toString());
                mrnPasien = user.getString("nmr");
                if (mUsername.equalsIgnoreCase(mrnPasien) && mPassword.equalsIgnoreCase(mrnPasien)) {
                    isExist = true;

                    progressBar.setVisibility(View.GONE);

                    namaPasien = user.getString("nama");
                    regidPasien = user.getString("regid");
                    roomPasien = user.getString("room");


//                    if (patientExists == false) {
//                        insertPasien();
//                    }

                    StringRequest fetchUser2 = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("")) {
                                insertPasien();
                            }
                            SharedPreferences spLogin = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor spEditor = spLogin.edit();

                            spEditor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            spEditor.putString(Config.NAME_SHARED_PREF, namaPasien);
                            spEditor.putString(Config.USERNAME_SHARED_PREF, mrnPasien);
                            spEditor.putString(Config.REGID_SHARED_PREF, regidPasien);
                            spEditor.putString(Config.ROOM_SHARED_PREF, roomPasien);
                            spEditor.putString(Config.ROLE_SHARED_PREF, "Patient");
                            spEditor.putString(Config.ID_SHARED_PREF, mrnPasien);

                            Intent patientIntent = new Intent(LoginActivity.this, MainActivity.class);
                            patientIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(patientIntent);

                            spEditor.commit();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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

                    fetchUser2.setRetryPolicy(new RetryPolicy() {
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
                    rq.add(fetchUser2);


                    break;
                } else {
                    Log.d("loginstatus", "Belum Sama");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (isExist == false) {
            progressBar.setVisibility(View.GONE);
            Snackbar loginErr = Snackbar.make(mLoginLayout, "Login Failed, please check your username and password", Snackbar.LENGTH_LONG);
            loginErr.show();
        }


    }

    private void insertPasien() {
        StringRequest insertPatient = new StringRequest(Request.Method.POST, Config.INSERT_PATIENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("insertResponse ", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("insertResponse ", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", mrnPasien);
                params.put("password", mrnPasien);
                params.put("nama", namaPasien);
                params.put("room", roomPasien);

                return params;
            }
        };

        insertPatient.setRetryPolicy(new RetryPolicy() {
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
        rq.add(insertPatient);
    }

    private void fetchUsers() {
        String userFetchUrl = "http://119.235.208.66/?mod=apijson&cmd=patient&uid=66845&s=Jygb58RTkq2";

        StringRequest fetchUser = new StringRequest(Request.Method.GET, userFetchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("fetchUser ", response);
                try {
                    userObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fetchUser ", error.toString());
            }
        });

        fetchUser.setRetryPolicy(new RetryPolicy() {
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
        rq.add(fetchUser);
    }

    private void fetchUsers2() {
        StringRequest fetchUser2 = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("")) {
                    //Toast.makeText(getApplicationContext(), "pasien belum terdaftar", Toast.LENGTH_SHORT).show();
                    status = "kosong";
                } else if (!response.isEmpty()) {
                    status = "ada";
                    //Toast.makeText(getApplicationContext(), String.valueOf(patientExists), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), "di fetch user2 " + status, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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

        fetchUser2.setRetryPolicy(new RetryPolicy() {
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
        rq.add(fetchUser2);
    }


    private void userLogin() {
        progressBar.setVisibility(View.VISIBLE);

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

//                        switch (role) {
//                            case "Patient":
                        Intent patientIntent = new Intent(LoginActivity.this, MainActivity.class);
                        patientIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(patientIntent);
//                                break;
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
//                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar volleyErr = Snackbar.make(mLoginLayout, error.toString(), Snackbar.LENGTH_LONG);
                volleyErr.show();
                Log.d("errLogin: ", error.toString());
                progressBar.setVisibility(View.GONE);
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
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        //Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the requested height and width.

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    protected void onServiceConnected() {
        btnLogin.setEnabled(true);
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
    private void loginClicked() {
        mUsername = etUsername.getText().toString();
        mPassword = etPassword.getText().toString();

        String userName = mUsername;


        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
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
        if (mUsername.equalsIgnoreCase("")) {
            Snackbar userError = Snackbar.make(mLoginLayout, "Masukkan Username", Snackbar.LENGTH_LONG);
            userError.show();
        } else if (mPassword.equalsIgnoreCase("")) {
            Snackbar passError = Snackbar.make(mLoginLayout, "Masukkan Password", Snackbar.LENGTH_LONG);
            passError.show();
        } else {
//                    loadingIndicator.setVisibility(View.VISIBLE);
//                    userLogin();
//                    login();
            //newLogin();
            //fetchUsers2();
            if (mUsername.contains("nurse")) {
                //Toast.makeText(getApplicationContext(), "Nurse Login", Toast.LENGTH_SHORT).show();
                userLogin();
            } else {
                if (userObject != null) {
                    testLogin();
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak dapat mengambil data pasien. Silakan restart ulang aplikasi.", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }
}
