package DTO;

public class Book {
    private String maSach;
    private String tenSach;
    private String maLoai;
    private String maTacGia;
    private String maNCC;
    private int soLuong;
    private float donGia;
    private int namXB;
    private String hinhAnh;

    // ✅ Constructor có tham số
    public Book(String maSach, String tenSach, String maLoai, String maTacGia, String maNCC, int soLuong, float donGia,int namXB,String hinhAnh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maLoai = maLoai;
        this.maTacGia = maTacGia;
        this.maNCC = maNCC;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.namXB = namXB;
        this.hinhAnh= hinhAnh;
    }

    // ✅ Constructor không tham số (mặc định)
    public Book() {
    }

    // ✅ Getter và Setter
    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }
    public String getHA() {
        return hinhAnh;
    }

    public void setHA(String HA) {
        this.hinhAnh = HA;
    }
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
}

