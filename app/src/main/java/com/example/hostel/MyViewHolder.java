package com.example.hostel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView Nameview;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Nameview = itemView.findViewById(R.id.name);
    }
}
