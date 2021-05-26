package model;

public class TheLoai {
    private String matl;
    private String tentl;
    private String mota;

    public TheLoai() {
    }

    public TheLoai(String matl, String tentl, String mota) {
        this.matl = matl;
        this.tentl = tentl;
        this.mota = mota;
    }


    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
