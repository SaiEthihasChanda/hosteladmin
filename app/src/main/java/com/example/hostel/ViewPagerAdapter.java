package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            return new FloorA();
        }
        else if(position==1) {
            return new FloorB();
        }
        else {
            return new FloorC();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Floor A";
        }
        if(position==1){
            return "Floor B";
        }
        else{
            return "Floor C";
        }
    }
}

