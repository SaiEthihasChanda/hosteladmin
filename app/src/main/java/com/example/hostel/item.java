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


    public item(String name, String stat, String reg) {

        this.name = name;
        this.Status = stat;
        this.Reg = reg;


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
