package model;

public class ThuChi {
    private int MaLoai;
    private String TenLoai;
    private String TrangThai;

    public ThuChi() {
    }

    public ThuChi(int maLoai, String tenLoai, String trangThai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        TrangThai = trangThai;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
