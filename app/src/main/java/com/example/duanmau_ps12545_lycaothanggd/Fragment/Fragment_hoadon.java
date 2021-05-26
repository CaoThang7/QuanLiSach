package com.example.duanmau_ps12545_lycaothanggd.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ps12545_lycaothanggd.R;
import com.example.duanmau_ps12545_lycaothanggd.hoadonchitiet_activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.adapterUser;
import adapter.adaterHoaDon;
import dao.HoaDondao;
import dao.QuanTriDAO;
import model.Hoadon;

public class Fragment_hoadon extends Fragment {
    FloatingActionButton Insert;
    RecyclerView recyclerView;
    Toolbar toolbar;
    adaterHoaDon adapter;
    ArrayList<Hoadon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hoadon,container,false);
        Insert=view.findViewById(R.id.btnInsert);
        recyclerView=view.findViewById(R.id.rcvhoadon);
//        toolbar=view.findViewById(R.id.toolbar_hoadon);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Hoá đơn");
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list= HoaDondao.readAll(getContext());
        adapter=new adaterHoaDon(getContext(),list);
        recyclerView.setAdapter(adapter);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });





        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.option_menu, menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.menu_hoadn){
//            dialog();
//            Toast.makeText(getContext(), "Them", Toast.LENGTH_SHORT).show();
//        }
//        return super.onContextItemSelected(item);
//    }

    private void dialog() {

        final Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.hoadon_dialog);
        final EditText etma,etdate;
        Button chondate,them,cancel;
        them=dialog.findViewById(R.id.btthemhoadon);
        cancel=dialog.findViewById(R.id.btnCancel);
        etma=dialog.findViewById(R.id.etmahoadon);
        etdate=dialog.findViewById(R.id.etngyahoadon);
        chondate=dialog.findViewById(R.id.cavender);
        chondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar=Calendar.getInstance();
                int nhay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang=calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePicker =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayOfMonth);

                        etdate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,nhay);
                datePicker.show();

            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = etma.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String ngay1=(etdate.getText().toString());

//                Date ngay = null;
//                try {
//                    ngay = sdf.parse(etdate.getText().toString());
//                } catch (ParseException e) {
//                    Toast.makeText(Hoadon_activity.this, "không đúng định dạng yyyy-MM-dd", Toast.LENGTH_SHORT).show();
//                }

                Log.e("kobk", "onClick: "+etdate.getText().toString());
                try {
                    if (HoaDondao.create(getContext(),new Hoadon(ma,sdf.parse(etdate.getText().toString()))))
                    {
                        Toast.makeText(getContext(), " Thanh Cong ", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(), hoadonchitiet_activity.class);
                        intent.putExtra("mahoadon",etma.getText().toString());
                        startActivity(intent);
                        list.clear();
                        list.addAll(HoaDondao.readAll(getContext()));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



}
