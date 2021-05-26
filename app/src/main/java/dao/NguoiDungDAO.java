package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.DbHelper;
import model.KhachHang;


public class NguoiDungDAO {


    public static ArrayList<KhachHang> readAll(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        ArrayList<KhachHang> data = new ArrayList<>();


        //Tao databse
        SQLiteDatabase db = helper.getWritableDatabase();
        // Tao con tro = pointer( cursor) de lay du lieu

        Cursor cs=db.rawQuery("select * from NGUOIDUNG",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String hoten=cs.getString(0);
            String phone=cs.getString(1);

                data.add(new KhachHang(hoten,phone));
            cs.moveToNext();

        }
        cs.close();
        return data;
    }
    public static ArrayList<KhachHang>getAll(Context context){

        ArrayList<KhachHang> ds=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cs=db.rawQuery("select * from NGUOIDUNG",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){

                String hoten = cs.getString(0);
                String phone = cs.getString(1);

                KhachHang khachHang=new KhachHang(hoten,phone);
                ds.add(khachHang);


            cs.moveToNext();

        }
        cs.close();
        return ds;
    }


    //delete
    public static boolean delete(Context context,String hoten){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        int row=db.delete("NGUOIDUNG","hoten=?",new String[]{hoten});
        return row > 0;
    }
//
//
////    public int insert(KhoanThuChi t){
////
////    }
//
//
////    public static boolean create(Context context,KhoanThuChi tc){
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////        DbHelper helper = new DbHelper(context);
////        SQLiteDatabase db=helper.getWritableDatabase();
////        ContentValues values=new ContentValues();
////        values.put("MaTc",tc.getMaTc());
////        values.put("TenKhoanTc",tc.getTenKhoanTc());
////        values.put("Ngay",sdf.format(tc.getNgay()));
////        values.put("Tien",tc.getTien());
////        values.put("GhiChu",tc.getGhiChu());
////        values.put("MaLoai",tc.getMaLoai());
////        long row=db.insert("KHOAN_TC",null,values);
////        return (row >0);
////
////    }
//
    public Boolean create(Context context, KhachHang khachHang){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getReadableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",khachHang.getHoten());
        values.put("phone",khachHang.getPhone());
        db.insert("NGUOIDUNG",null,values);
        return true;
    }


//Update
    public Boolean update(Context context,KhachHang khachHang){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",khachHang.getHoten());
        values.put("phone",khachHang.getPhone());
        db.update("NGUOIDUNG",values,"hoten=?",new String[]{khachHang.getHoten()});
        return true;
    }


}
