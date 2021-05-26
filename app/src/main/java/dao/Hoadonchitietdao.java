package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.DbHelper;
import model.Hoadon;
import model.Hoadonchitiet;
import model.Sach;

public class Hoadonchitietdao {
    public static ArrayList<Hoadonchitiet> reaAll(Context context){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        ArrayList<Hoadonchitiet> data=new ArrayList<>();
        DbHelper dBhelper=new DbHelper (context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT MaHDCT,HOADON.MaHoaDon,HOADON.Ngayhoadon," +
                "BOOK.masach,BOOK.tieude,BOOK.gia,BOOK.MaLoai_FK " +
                "FROM HOADONCHITIET INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach " +
                "INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            try {
                 Hoadonchitiet ee=new Hoadonchitiet();
                ee.setMahoadonchitiet(cs.getInt(0));
                ee.setHoadon(new Hoadon(cs.getString(1),sdf.parse(cs.getString(2))));
                ee.setSach(new Sach(cs.getString(0),cs.getString(1),cs.getString(3),cs.getString(4)));
                data.add(ee);
                Log.d("//=====", ee.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public static ArrayList<Hoadonchitiet> reaAll(Context context,String mahoadon){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        ArrayList<Hoadonchitiet> data=new ArrayList<>();
        DbHelper  dBhelper=new DbHelper (context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT MaHDCT,HOADON.MaHoaDon,HOADON.Ngayhoadon," +
                "BOOK.masach,BOOK.tieude,BOOK.gia,BOOK.MaLoai_FK " +
                "FROM HOADONCHITIET INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach " +
                "INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon WHERE HOADONCHITIET.MaHD_Fk='"+mahoadon+"' ", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            try {
                Hoadonchitiet ee=new Hoadonchitiet();
                ee.setMahoadonchitiet(cs.getInt(0));
                ee.setHoadon(new Hoadon(cs.getString(1),sdf.parse(cs.getString(2))));
                ee.setSach(new Sach(cs.getString(0),cs.getString(1),cs.getString(3),cs.getString(4)));
                data.add(ee);
                Log.d("//=====", ee.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public static boolean create(Context context, Hoadonchitiet hoadon){
        DbHelper  dBhelper=new DbHelper (context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("MaHD_Fk",hoadon.getHoadon().getMaSach());
        values.put("MaSach_FK",hoadon.getSach().getMasach());
        long row=db.insert("HOADONCHITIET",null,values);
        if (row > 0) {
            Log.e("TAG", "create: thang cong" );
            return true;
        } else {
            return false;
        }
    }
    public static int thanhtoan(Context context,String mahoadon){
        int tongTien=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String str= " select SUM(gia) as TongTien " +
                "from HOADONCHITIET INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach" +
                "  INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon " +
                "WHERE HOADONCHITIET.MaHD_Fk='"+mahoadon+"'";
        Cursor cs = db.rawQuery(str, null);
        cs.moveToFirst();
        if(cs.getCount()>=0){
            tongTien=cs.getInt(0);
        }
        return tongTien;
    }
    public static double getDoanhThuTheoNgay(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }

    public static double getDoanhThuTheoNgay1(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now','-1 day')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay2(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now','-2 day')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay3(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now','-3 day')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay4(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now','-4 day')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay5(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE HOADON.Ngayhoadon=date('now','-5 day')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoThang(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE strftime('%m',HOADON.Ngayhoadon)=strftime('%m','now')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNam(Context context){
        double doanhThu=0;
        DbHelper  dbhelper=new DbHelper (context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(BOOK.gia) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN BOOK on HOADONCHITIET.MaSach_FK=BOOK.masach WHERE strftime('%Y',HOADON.Ngayhoadon)=strftime('%Y','now')" +
                "GROUP By HOADONCHITIET.MaSach_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }

}
