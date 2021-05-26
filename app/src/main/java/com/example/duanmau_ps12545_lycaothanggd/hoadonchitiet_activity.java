package com.example.duanmau_ps12545_lycaothanggd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Date;

import adapter.adapterHoadonchitiet;
import dao.Hoadonchitietdao;
import dao.SachDAO;
import model.Hoadon;
import model.Hoadonchitiet;
import model.Sach;

public class hoadonchitiet_activity extends AppCompatActivity {
RecyclerView rcvhdct;
Button btnthanhtoan,btnthem;
EditText mahoadon,etmamusic;
TextView thanhtoan;
adapterHoadonchitiet adapter;
ArrayList<Hoadonchitiet> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadonchitiet_activity);
        rcvhdct=findViewById(R.id.rcvhoadonct);
        mahoadon=findViewById(R.id.edmahoadon);
        etmamusic=findViewById(R.id.edmasach);
        btnthanhtoan=findViewById(R.id.btthanhtoan);
        btnthem=findViewById(R.id.btaddhdct);
        thanhtoan=findViewById(R.id.txthdcttong);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(hoadonchitiet_activity.this);
        rcvhdct.setLayoutManager(layoutManager);
        final String mahoadon1=getIntent().getExtras().getString("mahoadon");
        mahoadon.setText(mahoadon1);
        Log.e("loi", "onCreate: "+mahoadon1);
        list=new ArrayList<>();
        list= Hoadonchitietdao.reaAll(hoadonchitiet_activity.this,mahoadon1);
        adapter=new adapterHoadonchitiet(hoadonchitiet_activity.this,list);
        rcvhdct.setAdapter(adapter);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = SachDAO.getSachbyID(hoadonchitiet_activity.this,(etmamusic.getText().toString()));
                   if(Hoadonchitietdao.create(hoadonchitiet_activity.this,new Hoadonchitiet(new Hoadon(mahoadon.getText().toString(),new Date()),sach)));
                Toast.makeText(hoadonchitiet_activity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                       list.clear();
                       list.addAll(Hoadonchitietdao.reaAll(hoadonchitiet_activity.this,mahoadon1));
                       adapter.notifyDataSetChanged();
            }
        });

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tong= (int) Hoadonchitietdao.thanhtoan(hoadonchitiet_activity.this,mahoadon.getText().toString());
                thanhtoan.setText("Thanh toan: "+tong);
            }
        });



    }
}