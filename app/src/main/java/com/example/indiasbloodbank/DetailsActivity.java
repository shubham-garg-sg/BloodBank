package com.example.indiasbloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText weight;
    private EditText haemoglobin;
    private EditText contact;
    private Button submit;
    String userID;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    private View.OnClickListener onSubmitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            /*String nameText = name.getText().toString().trim();
            String ageText = age.getText().toString().trim();
            String genderText = gender.getText().toString().trim();
            String weightText = weight.getText().toString().trim();
            String haemoglobinText = haemoglobin.getText().toString().trim();*/

            if (validate()) {
                submitDetails();
            }

        }
    };

    private void submitDetails() {

        String nameText = name.getText().toString().trim();
        int ageText = Integer.parseInt( age.getText().toString() );
        String genderText = gender.getText().toString().trim();
        int weightText = Integer.parseInt(weight.getText().toString());
        int haemoglobinText = Integer.parseInt(haemoglobin.getText().toString());
        long contactText = Long.parseLong(contact.getText().toString());


        userID = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("name", nameText);
        user.put("age", ageText);
        user.put("gender", genderText);
        user.put("weight", weightText);
        user.put("haemoglobin", haemoglobinText);
        user.put("contact", contactText);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(DetailsActivity.this, "User Profile Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });

    }

    private boolean validate() {

        boolean valid = true;

        if (name.length() == 0) {
            name.setError("Enter Name");
            valid = false;
        }
        if (age.length() == 0) {
            age.setError("Enter age");
            valid = false;
        }
        if (gender.length() == 0) {
            gender.setError("Enter gender");
            valid = false;
        }
       if (weight.length() == 0) {
            weight.setError("Enter weight");
            valid = false;
        }
       if (haemoglobin.length() == 0) {
            haemoglobin.setError("Enter haemoglobin level");
            valid = false;
        }
       if (contact.length() == 0) {
           contact.setError("Enter contact number");
       }
        return valid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        weight = findViewById(R.id.weight);
        haemoglobin = findViewById(R.id.haemoglobin);
        contact = findViewById(R.id.contact);
        submit = findViewById(R.id.submit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        submit.setOnClickListener(onSubmitListener);


    }
}