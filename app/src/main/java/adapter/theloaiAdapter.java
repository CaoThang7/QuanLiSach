package adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau_ps12545_lycaothanggd.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import dao.TheLoaiDAO;
import model.TheLoai;


public class theloaiAdapter extends RecyclerView.Adapter<theloaiAdapter.KhoanTCHolder> {
    Activity context;
    ArrayList<TheLoai> list;
    TheLoaiDAO dao;
    RecyclerView rcv;
    theloaiAdapter adapter;
    TheLoai theLoai;
    EditText etmatl,ettentl,etmota;
//    Spinner spnn;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public theloaiAdapter(Activity context, ArrayList<TheLoai>list){
        this.context=context;
        this.list=list;

    }



    @NonNull
    @Override
    public KhoanTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.theloai_item,parent,false);

        return (new KhoanTCHolder(view));

    }

    @Override
    public void onBindViewHolder(@NonNull final KhoanTCHolder holder, final int position) {
        holder.tvmatl.setText(list.get(position).getMatl());
        holder.tvtentl.setText(list.get(position).getTentl());
        holder.tvmota.setText(list.get(position).getMota());




        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(position);
            }
        });





        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String matl =list.get(position).getMatl();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắn chắn xoá không?");
                builder.setTitle("Xoá");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(TheLoaiDAO.delete(context,matl)){
                            Toast.makeText(context," thành công!!!",
                                    Toast.LENGTH_LONG).show();
                            list.remove(position);
                            notifyDataSetChanged();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(context," thất bại!!!",
                            Toast.LENGTH_LONG).show();

                    }
                });

                AlertDialog dialog=builder.create();
                dialog.show();




//                if(KhoanThuChiDAO.delete(context,MaTc)){
//                    Toast.makeText(context," thành công!!!",
//                            Toast.LENGTH_LONG).show();
//                    list.remove(position);
//                    notifyDataSetChanged();
//
//                }else {
//                    Toast.makeText(context," thất bại!!!",
//                            Toast.LENGTH_LONG).show();
//                }

            }


        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }



    public class KhoanTCHolder extends RecyclerView.ViewHolder{
        TextView tvmatl,tvtentl,tvmota;
        ImageView ivEdit,ivDel;
//        FloatingActionButton btInsert;
        CardView card;
//         RecyclerView card;

        public KhoanTCHolder(@NonNull View item) {
            super(item);
            tvmatl=item.findViewById(R.id.matl);
            tvtentl=item.findViewById(R.id.tentl);
            tvmota=item.findViewById(R.id.mota);

            ivEdit=item.findViewById(R.id.btnEdit);
            ivDel=item.findViewById(R.id.btnDel);
//            btInsert=item.findViewById(R.id.fbInsertLop);
            card=item.findViewById(R.id.aaa);
        }
    }

    public void dialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = context.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.edit_theloai, null);
        etmatl = v.findViewById(R.id.etmatl);
        ettentl= v.findViewById(R.id.ettentll);
        etmota= v.findViewById(R.id.etmota);

//        spnn=v.findViewById(R.id.spn);

        etmatl.setText(list.get(position).getMatl());
        ettentl.setText(list.get(position).getTentl());
        etmota.setText(list.get(position).getMota());



//        final ArrayAdapter adapterLop = new ArrayAdapter(context,
//                android.R.layout.simple_spinner_dropdown_item,list);
//        spnn.setAdapter(adapterLop);
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...

                            String matl=list.get(position).getMatl();
                            String tentl = ettentl.getText().toString();
                            String mota = etmota.getText().toString();

//
//                            KhoaHoc tc=(KhoanThuChi) spnn.getSelectedItem();
//                            int maloai=tc.getMaLoai();
//                            spnn.setAdapter(adapterLop);
                            theLoai = new TheLoai(matl,tentl,mota);
                            TheLoaiDAO theLoaiDAO = new TheLoaiDAO();
                            if(theLoaiDAO.update(context,theLoai)){
                                theLoai= new TheLoai(matl,tentl,mota);
                                list.set(position,theLoai);
                                notifyDataSetChanged();
                                Toast.makeText(context,"Thành công!!!",Toast.LENGTH_SHORT).show();
                            }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();



    }

    }

