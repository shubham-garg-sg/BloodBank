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

public class SignUpActivity extends AppCompatActivity {

    private EditText emailId;
    private EditText password;
    private Button signup_btn;
    private TextView back_to_login;
    private ProgressBar progressBar;


    private FirebaseAuth firebaseAuth;


    private View.OnClickListener onBacktoLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    };

    private View.OnClickListener onSignUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            progressBar.setVisibility(View.VISIBLE);

            if (validate()){
                register();
            }

        }
    };

    private boolean validate() {

        boolean valid = true;

        if (emailId.length()==0) {
            Toast.makeText(this, "Please enter Email-ID", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (password.length()==0) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }


    private void register() {

        final String user_email_Id = emailId.getText().toString().trim();
        final String user_password = password.getText().toString().trim();


        firebaseAuth.createUserWithEmailAndPassword(user_email_Id, user_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Registered Successfully. You can login now.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Could not register, Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

       if (firebaseAuth.getCurrentUser() != null) {
            finish();
           startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
       }

        emailId = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        signup_btn = findViewById(R.id.signup_btn);
        back_to_login = findViewById(R.id.login_here);
        progressBar = findViewById(R.id.progressbar);

        signup_btn.setOnClickListener(onSignUpListener);
        back_to_login.setOnClickListener(onBacktoLoginListener);

        /*if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
            finish();
        }*/
    }
}