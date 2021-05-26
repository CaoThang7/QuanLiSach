//package com.example.duanmau_ps12545_lycaothanggd.Fragment;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.duanmau_ps12545_lycaothanggd.R;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import adapter.theloaiadapter;
//import model.TheLoai;
//import model.User;
//
//public class Fragment_chi extends Fragment {
//    theloaiadapter theloaiadapterr;
//    FloatingActionButton Inserttheloai;
//    ArrayList<TheLoai> list=new ArrayList<>();
//    RecyclerView rcv;
//    DatabaseReference mDatabase;
//    LinearLayoutManager mLayoutManager;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.fragment_chi,container,false);
//        rcv=view.findViewById(R.id.rcvTheLoai);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        rcv.setLayoutManager(mLayoutManager);
//
//
//        mDatabase = FirebaseDatabase.getInstance().getReference("TheLoai");
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get LoaiSach object and use the values to update the UI
//                list.clear();
//
//                for (DataSnapshot data:dataSnapshot.getChildren()){
//                    TheLoai item = data.getValue(TheLoai.class);
//
//                    list.add(item);
//                }
//
//                capnhatLV();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//
//            }
//        };
//        mDatabase.addValueEventListener(listener);
//
//
//
//
//
//        Inserttheloai=view.findViewById(R.id.InsertTheLoai);
//        Inserttheloai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog=new Dialog(getContext());
//                dialog.setContentView(R.layout.activity_the_loai);
//                dialog.setCancelable(false);
//                dialog.show();
//                Button btnHuy=dialog.findViewById(R.id.btnCancelTheLoai);
//
//                btnHuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//            }
//        });
//
//        return view;
//    }
//
//    public void capnhatLV(){
//
//        theloaiadapterr.notifyItemInserted(list.size());
//
//        theloaiadapterr.notifyDataSetChanged();
//
//
//    }
//
//}
package com.example.duanmau_ps12545_lycaothanggd.Fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ps12545_lycaothanggd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.theloaiAdapter;
import dao.TheLoaiDAO;
import model.TheLoai;


public class Fragment_chi extends Fragment {
    FloatingActionButton InsertTheLoai;
    RecyclerView rcv;
    theloaiAdapter adapter;
    ArrayList<TheLoai> list;
    TheLoaiDAO dao;
    TheLoai theLoai;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ImageView setLichone,setLichtwo;
    DatePickerDialog datePickerDialog;
    Calendar lich=Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chi,container,false);
        rcv = view.findViewById(R.id.rcvTheLoai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list=TheLoaiDAO.readAll(getContext());
        adapter = new theloaiAdapter(getActivity(), list);
        rcv.setAdapter(adapter);



        InsertTheLoai=view.findViewById(R.id.InsertTheLoai);
        InsertTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext()," Thanh Cong!!!",
//                        Toast.LENGTH_LONG).show();
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.activity_the_loai);
                final EditText tilMatl=dialog.findViewById(R.id.edMaTheLoai);
                final EditText tilTentl=dialog.findViewById(R.id.edTenTheLoai);
                final EditText tilMota=dialog.findViewById(R.id.edMoTa);


                Button btnThem=dialog.findViewById(R.id.btnAddTheLoai);
                Button btnHuy=dialog.findViewById(R.id.btnCancelTheLoai);
//                 final ImageView setNgay=dialog.findViewById(R.id.ggg);
//                final Button setNgay=dialog.findViewById(R.id.ggg);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            String matl=tilMatl.getText().toString();
                            String tentl=tilTentl.getText().toString();
                            String mota=tilMota.getText().toString();
//                        int idLoai = (int) spn.getSelectedItem();

//                            Log.d("asv",String.valueOf(idLoai));
                            theLoai = new TheLoai(matl,tentl,mota);
                            TheLoaiDAO theLoaiDAO = new TheLoaiDAO();
                            if(theLoaiDAO.create(getContext(),theLoai)){
                                Toast.makeText(getContext(),
                                        "Thêm thành công!!!!!!", Toast.LENGTH_LONG).show();
                                list.clear();

                                list.addAll(TheLoaiDAO.readAll(getContext()));
//                                Toast.makeText(getContext(),
//                                        "Thất bại!!!!!!"+list.get(1).getTien(), Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(),
                                        "Thất bại!!!!!!", Toast.LENGTH_LONG).show();
                            }
                    }
                });
                dialog.setCancelable(false);
                dialog.show();

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });



        return view;
    }

}

