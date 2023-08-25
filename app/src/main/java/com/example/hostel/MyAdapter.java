package com.example.hostel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<item> items;
    FirebaseFirestore mStore;



    private int clickedPosition = RecyclerView.NO_POSITION; // Default value


    public MyAdapter(Context context, List<item> items;) {
        this.context = context;
        this.items = items;
    }
    public void setClickedPosition(int position) {
        clickedPosition = position;
        //notifyDataSetChanged(); // Refresh the RecyclerView
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Nameview.setText(items.get(position).getName());
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

        // Convert the date to a string in the desired format
        String formattedDate = dateFormat.format(currentDate);
        String inf = items.get(position).getStatus();
        String Floor = items.get(position).getFloor();
        if (inf.equals("absent")) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.GREEN);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                String marker = items.get(clickedPosition).getReg();
                //Toast.makeText(v.getContext(), marker,Toast.LENGTH_SHORT).show();
                mStore = FirebaseFirestore.getInstance();
                CollectionReference collectionRef = mStore.collection(Floor);

                collectionRef.whereEqualTo("Registration Number",marker)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    QuerySnapshot querySnapshot = task.getResult();
                                    for (QueryDocumentSnapshot document : querySnapshot){
                                        String attendance = document.getString(formattedDate);
                                        Map<String, Object> data = new HashMap<>();
                                        if(attendance.equals("absent")){

                                            data.put(formattedDate,"present");



                                        }
                                        else {
                                            data.put(formattedDate,"absent");
                                            document.getReference().update(data);

                                        }
                                        document.getReference().update(data);
                                    }
                                    //FloorA.refreshA();
                                    //FloorA floorA = new FloorA();
                                    ////floorA.refreshA();
                                    //FloorB floorB = new FloorB();
                                    //floorB.refreshB();

                                }
                                else{
                                    Toast.makeText(v.getContext(), "error retrieving data",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });





            }
        });
        //String marker = items.get(clickedPosition).getReg();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
