package com.example.duanmau_ps12545_lycaothanggd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau_ps12545_lycaothanggd.R;

import dao.Hoadonchitietdao;

public class Fragment_thongke extends Fragment {
    TextView txtdate,txtyear,txtmont;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thongke,container,false);
        txtdate=view.findViewById(R.id.tvtkdate);
        txtyear=view.findViewById(R.id.tvtkyear);
        txtmont=view.findViewById(R.id.tvtkmonth);
        txtdate.setText("Theo ngày : " + Hoadonchitietdao.getDoanhThuTheoNgay(getContext())+" " + "VNĐ");
        txtyear.setText("Theo năm :"+ Hoadonchitietdao.getDoanhThuTheoNam(getContext())+" " + "VNĐ");
        txtmont.setText("Theo tháng :" +Hoadonchitietdao.getDoanhThuTheoThang(getContext())+" " + "VNĐ");





        return view;
    }
}
