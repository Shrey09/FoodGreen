package com.example.foodgreen;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity implements View.OnClickListener {
    android.support.v7.widget.Toolbar toolbar;
    private static final String TAG = "EmailPassword";

    private TextView create_acc, forgot_pass ;
    private Button btnLogin;
    private EditText Email, Password;
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        create_acc = findViewById(R.id.textcreate);
        forgot_pass = findViewById(R.id.textforgotpass);
        btnLogin = findViewById(R.id.btn_login);
        Email = findViewById(R.id.login_email);

        Password = findViewById(R.id.login_passw);

        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Green");
        toolbar.setTitleTextColor(Color.BLACK);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
            }
        };

        btnLogin.setOnClickListener(this);

        create_acc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this, activity_register.class);
                startActivity(intent);
            }

        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this, activity_register.class);
                startActivity(intent);
            }

        });

    }


    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            email = Email.getText().toString();
            password = Password.getText().toString();

            if (email.length() == 0) {
                Email.setError("Enter email address");
            } else if (password.length() == 0) {
                Password.setError("Enter password");
            } else {
                btnLogin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                signIn(email, password);
            }

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void signIn(String email, String password) {
        email = Email.getText().toString();
        password = Password.getText().toString();
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(activity_login.this, "EmailId or Password is incorrect",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }
}

