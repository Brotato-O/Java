package DTO;

public class HD {
    private String maHD;
    private String maNV;
    private String maKH;
    private String ngayLap;
    private String phuongThuc;
    private float tongGG;
    private float tongTien;
    private int tongSL;
    private float thanhTien;

    public HD(){}

    public HD(String maHD, String maNV, String maKH, String ngayLap, String phuongThuc, float tongGG, float tongTien, int tongSL, float thanhTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.phuongThuc = phuongThuc;
        this.tongGG = tongGG;
        this.tongTien = tongTien;
        this.tongSL = tongSL;
        this.thanhTien= thanhTien;
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
    public float getTongGG() {
        return tongGG;
    }
    public void setTongGG(float tongGG) {
        this.tongGG = tongGG;
    }
    public float getTongTien() {
        return tongTien;
    }
    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    public int getTongSL() {
        return tongSL;
    }
    public void setTongSL(int tongSL) {
        this.tongSL = tongSL;
    }
    public float getThanhTien(){
        return thanhTien;
    }
    public void setThanhTien(float thanhTien){
        this.thanhTien= thanhTien;
    }
    // toString
    @Override
    public String toString() {
        return maHD + "," + maNV + "," + maKH + "," + ngayLap + "," + phuongThuc + "," + tongGG + "," + tongTien + "," + tongSL;
    }
}
