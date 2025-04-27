package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
}
