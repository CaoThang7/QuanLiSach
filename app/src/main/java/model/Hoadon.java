package model;

import java.util.Date;

public class Hoadon {
    String maSach;
    Date ngaysach;

    public Hoadon() {
    }

    public Hoadon(String maSach, Date ngaysach) {
        this.maSach = maSach;
        this.ngaysach = ngaysach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgaysach() {
        return ngaysach;
    }

    public void setNgaysach(Date ngaysach) {
        this.ngaysach = ngaysach;
    }
}
