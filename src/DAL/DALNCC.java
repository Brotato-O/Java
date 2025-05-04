package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.*;

public class DALNCC {
    public ArrayList<NCC> getAllNCC (){
         ArrayList<NCC> list = new ArrayList<>();
        try {
            // Gọi getConnection từ class khác
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from NHACUNGCAP where status = 0");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                NCC b = new NCC();
                b.setMaNCC(rs.getString("maNCC"));
                b.setTenNCC(rs.getString("tenNCC"));
                b.setDiaChi(rs.getString("diaChi"));
                b.setEmail(rs.getString("email"));
                b.setSoDienThoai(rs.getString("sdt"));
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
    public boolean insertNCC(NCC ncc) {
        String sql = "INSERT INTO NHACUNGCAP (maNCC, tenNCC, diaChi, email, sdt, status) VALUES (?, ?, ?, ?, ?, 0)";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getSoDienThoai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateNCC(NCC ncc) {
        String sql = "UPDATE NHACUNGCAP SET tenNCC = ?, diaChi = ?, email = ?, sdt = ? WHERE maNCC = ?";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getDiaChi());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getSoDienThoai());
            ps.setString(5, ncc.getMaNCC());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteNCC(String maNCC) {
        String sql = "DELETE FROM NHACUNGCAP WHERE maNCC = ?";
        try (
            Connection conn = new getConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, maNCC);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
            
}
