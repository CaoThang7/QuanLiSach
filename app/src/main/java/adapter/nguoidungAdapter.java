//package adapter;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.duanmau_ps12545_lycaothanggd.R;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//
//import dao.NguoiDungDAO;
//import model.KhachHang;
//
//
//
//public class nguoidungAdapter extends RecyclerView.Adapter<nguoidungAdapter.KhoanTCHolder> {
//    Activity context;
//    ArrayList<KhachHang> list;
//    NguoiDungDAO dao;
//    RecyclerView rcv;
//    nguoidungAdapter adapter;
//    KhachHang khachHang;
//    EditText ethoten,etphone;
////    Spinner spnn;
//
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//    public nguoidungAdapter(Activity context, ArrayList<KhachHang>list){
//        this.context=context;
//        this.list=list;
//
//    }
//
//
//
//    @NonNull
//    @Override
//    public KhoanTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater=context.getLayoutInflater();
//        View view=inflater.inflate(R.layout.user_item,parent,false);
//
//        return (new KhoanTCHolder(view));
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final KhoanTCHolder holder, final int position) {
//        holder.tvhoten.setText(list.get(position).getHoten());
//        holder.tvphone.setText(list.get(position).getPhone());
//
//
//
//
//        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog(position);
//            }
//        });
//
//
//
//
//
//        holder.ivDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                final String hoten =list.get(position).getHoten();
//
//                AlertDialog.Builder builder=new AlertDialog.Builder(context);
//                builder.setMessage("Bạn có chắn chắn xoá không?");
//                builder.setTitle("Xoá");
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        if(NguoiDungDAO.delete(context,hoten)){
//                            Toast.makeText(context," thành công!!!",
//                                    Toast.LENGTH_LONG).show();
//                            list.remove(position);
//                            notifyDataSetChanged();
//                        }
//
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                            Toast.makeText(context," thất bại!!!",
//                            Toast.LENGTH_LONG).show();
//
//                    }
//                });
//
//                AlertDialog dialog=builder.create();
//                dialog.show();
//
//
//
//
////                if(KhoanThuChiDAO.delete(context,MaTc)){
////                    Toast.makeText(context," thành công!!!",
////                            Toast.LENGTH_LONG).show();
////                    list.remove(position);
////                    notifyDataSetChanged();
////
////                }else {
////                    Toast.makeText(context," thất bại!!!",
////                            Toast.LENGTH_LONG).show();
////                }
//
//            }
//
//
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//
//    }
//
//
//
//    public class KhoanTCHolder extends RecyclerView.ViewHolder{
//        TextView tvhoten,tvphone;
//        ImageView ivEdit,ivDel;
////        FloatingActionButton btInsert;
//        CardView card;
////         RecyclerView card;
//
//        public KhoanTCHolder(@NonNull View item) {
//            super(item);
//            tvhoten=item.findViewById(R.id.name);
//            tvphone=item.findViewById(R.id.phone);
//            ivEdit=item.findViewById(R.id.btnEdit);
//            ivDel=item.findViewById(R.id.btnDel);
////            btInsert=item.findViewById(R.id.fbInsertLop);
//            card=item.findViewById(R.id.ggg);
//        }
//    }
//
//    public void dialog(final int position){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        // Get the layout inflater
//        LayoutInflater inflater = context.getLayoutInflater();
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        View v = inflater.inflate(R.layout.edit_nguoidung, null);
//        ethoten = v.findViewById(R.id.edhotenn);
//        etphone = v.findViewById(R.id.edPhonee);
//
////        spnn=v.findViewById(R.id.spn);
//
//        ethoten.setText(list.get(position).getHoten());
//        etphone.setText(list.get(position).getPhone());
//
//
//
//
////        final ArrayAdapter adapterLop = new ArrayAdapter(context,
////                android.R.layout.simple_spinner_dropdown_item,list);
////        spnn.setAdapter(adapterLop);
//        builder.setView(v)
//                // Add action buttons
//                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // sign in the user ...
//
//                            String hoten = ethoten.getText().toString();
//                            String phone = etphone.getText().toString();
//                            khachHang = new KhachHang(hoten,phone);
//                            NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
//                            if(nguoiDungDAO.update(context,khachHang)){
//                                khachHang= new KhachHang(hoten,phone);
//                                list.set(position,khachHang);
//                                notifyDataSetChanged();
//                                Toast.makeText(context,"Thành công!!!",Toast.LENGTH_SHORT).show();
//                            }
//
//
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                }).show();
//
//
//    }
//
//    }
//
