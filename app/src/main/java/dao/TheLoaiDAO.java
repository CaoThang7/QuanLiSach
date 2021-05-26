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
import model.TheLoai;


public class TheLoaiDAO {


    public static ArrayList<TheLoai> readAll(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        ArrayList<TheLoai> data = new ArrayList<>();


        //Tao databse
        SQLiteDatabase db = helper.getWritableDatabase();
        // Tao con tro = pointer( cursor) de lay du lieu

        Cursor cs=db.rawQuery("select * from THELOAI",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String matl=cs.getString(0);
            String tentl=cs.getString(1);
            String mota=cs.getString(2);


            data.add(new TheLoai(matl,tentl,mota));

            cs.moveToNext();

        }
        cs.close();
        return data;
    }
    public static ArrayList<TheLoai>getAll(Context context){

        ArrayList<TheLoai> ds=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cs=db.rawQuery("select * from THELOAI",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){


            String matl=cs.getString(0);
            String tentl=cs.getString(1);
            String mota=cs.getString(2);


            ds.add(new TheLoai(matl,tentl,mota));

            cs.moveToNext();

        }
        cs.close();
        return ds;
    }


    //delete
    public static boolean delete(Context context,String matheloai){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        int row=db.delete("THELOAI","matl=?",new String[]{String.valueOf(matheloai)});
        return row > 0;
    }

//
    public Boolean create(Context context, TheLoai theLoai){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getReadableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("matl",theLoai.getMatl());
        values.put("tentl",theLoai.getTentl());
        values.put("mota",theLoai.getMota());
        db.insert("THELOAI",null,values);
        return true;
    }


//Update
    public Boolean update(Context context,TheLoai theLoai){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
//        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("matl",theLoai.getMatl());
        values.put("tentl",theLoai.getTentl());
        values.put("mota",theLoai.getMota());
        db.update("THELOAI",values,"matl=?",new String[]{String.valueOf(theLoai.getMatl())});
        return true;
    }


}
