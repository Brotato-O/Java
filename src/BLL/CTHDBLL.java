/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CTHDDAL;
import DTO.CTHD;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CTHDBLL {
    CTHDDAL cthd= new CTHDDAL();
    public ArrayList<CTHD> selectAll(){
        return cthd.selectAll();
    }
    public ArrayList<CTHD> selectById(String id){
        return cthd.selectById(id);
    }
    public CTHD selectById(String id, String maSach){
        return cthd.selectById(id, maSach);
    }
    public int delete(String maHD, String maSach){
        return cthd.delete(maHD, maSach);
    }
    public int delete(String maHD){
        return cthd.delete(maHD);
    }
    public int add(String maHD, String maSach, String soLuong, String donGia, String tongTien, String giamGia, String thanhTien){
        int solg1;
        float donGia1, giamGia1, tongTien1, thanhTien1;
        try{
            solg1= Integer.parseInt(soLuong);
            donGia1= Float.parseFloat(donGia);
            giamGia1= Float.parseFloat(giamGia);
            tongTien1= solg1* donGia1;
            thanhTien1= tongTien1- giamGia1;
        }
        catch(Exception er){
            return -1;
        }
        if (solg1==0) return -1;
        if (cthd.selectById(maHD, maSach) != null) return -2;
        CTHD cthd1= new CTHD(maHD, maSach, solg1, donGia1, tongTien1, giamGia1, thanhTien1);
        return cthd.add(cthd1);
    }
    public int update(String maHD, String maSach, String soLuong, String donGia, String tongTien, String giamGia, String thanhTien, String maHD1, String maSach1){
        int solg1;
        float donGia1, giamGia1, tongTien1, thanhTien1;
        try{
            solg1= Integer.parseInt(soLuong);
            donGia1= Float.parseFloat(donGia);
            giamGia1= Float.parseFloat(giamGia);
            tongTien1= solg1* donGia1;
            thanhTien1= tongTien1- giamGia1;
        }
        catch(Exception er){
            return -1;
        }
        if (solg1==0) return -1;
        if (!maHD.equals(maHD1) && cthd.selectById(maHD1, maSach1) != null) return -2;
        CTHD cthd1= new CTHD(maHD, maSach, solg1, donGia1, tongTien1, giamGia1 , thanhTien1);
        return cthd.update(cthd1, maHD1, maSach1);
    }
}
