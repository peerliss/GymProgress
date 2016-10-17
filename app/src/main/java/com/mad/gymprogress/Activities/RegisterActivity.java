/**
 * Activity which allows a user to register to the apps Firebase Database
 */

package com.mad.gymprogress.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mad.gymprogress.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailEt;
    private EditText mPasswordEt;
    private EditText mConfirmPasswordEt;
    private Button mRegisterBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view and toolbar
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get current firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize fields
        mEmailEt = (EditText) findViewById(R.id.register_email);
        mPasswordEt = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordEt = (EditText) findViewById(R.id.register_confirmPassword);
        mRegisterBtn = (Button) findViewById(R.id.register_button);

        // Set button to register method
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    // Add user to database if conditions are met

    /**
     * Add user to database if Fields pass requirements
     */
    private void register() {
        final String email = mEmailEt.getText().toString();
        final String password = mPasswordEt.getText().toString();
        final String confirmPassword = mConfirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.enter_email, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.password_short, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.matches(confirmPassword)) {
            Toast.makeText(getApplicationContext(), R.string.confirm_password_mismatch, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.contains("@")) {
            Toast.makeText(getApplicationContext(), R.string.enter_valid_email, Toast.LENGTH_SHORT).show();
            return;
        }

        // Create user in the current firebase auth
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "The email is already registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "You have successfully signed up!", Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(email, password);
                }
            }
        });
    }
}
