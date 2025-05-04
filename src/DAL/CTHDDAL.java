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
    public int add(CTHD cthd){
        String query =" Insert into Chitiethoadon(mahd, masach, solg, dongia, tongtien, giamgia, thanhtien, status) values (?, ?, ?, ?, ?, ?, ? ,?)";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, cthd.getMaHD(), cthd.getMaSach(), cthd.getSoLuong(), cthd.getGiaTien(), cthd.getTongTien(), cthd.getGiamGia(), cthd.getThanhTien(), 0);
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public int add1(CTHD cthd) {
        String query = "INSERT INTO Chitiethoadon(mahd, masach, solg, dongia, tongtien, giamgia, thanhtien, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
    
            ps.setString(1, cthd.getMaHD());
            ps.setString(2, cthd.getMaSach());
            ps.setInt(3, cthd.getSoLuong());
            ps.setDouble(4, cthd.getGiaTien());
            ps.setDouble(5, cthd.getTongTien());
            ps.setDouble(6, cthd.getGiamGia());
            ps.setDouble(7, cthd.getThanhTien());
            ps.setInt(8, 0); // status mặc định
    
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi add CTHD: " + e);
            return 0;
        }
    }
    public boolean exists(String maHD, String maSach) {
        String query = "SELECT 1 FROM Chitiethoadon WHERE mahd = ? AND masach = ?";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHD);
            ps.setString(2, maSach);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Lỗi exists: " + e);
        }
        return false;
    }
    public int updateSoLuong(CTHD cthd) {
        String query = "UPDATE Chitiethoadon SET solg = solg + ?, tongtien = tongtien + ?, thanhtien = thanhtien + ? WHERE mahd = ? AND masach = ?";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cthd.getSoLuong());
            ps.setDouble(2, cthd.getTongTien());
            ps.setDouble(3, cthd.getThanhTien());
            ps.setString(4, cthd.getMaHD());
            ps.setString(5, cthd.getMaSach());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi updateSoLuong: " + e);
        }
        return 0;
    }
    public int saveCTHD(CTHD cthd) {
        if (exists(cthd.getMaHD(), cthd.getMaSach())) {
            return updateSoLuong(cthd);
        } else {
            return add1(cthd);
        }
    }
    
    
    
    // public int addAll(ArrayList<CTHD> list) {
    //     String query = "INSERT INTO Chitiethoadon(mahd, masach, solg, dongia, tongtien, giamgia, thanhtien, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //     int count = 0;
    //     String updateQuery = "UPDATE SACH SET soluong = soluong - ? WHERE masach = ?";
    //     try (Connection conn = get.getConnection()) {
    //         for (CTHD cthd : list) {
    //             int result = get.prepareUpdate(query, cthd.getMaHD(), cthd.getMaSach(), cthd.getSoLuong(),
    //                     cthd.getGiaTien(), cthd.getTongTien(), cthd.getGiamGia(), cthd.getThanhTien(), 0);
    //             if (result > 0){
    //                 get.prepareUpdate(updateQuery, cthd.getSoLuong(), cthd.getMaSach());
    //                 count++;
    //             }
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Lỗi khi thêm danh sách CTHD: " + e);
    //     }
    //     return count;
    // }
   
    
    
}
