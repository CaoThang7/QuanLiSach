package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ps12545_lycaothanggd.R;
import java.util.ArrayList;

import model.Hoadonchitiet;

public class adapterHoadonchitiet extends RecyclerView.Adapter<adapterHoadonchitiet.HoaDonCTHolder> {
    Context context;
    ArrayList<Hoadonchitiet> list;

    public adapterHoadonchitiet(Context context, ArrayList<Hoadonchitiet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonCTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadonct_item, parent, false);
        return (new adapterHoadonchitiet.HoaDonCTHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonCTHolder holder, int position) {
         holder.maSach.setText("Mã Sách:"+list.get(position).getSach().getMasach());
         holder.giaSach.setText("Buy Sách:" +list.get(position).getSach().getGia());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HoaDonCTHolder extends RecyclerView.ViewHolder{
        TextView maSach,giaSach;
        ImageView ivdelete;
        public HoaDonCTHolder(@NonNull View itemView) {
            super(itemView);
            maSach =itemView.findViewById(R.id.txtmamusichdct);
            giaSach=itemView.findViewById(R.id.buymusichdct);



        }
    }
}
