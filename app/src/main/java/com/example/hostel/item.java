package com.example.hostel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class item {
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    String Status;



    public void setReg(String reg) {
        Reg = reg;
    }

    String Reg;

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    String Floor;


    public item(String name, String stat, String reg, String floor) {

        this.name = name;
        this.Status = stat;
        this.Reg = reg;
        this.Floor = floor;


    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setstatus(String name) {
        this.Status = name;
    }
    public String getStatus() {
        return Status;
    }
    public String getReg()
    {
        return Reg;
    }

    String name;


}
