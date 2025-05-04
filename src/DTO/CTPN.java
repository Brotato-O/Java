/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class CTPN {
    private String maPN;
    private String maSach;
    private float donGia;
    private int soLg;
    private float thanhTien;

    // Constructor không tham số
    public CTPN() {
    }

    // Constructor đầy đủ
    public CTPN(String maPN, String maSach, float donGia, int soLg, float thanhTien) {
        this.maPN = maPN;
        this.maSach = maSach;
        this.donGia = donGia;
        this.soLg = soLg;
        this.thanhTien = thanhTien;
    }

    // Getter và Setter
    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getSoLg() {
        return soLg;
    }

    public void setSoLg(int soLg) {
        this.soLg = soLg;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
}

