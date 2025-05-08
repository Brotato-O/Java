/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CTPNDAL;
import DAL.DALQLS;
import DTO.CTPN;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CTPNBLL {
    CTPNDAL ctpn= new CTPNDAL();
    DALQLS dalqls = new DALQLS();
    public ArrayList<CTPN> selectAll(){
        return ctpn.selectAll();
    }
    public ArrayList<CTPN> selectById(String id){
        return ctpn.selectById(id);
    }
    public CTPN selectById(String id, String maSach){
        return ctpn.selectById(id, maSach);
    }
    public int delete(String maPN, String maSach, String solg){
        int solg1 = Integer.parseInt(solg);
        new DALQLS().truSoLuong(maSach, solg1);
        return ctpn.delete(maPN, maSach);
    }
    public int delete(String maPN){
        return ctpn.delete(maPN);
    }
    public int add(String maPN, String maSach, String soLuong, String donGia, String thanhTien){
        int solg1;
        float donGia1, thanhTien1;
        try{
            solg1= Integer.parseInt(soLuong);
            donGia1= Float.parseFloat(donGia);
            thanhTien1= solg1* donGia1;
        }
        catch(Exception er){
            return -1;
        }
        if (solg1==0) return -1;
        if (ctpn.selectById(maPN, maSach) != null) return -2;
        CTPN ctpn1= new CTPN(maPN, maSach, solg1, donGia1, thanhTien1);
        //sửa số sách trong bảng sách
        new DALQLS().congSoLuong(maSach, solg1);
        return ctpn.add(ctpn1);
    }
    public int update(String maPN, String maSach, String soLuong, String donGia, String thanhTien, String maPN1, String maSach1, String soLuong1){
        int solg1, soLgCu;
        float donGia1, giamGia1, tongTien1, thanhTien1;
        try{
            soLgCu = Integer.parseInt(soLuong1);
            solg1= Integer.parseInt(soLuong);
            donGia1= Float.parseFloat(donGia);
            thanhTien1= solg1* donGia1;
        }
        catch(Exception er){
            return -1;
        }
        if (solg1==0) return -1;
        if (!maPN.equals(maPN1) && ctpn.selectById(maPN1, maSach1) != null) return -2;
        CTPN ctpn1= new CTPN(maPN, maSach, solg1, donGia1 , thanhTien1);
        //sửa số lượng của sách trong bảng sách
        if(maSach.equals(maSach1)){
            int kq = solg1 -soLgCu;
            new DALQLS().congSoLuong(maSach, kq);
        }else {
            new DALQLS().truSoLuong(maSach1, soLgCu);
            new DALQLS().congSoLuong(maSach, solg1);
        }       
        return ctpn.update(ctpn1, maPN1, maSach1);
    }
}
