package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.CTGG;
import DTO.GG;

public class DALGG {
    public ArrayList<GG> getAllGG(){
        ArrayList<GG> list = new ArrayList<>();
         String sql = "select * from GIAMGIA ";
        
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                GG b = new GG();
                b.setMaGG(rs.getString("magg"));
                b.setTenGG(rs.getString("tengg"));
                b.setLuongGiam(rs.getFloat("luonggiam"));
                b.setNgayBD(rs.getString("ngaybd"));
                b.setNgayKT(rs.getString("ngaykt"));
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
    public boolean addGG( GG GG){
        
        String sql ="insert into GIAMGIA (magg, tengg, luonggiam, ngaybd, ngaykt) values(?, ?, ?, ?, ?)";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, GG.getMaGG());
           ps.setString(2, GG.getTenGG());
           ps.setFloat(3, GG.getLuongGiam());
           ps.setString(4, GG.getNgayBD());
           ps.setString(5, GG.getNgayKT());
           return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean deleteGGSQL( GG GG){
        
        String sql ="DELETE from GIAMGIA wHERE magg=?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, GG.getMaGG());
           return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean updateGG(GG GG) {
        String sql = "UPDATE GIAMGIA SET magg=?, tengg = ?, luonggiam = ?, ngaybd = ?, ngaykt = ? WHERE magg = ?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, GG.getMaGG());
            ps.setString(2, GG.getTenGG());
            ps.setFloat(3, GG.getLuongGiam());
            ps.setString(4, GG.getNgayBD());
            ps.setString(5, GG.getNgayKT());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<CTGG> seleCtgg(String id) {
        ArrayList<CTGG> list = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETGIAMGIA WHERE MAGG = ?";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CTGG b = new CTGG();
                    b.setMaGG(rs.getString("magg"));
                    b.setMaSach(rs.getString("masach"));
                    list.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<GG> selectGGByBook(String id) {
        ArrayList<GG> list = new ArrayList<>();
        String sql = "SELECT * FROM GIAMGIA join CHITIETGIAMGIA ON GIAMGIA.MAGG = CHITIETGIAMGIA.MAGG where MASACH = ?";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GG b = new GG();
                    b.setMaGG(rs.getString("magg"));
                    b.setTenGG(rs.getString("tengg"));
                    b.setLuongGiam(rs.getFloat("LUONGGIAM"));
                    b.setNgayBD(rs.getString("NgayBD"));
                    b.setNgayKT(rs.getString("NgayKT"));
                    list.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean addCTGG( CTGG ctgg){
        
        String sql ="insert into CHITIETGIAMGIA (masach, magg) values(?, ?)";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, ctgg.getMaSach());
           ps.setString(2, ctgg.getMaGG());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public boolean deleteCTGGSQL( CTGG ctgg){
        
        String sql ="DELETE from CHITIETGIAMGIA wHERE magg = ? AND masach = ?";
        try {
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ctgg.getMaGG());
            ps.setString(2, ctgg.getMaSach());
           return ps.executeUpdate() > 0;

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
    public ArrayList<GG> selectOneGG(String id){
        ArrayList<GG> list = new ArrayList<>();
        String sql = "select * from CHITIETGIAMGIA join GIAMGIA on CHITIETGIAMGIA.magg = GIAMGIA.magg where masach = ?";
       
       try {
           // Gọi getConnection từ class khác
           Connection conn = new getConnection().getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, id);
           ResultSet rs = ps.executeQuery();
          
           
           while (rs.next()) {
               GG b = new GG();
               b.setMaGG(rs.getString("magg"));
               b.setTenGG(rs.getString("tengg"));
               b.setLuongGiam(rs.getFloat("luonggiam"));
               b.setNgayBD(rs.getString("ngaybd"));
               b.setNgayKT(rs.getString("ngaykt"));
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
    }
