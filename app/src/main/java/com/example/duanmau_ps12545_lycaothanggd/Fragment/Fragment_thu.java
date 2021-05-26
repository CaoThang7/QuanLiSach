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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ps12545_lycaothanggd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import adapter.adapterUser;
import dao.NguoiDungDAO;
import dao.QuanTriDAO;
import model.KhachHang;
import model.NguoiDung;
import model.User;

public class Fragment_thu extends Fragment {
    FloatingActionButton btInsert;
//    RecyclerView rcv;
//    nguoidungAdapter adapter;
//    ArrayList<KhachHang> list;
//    NguoiDungDAO dao;
//    KhachHang khachHang;
    RecyclerView rcv;
    ArrayList<User> list;
    adapterUser adapter;
    QuanTriDAO dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DatePickerDialog datePickerDialog;
    Calendar lich=Calendar.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thu,container,false);
//        rcv = view.findViewById(R.id.dsUser);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rcv.setLayoutManager(layoutManager);
//        list = new ArrayList<>();
//        list=NguoiDungDAO.readAll(getContext());
//        adapter = new nguoidungAdapter(getActivity(), list);
//        rcv.setAdapter(adapter);
//
//
//
//        btInsert=view.findViewById(R.id.btnInsert);
//
//        btInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getContext()," Thanh Cong!!!",
////                        Toast.LENGTH_LONG).show();
//                final Dialog dialog=new Dialog(getContext());
//                dialog.setContentView(R.layout.activity_user);
//                final EditText tilTen=dialog.findViewById(R.id.edFullName);
//                final EditText tilGiangvien=dialog.findViewById(R.id.edPhone);
//
//                Button btnThem=dialog.findViewById(R.id.btnAddUser);
//                Button btnHuy=dialog.findViewById(R.id.btnCancelUser);
////                 final ImageView setNgay=dialog.findViewById(R.id.ggg);
////                final Button setNgay=dialog.findViewById(R.id.ggg);
//
//                btnThem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try{
//                            String hoten=tilTen.getText().toString();
//                            String phone=tilGiangvien.getText().toString();
//
////                            Log.d("asv",String.valueOf(idLoai));
//                            khachHang = new KhachHang(hoten,phone);
//                            NguoiDungDAO khoaHocDAO = new NguoiDungDAO();
//                            if(khoaHocDAO.create(getContext(),khachHang)){
//                                Toast.makeText(getContext(),
//                                        "Thêm thành công!!!!!!", Toast.LENGTH_LONG).show();
//                                list.clear();
//
//                                list.addAll(NguoiDungDAO.getAll(getContext()));
////                                Toast.makeText(getContext(),
////                                        "Thất bại!!!!!!"+list.get(1).getTien(), Toast.LENGTH_LONG).show();
//                                adapter.notifyDataSetChanged();
//                                dialog.dismiss();
//                            }else{
//                                Toast.makeText(getContext(),
//                                        "Thất bại!!!!!!", Toast.LENGTH_LONG).show();
//                            }
//                        }catch (Exception ex){
//                            Toast.makeText(getContext(),
//                                    "Lỗi!!!!!!", Toast.LENGTH_LONG).show();
//                            ex.printStackTrace();
//                        }
//
//                    }
//                });
//                dialog.setCancelable(false);
//                dialog.show();
//
//                btnHuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//            }
//        });


        //        rcv = view.findViewById(R.id.dsUser);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rcv.setLayoutManager(layoutManager);
//        list = new ArrayList<>();
//        list=NguoiDungDAO.readAll(getContext());
//        adapter = new nguoidungAdapter(getActivity(), list);
//        rcv.setAdapter(adapter);

        //recycleView
        rcv=view.findViewById(R.id.srcuser);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list=QuanTriDAO.getAll(getContext());
        adapter=new adapterUser(getContext(),list);
        rcv.setAdapter(adapter);

        btInsert=view.findViewById(R.id.btnInsert);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                final EditText etname,etngay,etphone,etemail,etusername,etpassword;
                TextView title;
                Button btthem,bthuy,date;
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_user);
                title=dialog.findViewById(R.id.titleuser);
                bthuy=dialog.findViewById(R.id.btuserhuy);
                etname=dialog.findViewById(R.id.etnameuser);
                etngay=dialog.findViewById(R.id.etdateuser);
                etphone=dialog.findViewById(R.id.etphoneuser);
                etemail=dialog.findViewById(R.id.etemailuser);
                etusername=dialog.findViewById(R.id.etadminuser);
                etpassword=dialog.findViewById(R.id.etpwduser);
                btthem=dialog.findViewById(R.id.btuser);
                date=dialog.findViewById(R.id.date);
                title.setText("Thêm User");
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                                etngay.setText(year + "-" +(monthOfYear+1) + "-" +dayOfMonth);





                            }
                        },
                                lich.get(Calendar.YEAR),
                                lich.get(Calendar.MONTH),
                                lich.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }

                });
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            String ten=etname.getText().toString();
                            Date ngay=sdf.parse(etngay.getText().toString());
                            int phone =Integer.parseInt(etphone.getText().toString());
                            String email = etemail.getText().toString();
                            String username = etusername.getText().toString();
                            String pass = etpassword.getText().toString();
                            if(QuanTriDAO.create(getContext(),new User(ten,ngay,phone,email,username,pass))){
                                Toast.makeText(getContext(),"Thêm thành công!!!",Toast.LENGTH_LONG).show();
                                list.clear();
                                list.addAll(QuanTriDAO.getAll(getContext()));
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.setCancelable(false);
                dialog.show();

            }
        });







        return view;
    }
}
