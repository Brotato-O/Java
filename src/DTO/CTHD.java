package DTO;

public class CTHD {
    private String maHD;
    private String maSach;
    private int soLuong;
    private float giaTien;
    private float tongTien;
    private float giamGia;
    private float thanhTien;

    public CTHD(){};

    public CTHD(String maHD, String maSach, int soLuong, float giaTien, float tongTien, float giamGia, float thanhTien) {
        this.maHD = maHD;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.thanhTien = thanhTien;
    }
    // Getters and setters
    public String getMaHD() {
        return maHD;
    }
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }
    public String getMaSach() {
        return maSach;
    }
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public float getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }
    public float getTongTien() {
        return tongTien;
    }
    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    public float getGiamGia() {
        return giamGia;
    }
    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }
    public float getThanhTien() {
        return thanhTien;
    }
    @Override
    public String toString() {
        return "";
    }
}
