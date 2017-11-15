package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TabProfilePatient extends Fragment {
    RecyclerView recycler_doctor;
    private ContactsAdapter contactsAdapter;
    private List<Contacts> contactsList = new ArrayList<>();
    String mId, mRole, mUsername;
    ProgressBar pbHomeChat;
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_profile_patient, container, false);

        pbHomeChat = (ProgressBar) rootView.findViewById(R.id.pb_homeChat);
        pbHomeChat.setVisibility(View.VISIBLE);

        usersList = (ListView) rootView.findViewById(R.id.usersList);
        noUsersText = (TextView) rootView.findViewById(R.id.noUsersText);

//        recycler_doctor = (RecyclerView) rootView.findViewById(R.id.recycler_doctor);
//        recycler_doctor.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recycler_doctor.setLayoutManager(layoutManager);

        SharedPreferences sp = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mId = sp.getString(Config.ID_SHARED_PREF, "error getting id");
        mUsername = sp.getString(Config.USERNAME_SHARED_PREF, "error getting username");
        Log.d("Isi mUsername: ", mUsername);
        mRole = sp.getString(Config.ROLE_SHARED_PREF, "error getting role");
//
//        contactsAdapter = new ContactsAdapter(usersList);
//        recycler_doctor.setAdapter(contactsAdapter);

        //getListContacts();
        newGetContacts();

        return rootView;
    }

    private void newGetContacts() {
        final String getContactsUrl = "https://brawijaya-be227.firebaseio.com/users.json";

        final StringRequest listUsersReq = new StringRequest(Request.Method.GET, getContactsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doOnSuccess(response);
                Log.d("RESPONSE: ", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Can't retrieve users list " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        listUsersReq.setRetryPolicy(new RetryPolicy() {
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

        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(listUsersReq);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserDetails.chatWith = al.get(i);
                Log.d("chatwith di itemclick:", UserDetails.chatWith);
                startActivity(new Intent(getActivity().getApplicationContext(), ChatsActivity.class));
            }
        });
    }

    private void doOnSuccess(String s) {
        try {
            JSONObject obj = new JSONObject(s);
            Iterator<String> iter = obj.keys();
            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();
                Log.d("mUsername di onsuccess:", mUsername);

                if (!key.equals(UserDetails.username) && !key.equals(mUsername)) {
                    al.add(key);
                    totalUsers++;
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers <= 1) {
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        } else {
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, al));
        }

        pbHomeChat.setVisibility(View.GONE);
    }

//    private void getListContacts() {
//
//        StringRequest getContactsRequest = new StringRequest(Request.Method.POST, Config.GET_CONTACTS_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("ListContacts: ", response);
//
//                try {
//                    JSONArray contactsArr = new JSONArray(response);
//
//                    for (int i = 0; i < contactsArr.length(); i++) {
//                        JSONObject obj = contactsArr.getJSONObject(i);
//                        String namakontak = obj.getString("nama_user");
//                        String rolekontak = obj.getString("role_user");
//
//                        Contacts contacts = new Contacts(namakontak);
//                        contactsList.add(contacts);
//
//                    }
//                    contactsAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("ListContactsErr: ", error.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("id", mId);
//
//                return params;
//            }
//        };
//
//        getContactsRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//
//        RequestQueue rq = Volley.newRequestQueue(getContext());
//        rq.add(getContactsRequest);
//    }

}
