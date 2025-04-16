/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CTHD;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTHDDAL {
    public ArrayList<CTHD> selectAll(){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        ArrayList<CTHD> cthd= new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Select * from CHITIETHOADON where STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongTien= rs.getFloat("TONGTIEN");
                float giamGia= rs.getFloat("GIAMGIA");
                
                CTHD temp= new CTHD(maHD, maSach, solg, donGia, tongTien, giamGia, thanhTien);
                cthd.add(temp);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return cthd;
    }
    
    public ArrayList<CTHD> selectById(String id){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        ArrayList<CTHD> cthd= new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Select * from CHITIETHOADON where MAHD=? and STATUS= 0";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, id);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maHD= rs.getString("MAHD");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongTien= rs.getFloat("TONGTIEN");
                float giamGia= rs.getFloat("GIAMGIA");
                
                CTHD temp= new CTHD(maHD, maSach, solg, donGia, tongTien, giamGia, thanhTien);
                cthd.add(temp);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return cthd;
    }
    
    public CTHD selectById(String id, String idSach){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        CTHD cthd= null;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Select * from CHITIETHOADON where MAHD=? and MASACH=? and STATUS= 0";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, id);
            prestm.setString(2, idSach);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maHD= rs.getString("MAHD");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongTien= rs.getFloat("TONGTIEN");
                float giamGia= rs.getFloat("GIAMGIA");
                
                cthd= new CTHD(maHD, maSach, solg, donGia, tongTien, giamGia, thanhTien);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return cthd;
    }
    
    public int delete(String maHD, String maSach){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        int row= 0;
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            String query= "Update CHITIETHOADON set STATUS= 1 where MAHD= ? and MASACH= ?";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, maHD);
            prestm.setString(2, maSach);
            row= prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
}
