package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputNama;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseAuth auth2;
    private String email, password, nama;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();



        btnSignUp = (Button) findViewById(R.id.btnDaftar);
        inputEmail = (EditText) findViewById(R.id.email_signup);
        inputPassword = (EditText) findViewById(R.id.password_signup);
        inputNama = (EditText) findViewById(R.id.nama_signup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                nama = inputNama.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(getApplicationContext(), "Enter nama!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 character!", Toast.LENGTH_SHORT).show();
                    return;
                }

                signup();

            }
        });
    }

    private void signup() {
        progressBar.setVisibility(View.VISIBLE);

        //auth2
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://brawijaya-be227.firebaseio.com/")
                .setApiKey("AIzaSyBbg6uEcFz2qqPANsTF9EocoB0YC5Qs4dQ")
                .setApplicationId("brawijaya-be227").build();

        FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "brawiapps");

        auth2 = FirebaseAuth.getInstance(myApp);

        //create user
        auth2.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Toast.makeText(SignupActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                            finish();
                            insertData();


                        }
                    }
                });
    }

    private void insertData() {
        FirebaseUser user = auth2.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            Log.d("uid_user_baru: ",uid);
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("users").setValue(uid);


        } else {
            Log.d("uid_user: ", "User null!");
        }

//        auth2.signOut();
//        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
