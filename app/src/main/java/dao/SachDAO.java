package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.DbHelper;
import model.Sach;


public class SachDAO {

//     DbHelper helper;
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//
//
//     public KhoanThuChiDAO(Context context){
//         helper=new DbHelper(context);
//     }


    public static ArrayList<Sach> readAll(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        ArrayList<Sach> data = new ArrayList<>();


        //Tao databse
        SQLiteDatabase db = helper.getWritableDatabase();
        // Tao con tro = pointer( cursor) de lay du lieu

        String sql = "Select * from BOOK JOIN THELOAI on MaLoai_FK = matl ";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {

            String masach = cs.getString(0);
            String tieude = cs.getString(1);
            String gia = cs.getString(2);
            String assignmentduanmau=cs.getString(3);
            String MaLoai_FK = cs.getString(4);


                data.add(new Sach(masach,tieude,gia,assignmentduanmau,MaLoai_FK));
            cs.moveToNext();

        }
        cs.close();
        return data;
    }


    //delete
    public static boolean delete(Context context,String masach){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        int row=db.delete("BOOK","masach=?",new String[]{String.valueOf(masach)});
        return row > 0;
    }


    public static ArrayList<Sach>getAll(Context context){

        ArrayList<Sach> ds=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        String str = "select * from BOOK JOIN THELOAI " +
                "on MaLoai = MaLoai_FK ";
        Cursor cs=db.rawQuery(str,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){

            String masach = cs.getString(0);
            String tieude = cs.getString(1);
            String gia = cs.getString(2);
            String assignmentduanmau=cs.getString(3);
            String MaLoai_FK = cs.getString(4);


            ds.add(new Sach(masach,tieude,gia,assignmentduanmau,MaLoai_FK));

            cs.moveToNext();

        }
        cs.close();
        return ds ;
    }

    public  static Sach getSachbyID(Context context,String Masach){
        Sach s=null;
        DbHelper dbhelper =new DbHelper(context);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("select * from BOOK" +
                " WHERE masach ='" + Masach + "'",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            s=new Sach();
            s.setMasach(cs.getString(0));
            s.setTieude(cs.getString(1));
            s.setGia(cs.getString(2));
            s.setAssignmentduanmau(cs.getString(3));
            s.setMaLoai(cs.getString(4));
            break;
        }
        cs.close();
        return s;
    }




    public Boolean create(Context context,Sach sach){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getReadableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("masach",sach.getMasach());
        values.put("tieude",sach.getTieude());
        values.put("gia",sach.getGia());
        values.put("assignmentduanmau",sach.getAssignmentduanmau());
        values.put("MaLoai_FK",sach.getMaLoai());
        db.insert("BOOK",null,values);
        return true;
    }

//Update
    public Boolean update(Context context, Sach sach){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("masach",sach.getMasach());
        values.put("tieude",sach.getTieude());
        values.put("gia",sach.getGia());
        values.put("assignmentduanmau",sach.getAssignmentduanmau());
        values.put("MaLoai_FK",sach.getMaLoai());
        db.update("BOOK",values,"masach=?",new String[]{String.valueOf(sach.getMasach())});
        return true;
    }


}
