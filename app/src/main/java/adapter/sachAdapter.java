package adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau_ps12545_lycaothanggd.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;


import dao.SachDAO;
import model.Sach;

public class sachAdapter extends RecyclerView.Adapter<sachAdapter.KhoanTCHolder> implements Filterable {
    Activity context;
    ArrayList<Sach> list;
    ArrayList<Sach> list2;
    SachDAO dao;
    RecyclerView rcv;
    sachAdapter adapter;
    Sach sach;
    EditText edmasach,edtieude,edgia,edass;
    Spinner spn;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public sachAdapter(Activity context, ArrayList<Sach>list){
        this.context=context;
        this.list=list;
        list2=new ArrayList<>(list);


    }



    @NonNull
    @Override
    public KhoanTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sach_item,parent,false);

        return (new KhoanTCHolder(view));

    }

    @Override
    public void onBindViewHolder(@NonNull final KhoanTCHolder holder, final int position) {
        holder.tvmasach.setText(list.get(position).getMasach());
        holder.tvtieude.setText(list.get(position).getTieude());
        holder.tvgia.setText(list.get(position).getGia());
        holder.tvass.setText(list.get(position).getAssignmentduanmau());
        holder.tvmatl_FK.setText(list.get(position).getMaLoai());



//
//        holder.btInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context," thành công!!!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(position);
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String masach =list.get(position).getMasach();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắn chắn xoá không?");
                builder.setTitle("Xoá");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(SachDAO.delete(context,masach)){
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


            }


        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }


    //Search sách
    @Override
    public Filter getFilter() {
        return listFilter;
    }
    private Filter listFilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Sach> fillteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                fillteredList.addAll(list2);
            }else {
                String fillterPattern=constraint.toString().toLowerCase().trim();
                for(Sach sach: list2){
                    if(sach.getTieude().toLowerCase().contains(fillterPattern)){
                        fillteredList.add(sach);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=fillteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };





    public class KhoanTCHolder extends RecyclerView.ViewHolder{
        TextView tvmasach,tvtieude,tvgia,tvmatl_FK,tvass;
        ImageView ivEdit,ivDel;
//        FloatingActionButton btInsert;
        CardView card;
//         RecyclerView card;

        public KhoanTCHolder(@NonNull View item) {
            super(item);
            tvmasach=item.findViewById(R.id.masach);
            tvtieude=item.findViewById(R.id.tvTieuDee);
            tvgia=item.findViewById(R.id.gia);
            tvass=item.findViewById(R.id.ass);
            tvmatl_FK=item.findViewById(R.id.matl_FK);
            ivEdit=item.findViewById(R.id.btnEdit);
            ivDel=item.findViewById(R.id.btnDel);
//            btInsert=item.findViewById(R.id.fbInsertLop);
            card=item.findViewById(R.id.thang);
        }
    }

    public void dialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = context.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.edit_sach, null);
        edmasach = v.findViewById(R.id.tvmasach);
        edtieude = v.findViewById(R.id.tvTieuDe);


        edgia = v.findViewById(R.id.tvgia);
        edass=v.findViewById(R.id.assignment);
        spn=v.findViewById(R.id.spn);


        edmasach.setText(list.get(position).getMasach());
        edtieude.setText(list.get(position).getTieude());
        edgia.setText(list.get(position).getGia());
        edass.setText(list.get(position).getAssignmentduanmau());



        final ArrayAdapter adapterSach = new ArrayAdapter(context,
                android.R.layout.simple_spinner_dropdown_item,list);
        spn.setAdapter(adapterSach);
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        try {
                            String masach = list.get(position).getMasach();
                            String tieude = edtieude.getText().toString();
                            String gia = edgia.getText().toString();
                            String ass=edass.getText().toString();
                            Sach tc=(Sach) spn.getSelectedItem();
                            String maloai=tc.getMaLoai();
                            spn.setAdapter(adapterSach);
                            sach = new Sach(masach,tieude,gia,ass,maloai);
                            SachDAO sachDAO = new SachDAO();
                            if(sachDAO.update(context,sach)){
                                sach = new Sach(masach,tieude,gia,ass,maloai);
                                list.set(position,sach);
                                notifyDataSetChanged();
                                Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(context,"Lỗi ngày",Toast.LENGTH_SHORT).show();
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
