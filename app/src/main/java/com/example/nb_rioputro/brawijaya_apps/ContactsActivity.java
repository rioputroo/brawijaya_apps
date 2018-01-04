package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ContactsActivity extends BaseActivity {
    ImageButton imbBack, imbVidCall;
    RecyclerView rvContacts;

    private ListView contactsList;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<Contacts>();
    private ContactsAdapter contactsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //setSupportActionBar(toolbar);
        //asking for permissions here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.READ_PHONE_STATE}, 100);
        }

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //set the recyclerview for contacts
        rvContacts = (RecyclerView) findViewById(R.id.recycler_contacts);
        rvContacts.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvContacts.setLayoutManager(layoutManager);

        contactsAdapter = new ContactsAdapter(contactsArrayList);
        rvContacts.setAdapter(contactsAdapter);



        //check the role
        SharedPreferences spRole = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String curr_role = spRole.getString(Config.ROLE_SHARED_PREF, "cannot getting role");
        String username_user = spRole.getString(Config.USERNAME_SHARED_PREF, "cannot getting username");

        UserDetails.username = username_user;


        if (curr_role.equalsIgnoreCase("Patient")) {
            StringRequest fetchUser = new StringRequest(Request.Method.POST, Config.GET_CONTACTS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response user: ", response);
                    try {
                        JSONArray userArray = new JSONArray(response);

                        for (int i = 0; i < userArray.length(); i++) {
                            String nama_user = userArray
                                    .getJSONObject(i)
                                    .getString("nama_user");

                            String id_user = userArray
                                    .getJSONObject(i)
                                    .getString("id_user");

                            String username_user = userArray
                                    .getJSONObject(i)
                                    .getString("username_user");

                            String role_user = userArray
                                    .getJSONObject(i)
                                    .getString("role_user");

                            Contacts contacts = new Contacts(id_user, nama_user, username_user, role_user);
                            contactsArrayList.add(contacts);
                        }
                        contactsAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ContactsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

            RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
            rQueue.add(fetchUser);
        } else if (curr_role.equalsIgnoreCase("Nurse")) {
            StringRequest fetchUser = new StringRequest(Request.Method.POST, Config.GET_CONTACTSPATIENT_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response user: ", response);
                    try {
                        JSONArray userArray = new JSONArray(response);

                        for (int i = 0; i < userArray.length(); i++) {
                            String nama_user = userArray
                                    .getJSONObject(i)
                                    .getString("nama_user");

                            String id_user = userArray
                                    .getJSONObject(i)
                                    .getString("id_user");

                            String username_user = userArray
                                    .getJSONObject(i)
                                    .getString("username_user");

                            String role_user = userArray
                                    .getJSONObject(i)
                                    .getString("role_user");

                            Contacts contacts = new Contacts(id_user, nama_user, username_user, role_user);
                            contactsArrayList.add(contacts);
                        }
                        contactsAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ContactsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

            RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
            rQueue.add(fetchUser);
        }

    }

}
