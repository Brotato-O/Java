package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.*;

public class DALQLLS {
    public ArrayList <LoaiSach> getAllLoai(){
        ArrayList<LoaiSach>  list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from LOAISACH");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                LoaiSach b = new LoaiSach();
                b.setMaLoai(rs.getString("maloai"));
                b.setTenLoai(rs.getString("tenloai"));
                b.setDoTuoi(rs.getInt("dotuoi"));
                list.add(b);
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addLS( LoaiSach book){
        
        String sql ="insert into LOAISACH (maloai, tenloai, dotuoi) values(?, ?, ?)";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, book.getMaLoai());
           ps.setString(2, book.getTenLoai());
           ps.setInt(3, book.getDoTuoi());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean deleteLSSQL( LoaiSach book){
        
        String sql ="DELETE from LOAISACH wHERE MALOAI=?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getMaLoai());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean updateLS(LoaiSach book) {
        String sql = "UPDATE LOAISACH SET maloai = ?, tenloai = ?, dotuoi = ? WHERE maloai = ?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getMaLoai());
            ps.setString(2, book.getTenLoai());
            ps.setInt(3, book.getDoTuoi());
            ps.setString(4, book.getMaLoai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Book> selectSach(String id) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM SACH WHERE MALOAI = ?";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book b = new Book();
                    b.setMaSach(rs.getString("maSach"));
                    b.setTenSach(rs.getString("tenSach"));
                    b.setMaLoai(rs.getString("maLoai"));
                    b.setMaTacGia(rs.getString("matg"));
                    b.setSoLuong(rs.getInt("soLg"));
                    b.setNamXB(rs.getInt("namXB"));
                    b.setDonGia(rs.getFloat("donGia"));
                    list.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
