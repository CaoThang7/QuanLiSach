package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.DbHelper;
import model.Hoadon;

public class HoaDondao {
    public static ArrayList<Hoadon> readAll(Context context){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Hoadon> list=new ArrayList<>();
        DbHelper dBhelper=new DbHelper (context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM HOADON", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String mahd=cs.getString(0);
            String ngayhoadon=cs.getString(1);
            try {
                list.add(new Hoadon(mahd,simpleDateFormat.parse(ngayhoadon)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    public static boolean create(Context context, Hoadon hoadon){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DbHelper  dBhelper=new DbHelper (context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("MaHoaDon",hoadon.getMaSach());
        values.put("Ngayhoadon",simpleDateFormat.format(hoadon.getNgaysach()));
        long row=db.insert("HOADON",null,values);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean delete(Context context,String mahd){
        DbHelper  dbhelper =new DbHelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int row = db.delete("HOADON", "MaHoaDon=?",new String[]{String.valueOf(mahd)});
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
    public static boolean update(Context context,Hoadon hoadon) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DbHelper  dbhelper = new DbHelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaHoaDon",hoadon.getMaSach());
        values.put("Ngayhoadon",simpleDateFormat.format(hoadon.getNgaysach()));

        int row = db.update("HOADON", values, "MaHoaDon=?", new String[]{String.valueOf(hoadon.getMaSach())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
}
