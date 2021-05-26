package model;

import java.util.Date;

public class KhachHang {

   private String hoten;
   private String phone;

    public KhachHang(String hoten, String phone) {
        this.hoten = hoten;
        this.phone = phone;
    }

    public KhachHang() {
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


