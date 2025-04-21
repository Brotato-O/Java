/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CTHD;
import DTO.HD;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class HDDAL {
    getConnection get= new getConnection();
    
    public ArrayList<HD> selectAll(){
        ArrayList<HD> hd= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
            String query= "Select * from HOADON where STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maNV= rs.getString("MANV");
                String maKH= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                HD temp= new HD(maHD, maNV, maKH, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                hd.add(temp);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public HD selectById(String id){
        HD hd= null;
        try{
            Connection conn= get.getConnection();
            String query= "Select * from HOADON where MAHD= ? and STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            prestm.setString(1, id);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maNV= rs.getString("MANV");
                String maKH= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                hd= new HD(maHD, maNV, maKH, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return hd;
    }
    
    //chua biet lam gì nen de day
    public int updateTongTien(String maHD, float thanhTien){
        
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ? where maHD=?";
            PreparedStatement prestm= conn.prepareStatement(query);
            row= get.prepareUpdate(query, maHD, thanhTien);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    
    public int updateTongTien(CTHD cthd){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ?, TONGSOLG= TONGSOLG-?, TONGGG= TONGGG-? where maHD=?";
            String query1= "Update HOADON set THANHTIEN= TONGTIEN- TONGGG";
            row= get.prepareUpdate(query, cthd.getTongTien(), cthd.getSoLuong(),  cthd.getGiamGia(), cthd.getMaHD());
            
            PreparedStatement prestm= conn.prepareStatement(query1);
            prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    
    public int delete(String maHD){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set STATUS= 1 where MAHD= ?";
            row= get.prepareUpdate(query, maHD);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
}
