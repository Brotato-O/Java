package DTO;

public class GG {
    private String maGG;
    private String tenGG;
    private float luongGiam;
    private String ngayBD;
    private String ngayKT;

    // Constructor
    public GG() {

    }
    public GG(String maGG, String tenGG, float luongGiam, String ngayBD, String ngayKT) {
        this.maGG = maGG;
        this.tenGG = tenGG;
        this.luongGiam = luongGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    // Getters
    public String getMaGG() {
        return maGG;
    }

    public String getTenGG() {
        return tenGG;
    }

    public float getLuongGiam() {
        return luongGiam;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    // Setters
    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public void setTenGG(String tenGG) {
        this.tenGG = tenGG;
    }

    public void setLuongGiam(float luongGiam) {
        this.luongGiam = luongGiam;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }
}

