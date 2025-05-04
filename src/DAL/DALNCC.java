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
}
