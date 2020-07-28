package com.example.indiasbloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailIdText;
    private EditText passwordText;
    private Button loginbtn;
    private TextView signup;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailIdText = findViewById(R.id.emailIdText);
        passwordText = findViewById(R.id.passwordText);
        loginbtn = findViewById(R.id.signin_btn);
        signup = findViewById(R.id.sign_up);
        progressBar = findViewById(R.id.progressbar);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
        }

        signup.setOnClickListener(onSignupListener);
        loginbtn.setOnClickListener(onLoginListener);

       /* signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailIdText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailIdText.setError("Enter EmailID");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordText.setError("Enter password");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

    }

    private View.OnClickListener onSignupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        }
    };

    private View.OnClickListener onLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            progressBar.setVisibility(View.VISIBLE);
            if (validate()) {
                login();
            }
        }
    };

    private void login() {

        String emailId = emailIdText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(emailId, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validate() {

        progressBar.setVisibility(View.GONE);
        boolean valid = true;

        if (emailIdText.length() == 0) {
            emailIdText.setError("Enter Email-ID");
            valid = false;
        }
        if (passwordText.length() == 0) {
            passwordText.setError("Enter Password");
            valid = false;
        }
        return valid;
    }
}