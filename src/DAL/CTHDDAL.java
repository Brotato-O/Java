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
    getConnection get= new getConnection();
    public ArrayList<CTHD> selectAll(){
        ArrayList<CTHD> cthd= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
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
        ArrayList<CTHD> cthd= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
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
        CTHD cthd= null;
        try{
            Connection conn= get.getConnection();
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
        int row= 0;
        try{
            Connection conn= get.getConnection();
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
    public int delete(String maHD){
        String query= "Update CHITIETHOADON set STATUS= 1 where maHD= ?";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, maHD);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
}
