package DAL;

import java.util.ArrayList;
import DTO.*;
import java.sql.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DALQLS {
    public  ArrayList<Book> getAllBooks() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT \r\n" + //
                        "    masach, \r\n" + //
                        "    tensach, \r\n" + //
                        "    tenloai, \r\n" + //
                        "    solg, \r\n" + //
                        "    dongia, \r\n" + //
                        "    tentg, \r\n" + //
                        "    namxb \r\n" + //
                        "FROM \r\n" + //
                        "    SACH\r\n" + //
                        "JOIN \r\n" + //
                        "    TACGIA ON SACH.matg = TACGIA.matg\r\n" + //
                        "JOIN \r\n" + //
                        "   LOAISACH ON SACH.maloai = LOAISACH.maloai\r\n" + //
                        "WHERE \r\n" + //
                        "    SACH.status = 1;\r\n" + //
                        "";
        
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Book b = new Book();
                b.setMaSach(rs.getString("maSach"));
                b.setTenSach(rs.getString("tenSach"));
                b.setMaLoai(rs.getString("tenLoai"));
                b.setMaTacGia(rs.getString("tentg"));
                b.setSoLuong(rs.getInt("soLg"));
                b.setNamXB(rs.getInt("namXB"));
                b.setDonGia(rs.getFloat("donGia"));
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
    public  ArrayList<map> getAllTG() {
        ArrayList<map> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select matg, tentg from TACGIA");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String ma = rs.getString("matg");
                String ten = rs.getString("tentg");
                list.add(new map(ma, ten));
                
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }
    public  ArrayList<String> getAllTL() {
        ArrayList<String> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT maloai FROM SACH" );
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                String ten = rs.getString("maloai");
                list.add(new String(ten));
                
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addBook( Book book){
        
        String sql ="insert into SACH (masach, tensach, maloai, matg, solg, dongia, namxb, status) values(?, ?, ?, ?, ?, ?, ?,1)";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, book.getMaSach());
           ps.setString(2, book.getTenSach());
           ps.setString(3, book.getMaLoai());
           ps.setString(4, book.getMaTacGia());
           ps.setInt(5, book.getSoLuong());
           ps.setFloat(6, book.getDonGia());
           ps.setInt(7, book.getNamXB());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean deleteBookSQL( Book book){
        
        String sql ="DELETE from SACH wHERE SACH.MASACH=?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getMaSach());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean deleteBook( Book book){
        
        String sql ="UPDATE SACH SET status =0 wHERE SACH.MASACH=?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getMaSach());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean updateBook(Book book) {
        String sql = "UPDATE SACH SET tensach = ?, maloai = ?, matg = ?, solg = ?, dongia = ?, namxb = ? WHERE masach = ?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTenSach());
            ps.setString(2, book.getMaLoai());
            ps.setString(3, book.getMaTacGia());
            ps.setInt(4, book.getSoLuong());
            ps.setFloat(5, book.getDonGia());
            ps.setInt(6, book.getNamXB());
            ps.setString(7, book.getMaSach());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String generateNewBookId() {
        String sql = "SELECT COUNT(*) FROM SACH";
        try (Connection conn = new getConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return "s" + (count + 1); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "s1";  
    }

    public boolean outputExcel(String filePath){
        String sql = "SELECT * FROM SACH";
        try (Connection conn = new getConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             PrintWriter writer = new PrintWriter(new FileWriter(filePath)))  {
            writer.println("masach, tensach, maloai, matg, solg, dongia, namxb, status");
            
            while (rs.next()) {
                String line = String.format("%s,%s,%s,%s,%d,%d,%.2f,%d",
                rs.getString("maSach"),
                rs.getString("tenSach"),
                rs.getString("maLoai"),
                rs.getString("matg"),
                rs.getInt("soLg"),
                rs.getInt("namXB"),
                rs.getFloat("donGia"),
                rs.getInt("status")
                );
                writer.println(line);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  

    }
    public float getMaxPrice() {
        String sql = "SELECT MAX(dongia) FROM SACH";
        try (Connection conn = new getConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            if (rs.next()) {
                return rs.getFloat(1); 
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; 
    }
    public float getMinPrice() {
        String sql = "SELECT MIN(dongia) FROM SACH";
        try (Connection conn = new getConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            if (rs.next()) {
                return rs.getFloat(1); 
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; 
    }
    
    public ArrayList<map> getAllLS(){
        ArrayList<map> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from LOAISACH");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String ma = rs.getString("maloai");
                String ten = rs.getString("tenloai");
                list.add(new map(ma, ten));
                
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public int getSoLuong(String maSach) {
        String query = "SELECT solg FROM SACH WHERE masach = ?";
        try ( Connection conn = new getConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maSach);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("solg");
            }
        } catch (Exception e) {
            System.out.println("Lỗi getSoLuong: " + e);
        }
        return -1; // hoặc throw exception tùy cách bạn xử lý
    }
    
    public int truSoLuong(String maSach, int soLuong) {
        String query = "UPDATE SACH SET solg = solg - ? WHERE masach = ?";
        try (Connection conn = new getConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, soLuong);
            ps.setString(2, maSach);
            return ps.executeUpdate(); 
        } catch (Exception e) {
            System.out.println("Lỗi truSoLuong: " + e);
        }
        return 0;
    }    
    public int congSoLuong(String maSach, int soLuong) {
        String query = "UPDATE SACH SET solg = solg + ? WHERE masach = ?";
        try (Connection conn = new getConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, soLuong);
            ps.setString(2, maSach);
            return ps.executeUpdate(); 
        } catch (Exception e) {
            System.out.println("Lỗi congSoLuong: " + e);
        }
        return 0;
    }    
    public ArrayList<map> typeBook(){
        ArrayList<map> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select tenloai, count(masach) as soluong from SACH join  LOAISACH on SACH.maloai = LOAISACH.maloai WHERE SACH.status = 1 GROUP BY tenloai;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String ma = rs.getString("tenloai");
                String soLuongStr = String.valueOf(rs.getInt("soluong"));
                list.add(new map(ma, soLuongStr));
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<map> tgBook(){
        ArrayList<map> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select tentg, count(masach) as soluong from SACH join   TACGIA ON SACH.matg = TACGIA.matg WHERE SACH.status = 1 GROUP BY tentg;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String ma = rs.getString("tentg");
                String soLuongStr = String.valueOf(rs.getInt("soluong"));
                list.add(new map(ma, soLuongStr));
            }
            rs.close();
            ps.close();
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // public boolean addBook1(Book book) {
    //     String checkSql = "SELECT * FROM SACH WHERE masach = ? AND mancc = ?";
    //     String updateSql = "UPDATE SACH SET solg = solg + ? WHERE masach = ? AND mancc = ?";
    //     String insertSql = "INSERT INTO SACH (masach, tensach, maloai, matg, mancc, solg, dongia, hinhanh, namxb, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
    
    //     try (Connection conn = new getConnection().getConnection()) {
    //         // 1. Kiểm tra xem masach + mancc đã tồn tại chưa
    //         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
    //         checkStmt.setString(1, book.getMaSach());
    //         checkStmt.setString(2, book.getMaNCC());
    
    //         ResultSet rs = checkStmt.executeQuery();
    
    //         if (rs.next()) {
    //             // 2. Nếu tồn tại → cộng thêm số lượng
    //             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
    //             updateStmt.setInt(1, book.getSoLuong());
    //             updateStmt.setString(2, book.getMaSach());
    //             updateStmt.setString(3, book.getMaNCC());
    //             return updateStmt.executeUpdate() > 0;
    //         } else {
    //             // 3. Nếu không → thêm mới
    //             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
    //             insertStmt.setString(1, book.getMaSach());
    //             insertStmt.setString(2, book.getTenSach());
    //             insertStmt.setString(3, book.getMaLoai());
    //             insertStmt.setString(4, book.getMaTacGia());
    //             insertStmt.setString(5, book.getMaNCC());
    //             insertStmt.setInt(6, book.getSoLuong());
    //             insertStmt.setFloat(7, book.getDonGia());
    //             insertStmt.setString(8, book.getHA());
    //             insertStmt.setInt(9, book.getNamXB());
    //             return insertStmt.executeUpdate() > 0;
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    
}
