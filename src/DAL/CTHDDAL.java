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
            String query= "Select * from CHITIETHOADON";
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
            String query= "Select * from CHITIETHOADON where MAHD=?";
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
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        return cthd;
    }
}
