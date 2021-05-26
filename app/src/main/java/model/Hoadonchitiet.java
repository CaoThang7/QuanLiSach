package model;

public class Hoadonchitiet {
    int mahoadonchitiet;
    Hoadon hoadon;
    Sach sach;


    public Hoadonchitiet() {
    }

    public Hoadonchitiet(int mahoadonchitiet, Hoadon hoadon, Sach sach) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.hoadon = hoadon;
        this.sach = sach;
    }

    public Hoadonchitiet(Hoadon hoadon, Sach sach) {
        this.hoadon = hoadon;
        this.sach = sach;
    }

    public int getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(int mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public Hoadon getHoadon() {
        return hoadon;
    }

    public void setHoadon(Hoadon hoadon) {
        this.hoadon = hoadon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }
}
