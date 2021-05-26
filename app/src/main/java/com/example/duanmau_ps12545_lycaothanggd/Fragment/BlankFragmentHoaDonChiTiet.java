package com.example.duanmau_ps12545_lycaothanggd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.duanmau_ps12545_lycaothanggd.R;


public class BlankFragmentHoaDonChiTiet extends Fragment {


    public BlankFragmentHoaDonChiTiet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hoadonchitiet, container, false);
        return view;
    }
}
