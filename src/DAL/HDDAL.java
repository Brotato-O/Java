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
    public ArrayList<HD> selectAll(){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        ArrayList<HD> hd= new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
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
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public HD selectById(String id){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        HD hd= null;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
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
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    //chua biet lam gì nen de day
    public int updateTongTien(String maHD, float thanhTien){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        int row= 0;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ? where maHD=?";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setFloat(1, thanhTien);
            prestm.setString(2, maHD);
            System.out.println(thanhTien);
            row= prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    
    public int updateTongTien(CTHD cthd){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        int row= 0;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ?, TONGSOLG= TONGSOLG-?, TONGGG= TONGGG-? where maHD=?";
            String query1= "Update HOADON set THANHTIEN= TONGTIEN- TONGGG";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setFloat(1, cthd.getTongTien());
            prestm.setInt(2, cthd.getSoLuong());
            prestm.setFloat(3, cthd.getGiamGia());
            prestm.setString(4, cthd.getMaHD());
            
            prestm.executeUpdate();
            
            prestm= conn.prepareCall(query1);
            row= prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    
    public int delete(String maHD){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        int row= 0;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Update HOADON set STATUS= 1 where MAHD= ?";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, maHD);
            row= prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
}
