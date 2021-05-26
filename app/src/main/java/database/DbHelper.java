package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){super(context,"androidnangcao",null,1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE THELOAI(matl text primary key,tentl text,mota text)";
        db.execSQL(sql);
        sql="INSERT INTO THELOAI VALUES('1.IT','Công Nghệ Thông Tin','Nice')";
        db.execSQL(sql);
        sql="INSERT INTO THELOAI VALUES('2.Kinh Tế','QL.Tài Chính','Nice')";
        db.execSQL(sql);

        //Book
        sql ="CREATE TABLE BOOK(masach text primary key,"+
                "tieude text,gia text,assignmentduanmau text,"+
                "MaLoai_FK text references THELOAI(matl))";
        db.execSQL(sql);
        sql="INSERT INTO BOOK(masach,tieude,gia,assignmentduanmau,MaLoai_FK) VALUES('001','JAVA','100.000 VNĐ','abcdef','1.IT')";
        db.execSQL(sql);
        sql="INSERT INTO BOOK(masach,tieude,gia,assignmentduanmau,MaLoai_FK) VALUES('002','Kinh Doanh','200.000 VNĐ','abcdef','2.Kinh Tế')";
        db.execSQL(sql);


        //Hoa Don
        sql="CREATE TABLE HOADON(MaHoaDon text primary key ,Ngayhoadon Date)";
        db.execSQL(sql);
        sql="INSERT INTO HOADON VALUES('E0','2020-9-2')";
        db.execSQL(sql);

        //HoaDonchitiet
        sql="CREATE TABLE HOADONCHITIET(MaHDCT integer primary key autoincrement," +
                "MaHD_Fk text references HOADON(MaHoaDon)," +
                "MaSach_FK integer references BOOK(masach)," +
                "giabia double)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaSach_FK) VALUES('E0',1)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaSach_FK) VALUES('E1',2)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaSach_FK) VALUES('E2',3)";
        db.execSQL(sql);







//
//        sql="CREATE TABLE QUANTRI(username text primary key, password text)";
//        db.execSQL(sql);
//        sql="INSERT INTO QUANTRI VALUES('admin','admin')";
//        db.execSQL(sql);
//        sql="INSERT INTO QUANTRI VALUES('admin2','123')";
//        db.execSQL(sql);

        sql="CREATE TABLE QUANTRI(MaTc integer primary key autoincrement,TenKhoanTc text,Ngay Date,phone integer,email text,username text, password text)";
        db.execSQL(sql);
        sql="INSERT INTO QUANTRI(TenKhoanTc,Ngay,phone,email,username,password) VALUES('Nam','2001-01-01',0934135394,'thangps12545','admin','admin')";
        db.execSQL(sql);
        sql="INSERT INTO QUANTRI(TenKhoanTc,Ngay,phone,email,username,password) VALUES('Nu','2002-02-02',01235987,'thangps12545','admin123','1')";
        db.execSQL(sql);


        sql="CREATE TABLE NGUOIDUNG(hoten text primary key, phone text)";
        db.execSQL(sql);
        sql="INSERT INTO NGUOIDUNG(hoten,phone) VALUES('Lý Cao Thắng','0934135304')";
        db.execSQL(sql);
        sql="INSERT INTO NGUOIDUNG(hoten,phone) VALUES('Trần Ngọc Duy','0932153610')";
        db.execSQL(sql);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists THELOAI");
        db.execSQL("Drop table if exists BOOK");
        db.execSQL("Drop table if exists NGUOIDUNG");
        db.execSQL("drop table if exists HOADON");
        db.execSQL("drop table if exists HOADONCHITIET");
        onCreate(db);

    }
}
