package com.example.hostel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FloorA extends Fragment {
    ArrayList<String> list;
    RecyclerView recyclerView;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;




    public FloorA() {
        // Required empty public constructor
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_floor_a, container, false);
        List<item> items = new ArrayList<item>();
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("Sai Ethihas","Present");
        user.put("Satya Bharat","Present");
        user.put("Pranav Krishna","Present");
        user.put("Shivasis Das","Present");
        user.put("Adishesan","Present");
        mStore.collection("Floor A").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getContext(), "user added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });




        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(),items));



        return view;
    }


}