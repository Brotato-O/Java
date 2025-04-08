package DTO;

public class CTHD {
    private String maHD;
    private String maSach;
    private int soLuong;
    private double giaTien;
    private double tongTien;
    private double giamGia;
    private double thanhTien;

    public CTHD(){};

    public CTHD(String maHD, String maSach, int soLuong, double giaTien, double tongTien, double giamGia, double thanhTien) {
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
    public double getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
    public double getTongTien() {
        return tongTien;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    public double getGiamGia() {
        return giamGia;
    }
    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }
    public double getThanhTien() {
        return thanhTien;
    }
    @Override
    public String toString() {
        return "";
    }
}
