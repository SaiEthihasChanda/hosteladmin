package com.example.hostel;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class FloorB extends Fragment {
    ArrayList<String> list;
    RecyclerView recyclerView;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;

    Button buttonB;



    public FloorB() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_floor_b, container, false);
        List<item> items = new ArrayList<item>();
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        buttonB = view.findViewById(R.id.add_studentB);

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupdialog();


            }
        });







        recyclerView = view.findViewById(R.id.B);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(),items));
        refreshB();



        return view;
    }

    private void refreshB() {

        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

        // Convert the date to a string in the desired format
        String formattedDate = dateFormat.format(currentDate);

        List<item> items = new ArrayList<item>();
        CollectionReference collectionRef = mStore.collection("Floor B");

        collectionRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                if (document.contains(formattedDate)) {
                                    String name = document.getString("Name");
                                    String reg = document.getString("Registration Number");
                                    String stat = document.getString(formattedDate);
                                    items.add(new item(name,stat, reg));
                                }
                                if (!document.contains(formattedDate)) {
                                    Map<String, Object> today = new HashMap<>();
                                    today.put(formattedDate,"absent");
                                    document.getReference().update(today);
                                    refreshB();
                                    // Access data from each document

                                    // Do something with the data
                                }
                            }
                            recyclerView.setAdapter(new MyAdapter(getContext(), items));
                        } else {
                            Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });





    }

    private void showpopupdialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        dialogBuilder.setView(dialogView);
        EditText editTextInput = dialogView.findViewById(R.id.editTextInput);
        Button buttonConfirm = dialogView.findViewById(R.id.buttonConfirm);
        EditText reg = dialogView.findViewById(R.id.regnoinput);
        EditText room = dialogView.findViewById(R.id.roomNumber);

        AlertDialog alertDialog = dialogBuilder.create();
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editTextInput.getText().toString();
                if (!userInput.isEmpty()) {
                    // Perform a task with the user input
                    // For example, you can add the user input to Firestore
                    mStore = FirebaseFirestore.getInstance();
                    mAuth = FirebaseAuth.getInstance();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name",userInput.toString());
                    user.put("Registration Number",reg.getText().toString());
                    user.put("Room Number",room.getText().toString());
                    Toast.makeText(getContext(),"Adding Student Profile",Toast.LENGTH_SHORT).show();
                    mStore.collection("Floor B").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            refreshB();
                            Toast.makeText(getContext(), "user added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Dismiss the dialog
                    alertDialog.dismiss();
                } else {
                    // Handle empty input
                    Toast.makeText(getContext(), "Please enter text", Toast.LENGTH_SHORT).show();
                }

            }
        });
        alertDialog.show();

    }
    }


    // Inflate the layout for this fragment


