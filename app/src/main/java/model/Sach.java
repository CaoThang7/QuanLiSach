package model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Sach {
   private String masach;
   private String tieude;
   private String gia;
   private String assignmentduanmau;
   private String MaLoai;



    @NonNull
    @Override
    public String toString() {
        return String.valueOf(getMaLoai());
    }


    public Sach(String masach, String tieude, String gia, String maLoai) {
        this.masach = masach;
        this.tieude = tieude;
        this.gia = gia;
        MaLoai = maLoai;
    }

    public Sach(String masach, String tieude, String gia, String assignmentduanmau, String maLoai) {
        this.masach = masach;
        this.tieude = tieude;
        this.gia = gia;
        this.assignmentduanmau = assignmentduanmau;
        MaLoai = maLoai;
    }

    public Sach() {
    }

//    public Sach() {
//    }
//
//    public String getMasach() {
//        return masach;
//    }
//
//    public void setMasach(String masach) {
//        this.masach = masach;
//    }
//
//    public String getTieude() {
//        return tieude;
//    }
//
//    public void setTieude(String tieude) {
//        this.tieude = tieude;
//    }
//
//    public String getGia() {
//        return gia;
//    }
//
//    public void setGia(String gia) {
//        this.gia = gia;
//    }
//
//    public String getMaLoai() {
//        return MaLoai;
//    }
//
//    public void setMaLoai(String maLoai) {
//        MaLoai = maLoai;
//    }


    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getAssignmentduanmau() {
        return assignmentduanmau;
    }

    public void setAssignmentduanmau(String assignmentduanmau) {
        this.assignmentduanmau = assignmentduanmau;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
}
