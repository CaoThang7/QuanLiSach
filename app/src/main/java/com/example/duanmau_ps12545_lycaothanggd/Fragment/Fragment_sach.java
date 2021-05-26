package com.example.duanmau_ps12545_lycaothanggd.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ps12545_lycaothanggd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.sachAdapter;
import adapter.theloaiAdapter;
import dao.NguoiDungDAO;
import dao.SachDAO;
import dao.TheLoaiDAO;
import model.KhachHang;
import model.Sach;
import model.TheLoai;

public class Fragment_sach extends Fragment {

    FloatingActionButton InsertSach;
    RecyclerView rcv;
    sachAdapter adapter;
    ArrayList<Sach> list;
    SachDAO dao;
    Sach sach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sach,container,false);
        setHasOptionsMenu(true);


        rcv = view.findViewById(R.id.dsSach);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list=SachDAO.readAll(getContext());
        adapter = new sachAdapter(getActivity(), list);
        rcv.setAdapter(adapter);


        InsertSach=view.findViewById(R.id.btnInsertSach);

       InsertSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.activity_them_sach);
                final EditText tilmasach=dialog.findViewById(R.id.edMaSach);
                final EditText tiltensach=dialog.findViewById(R.id.edTenSach);
                final EditText titlgia=dialog.findViewById(R.id.edGiaBia);
                final EditText titlass=dialog.findViewById(R.id.edass);
                final Spinner spn=dialog.findViewById(R.id.spnSach);

                Button btnThem=dialog.findViewById(R.id.btnAddBook);
                Button btnHuy=dialog.findViewById(R.id.btnCancelBook);
                final ArrayAdapter adapterSach = new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,list);
                spn.setAdapter(adapterSach);


                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            String masach=tilmasach.getText().toString();
                            String tensach=tiltensach.getText().toString();
                            String gia=titlgia.getText().toString();
                            String ass=titlass.getText().toString();
                            Sach tc=(Sach) spn.getSelectedItem();
                            String idLoai=tc.getMaLoai();
                            spn.setAdapter(adapterSach);
                            sach = new Sach(masach,tensach,gia,ass,idLoai);
                            SachDAO sachDAO = new SachDAO();
                            if(sachDAO.create(getContext(),sach)){
                                Toast.makeText(getContext(),
                                        "Thêm thành công!!!!!!", Toast.LENGTH_LONG).show();
                                list.clear();

                                list.addAll(SachDAO.readAll(getContext()));
//                                Toast.makeText(getContext(),
//                                        "Thất bại!!!!!!"+list.get(1).getTien(), Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(),
                                        "Thất bại!!!!!!", Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception ex){
                            Toast.makeText(getContext(),
                                    "Lỗi!!!!!!", Toast.LENGTH_LONG).show();
                            ex.printStackTrace();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem seachItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) seachItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);

    }

}
