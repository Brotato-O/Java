/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CTPN;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTPNDAL {
    getConnection get= new getConnection();
    public ArrayList<CTPN> selectAll(){
        ArrayList<CTPN> ctpn= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
            String query= "Select * from CHITIETPHIEUNHAP where STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maPN= rs.getString("MAPN");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                
                CTPN temp= new CTPN(maPN, maSach, solg, donGia, thanhTien);
                ctpn.add(temp);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ctpn;
    }
    
    public ArrayList<CTPN> selectById(String id){
        ArrayList<CTPN> ctpn= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
            String query= "Select * from CHITIETPHIEUNHAP where MAPN=? and STATUS= 0";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, id);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maPN= rs.getString("MAPN");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                
                CTPN temp= new CTPN(maPN, maSach, solg, donGia, thanhTien);
                ctpn.add(temp);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ctpn;
    }
    
    public CTPN selectById(String id, String idSach){
        CTPN ctpn= null;
        try{
            Connection conn= get.getConnection();
            String query= "Select * from CHITIETPHIEUNHAP where MAPN=? and MASACH=? and STATUS= 0";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, id);
            prestm.setString(2, idSach);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maPN= rs.getString("MAPN");
                String maSach= rs.getString("MASACH");
                int solg= rs.getInt("SOLG");
                float donGia= rs.getFloat("DONGIA");
                float thanhTien= rs.getFloat("THANHTIEN");
                
                ctpn= new CTPN(maPN, maSach, solg, donGia, thanhTien);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ctpn;
    }
    
    public int delete(String maPN, String maSach){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update CHITIETPHIEUNHAP set STATUS= 1 where MAPN= ? and MASACH= ?";
            PreparedStatement prestm= conn.prepareCall(query);
            prestm.setString(1, maPN);
            prestm.setString(2, maSach);
            row= prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    public int delete(String maPN){
        String query= "Update CHITIETPHIEUNHAP set STATUS= 1 where maPN= ?";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, maPN);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public int add(CTPN ctpn){
        String query =" Insert into Chitietphieunhap(mapn, masach, solg, dongia, thanhtien, status) values (?, ?, ?, ?, ?, ?)";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, ctpn.getMaPN(), ctpn.getMaSach(), ctpn.getSoLg(), ctpn.getDonGia(), ctpn.getThanhTien(), 0);
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public int add1(CTPN ctpn) {
        String query = "INSERT INTO Chitiethoadon(mahd, masach, solg, dongia, thanhtien, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
    
            ps.setString(1, ctpn.getMaPN());
            ps.setString(2, ctpn.getMaSach());
            ps.setInt(3, ctpn.getSoLg());
            ps.setDouble(4, ctpn.getDonGia());
            ps.setDouble(5, ctpn.getThanhTien());
            ps.setInt(6, 0); // status mặc định
    
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi add CTPN: " + e);
            return 0;
        }
    }
    public boolean exists(String maPN, String maSach) {
        String query = "SELECT 1 FROM Chitietphieunhap WHERE mapn = ? AND masach = ?";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maPN);
            ps.setString(2, maSach);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Lỗi exists: " + e);
        }
        return false;
    }
    public int updateSoLuong(CTPN ctpn) {
        String query = "UPDATE Chitiethoadon SET solg = solg + ?, thanhtien = thanhtien + ? WHERE mapn = ? AND masach = ?";
        try (Connection conn = get.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ctpn.getSoLg());
            ps.setDouble(2, ctpn.getThanhTien());
            ps.setString(3, ctpn.getMaPN());
            ps.setString(4, ctpn.getMaSach());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi updateSoLuong: " + e);
        }
        return 0;
    }
    public int saveCTPN(CTPN ctpn) {
        if (exists(ctpn.getMaPN(), ctpn.getMaSach())) {
            return updateSoLuong(ctpn);
        } else {
            return add1(ctpn);
        }
    }
    public int update(CTPN ctpn, String oldMaPN, String oldMaSach) {
    String query = "UPDATE Chitietphieunhap SET mapn = ?, masach = ?, solg = ?, dongia = ?,  thanhtien = ?, status = ? WHERE mahd = ? AND masach = ?";
    int rs = 0;
    try {
        Connection conn = get.getConnection();
        rs = get.prepareUpdate(query,
            ctpn.getMaPN(),        // mới
            ctpn.getMaSach(),      // mới
            ctpn.getSoLg(),
            ctpn.getDonGia(),
            ctpn.getThanhTien(),
            0,                     // status mới
            oldMaPN,               // điều kiện cũ
            oldMaSach              // điều kiện cũ
        );
        conn.close();
    } catch (Exception e) {
        e.printStackTrace(); // để bạn dễ debug
    }
    return rs;
}


    
    
    // public int addAll(ArrayList<CTPN> list) {
    //     String query = "INSERT INTO Chitiethoadon(mahd, masach, solg, dongia, tongtien, giamgia, thanhtien, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //     int count = 0;
    //     String updateQuery = "UPDATE SACH SET soluong = soluong - ? WHERE masach = ?";
    //     try (Connection conn = get.getConnection()) {
    //         for (CTPN ctpn : list) {
    //             int result = get.prepareUpdate(query, ctpn.getMaPN(), ctpn.getMaSach(), ctpn.getSoLuong(),
    //                     ctpn.getGiaTien(), ctpn.getTongTien(), ctpn.getGiamGia(), ctpn.getThanhTien(), 0);
    //             if (result > 0){
    //                 get.prepareUpdate(updateQuery, ctpn.getSoLuong(), ctpn.getMaSach());
    //                 count++;
    //             }
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Lỗi khi thêm danh sách CTPN: " + e);
    //     }
    //     return count;
    // }
   
    
    
}
