package com.example.duanmau_ps12545_lycaothanggd;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import adapter.adapterHoadonchitiet;
import dao.Hoadonchitietdao;
import model.Hoadonchitiet;

public class hoadonchitiet_rcv2 extends AppCompatActivity {
    RecyclerView rcvhdct;
    adapterHoadonchitiet adapter;
    ArrayList<Hoadonchitiet> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadonchutiet_rcv2);
        rcvhdct=findViewById(R.id.rcv_hdct2);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(hoadonchitiet_rcv2.this);
        rcvhdct.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        final String mahoadon1=getIntent().getExtras().getString("mahoadon2");
        list= Hoadonchitietdao.reaAll(hoadonchitiet_rcv2.this,mahoadon1);
        adapter=new adapterHoadonchitiet(hoadonchitiet_rcv2.this,list);
        rcvhdct.setAdapter(adapter);
        Log.e("loi", "onCreate: "+mahoadon1);
    }
}
