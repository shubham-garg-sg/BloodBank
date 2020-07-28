package com.example.indiasbloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView donorsList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firebaseFirestore = FirebaseFirestore.getInstance();
        donorsList = findViewById(R.id.recyclerView);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        //Query
        Query query = firebaseFirestore.collection("Users");

        //RecyclerOptions
        FirestoreRecyclerOptions<DonorModel> options = new FirestoreRecyclerOptions.Builder<DonorModel>()
                .setQuery(query, DonorModel.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<DonorModel, DonorViewHolder>(options) {
            @NonNull
            @Override
            public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_donor_single, parent, false);
                return new DonorViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull DonorViewHolder holder, int position, @NonNull DonorModel model) {
                holder.donor_name.setText(model.getName());
                holder.donor_age.setText(String.valueOf(model.getAge()));
                holder.donor_gender.setText(model.getGender());
                holder.donor_weight.setText(String.valueOf(model.getWeight()));
                holder.donor_haemoglobin.setText(String.valueOf(model.getHaemoglobin()));
                holder.donor_contact.setText(String.valueOf(model.getContact()));


            }
        };

         donorsList.setHasFixedSize(true);
         LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         donorsList.setLayoutManager(layoutManager);
         donorsList.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(donorsList.getContext(), layoutManager.getOrientation());

        donorsList.addItemDecoration(dividerItemDecoration);

        }


    protected class DonorViewHolder extends RecyclerView.ViewHolder{

        private TextView donor_name;
        private TextView donor_age;
        private TextView donor_gender;
        private TextView donor_weight;
        private TextView donor_haemoglobin;
        private TextView donor_contact;

        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);

            donor_name = itemView.findViewById(R.id.donor_name);
            donor_age = itemView.findViewById(R.id.donor_age);
            donor_gender = itemView.findViewById(R.id.donor_gender);
            donor_weight = itemView.findViewById(R.id.donor_weight);
            donor_haemoglobin = itemView.findViewById(R.id.donor_haemoglobin);
            donor_contact = itemView.findViewById(R.id.donor_contact);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

}