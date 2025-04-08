package DTO;

public class HD {
    private String maHD;
    private String maNV;
    private String maKH;
    private String ngayLap;
    private String phuongThuc;
    private String tongGG;
    private String tongTien;
    private String tongSL;

    public HD(){}

    public HD(String maHD, String maNV, String maKH, String ngayLap, String phuongThuc, String tongGG, String tongTien, String tongSL) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.phuongThuc = phuongThuc;
        this.tongGG = tongGG;
        this.tongTien = tongTien;
        this.tongSL = tongSL;
    }
    // Getters and setters
    public String getMaHD() {
        return maHD;
    }
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }
    public String getMaNV() {
        return maNV;
    }
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public String getMaKH() {
        return maKH;
    }
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    public String getNgayLap() {
        return ngayLap;
    }
    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
    public String getPhuongThuc() {
        return phuongThuc;
    }
    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }
    public String getTongGG() {
        return tongGG;
    }
    public void setTongGG(String tongGG) {
        this.tongGG = tongGG;
    }
    public String getTongTien() {
        return tongTien;
    }
    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
    public String getTongSL() {
        return tongSL;
    }
    public void setTongSL(String tongSL) {
        this.tongSL = tongSL;
    }
    // toString
    @Override
    public String toString() {
        return maHD + "," + maNV + "," + maKH + "," + ngayLap + "," + phuongThuc + "," + tongGG + "," + tongTien + "," + tongSL;
    }
}
