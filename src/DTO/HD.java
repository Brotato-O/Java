package DTO;

public class HD {
    private String maHD;
    private String maNV;
    private String maKH;
    private String ngayLap;
    private String phuongThuc;
    private int tongGG;
    private int tongTien;
    private int tongSL;
    private int thanhTien;

    public HD(){}

    public HD(String maHD, String maNV, String maKH, String ngayLap, String phuongThuc, int tongGG, int tongTien, int tongSL, int thanhTien) {
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
    public int getTongGG() {
        return tongGG;
    }
    public void setTongGG(int tongGG) {
        this.tongGG = tongGG;
    }
    public int getTongTien() {
        return tongTien;
    }
    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
    public int getTongSL() {
        return tongSL;
    }
    public void setTongSL(int tongSL) {
        this.tongSL = tongSL;
    }
    public int getThanhTien(){
        return thanhTien;
    }
    public void setThanhTien(int thanhTien){
        this.thanhTien= thanhTien;
    }
    // toString
    @Override
    public String toString() {
        return maHD + "," + maNV + "," + maKH + "," + ngayLap + "," + phuongThuc + "," + tongGG + "," + tongTien + "," + tongSL;
    }
}
