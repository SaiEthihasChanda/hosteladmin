package com.example.hostel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class FloorC extends Fragment {
    ArrayList<String> list;
    RecyclerView recyclerViewC;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    Button buttonC;


    public FloorC() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_floor_c, container, false);
        List<item> items = new ArrayList<item>();
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //buttonC = view.findViewById(R.id.add_eventC);





        recyclerViewC = view.findViewById(R.id.C);
        recyclerViewC.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewC.setAdapter(new MyAdapter(getContext(),items));



        return view;
    }



}