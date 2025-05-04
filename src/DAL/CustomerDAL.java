package DAL;

import DTO.CustomerDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAL {
    
    public ArrayList<CustomerDTO> getDSKH() {
        try {
            ArrayList<CustomerDTO> listKH = new ArrayList<CustomerDTO>();
            String sql = "SELECT * FROM KHACHHANG WHERE STATUS = 0";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            while(res.next()) {
                CustomerDTO kh = new CustomerDTO();
                kh.setMaKH(res.getString("MAKH"));
                kh.setTenKH(res.getString("TENKH"));
                kh.setSDT(res.getString("SDT"));
                kh.setEmail(res.getString("EMAIL"));
                kh.setGioiTinh(res.getString("PHAI"));
                kh.setNgaySinh(res.getString("NGAYSINH"));
                listKH.add(kh);
            }
            return listKH;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;    
    }

    public CustomerDTO getKH(String maKH) {
        try {
            String sql = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1,maKH);
            ResultSet res = pre.executeQuery();
            while(res.next()) {
                CustomerDTO kh = new CustomerDTO();
                kh.setMaKH(res.getString("MAKH"));
                kh.setTenKH(res.getString("TENKH"));
                kh.setSDT(res.getString("SDT"));
                kh.setEmail(res.getString("EMAIL"));
                kh.setGioiTinh(res.getString("PHAI"));
                kh.setNgaySinh(res.getString("NGAYSINH"));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addKH(CustomerDTO kh) {
        try {
            String sql = "INSERT INTO KHACHHANG (MAKH, TENKH, SDT ,EMAIL, PHAI, NGAYSINH,STATUS) VALUES (?, ?,?,?, ?, ?,0)";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, kh.getMaKH());
            pre.setString(2,kh.getTenKH());
            pre.setString(3, kh.getSDT());
            pre.setString(4, kh.getEmail());
            pre.setString(5, kh.getGioiTinh());
            pre.setString(6, kh.getNgaySinh());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkMaKH(String maKH) {
        try {
            String sql ="SELECT * FROM KHACHHANG WHERE MAKH = ?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, maKH);
            ResultSet res = pre.executeQuery();
            return res.next(); // Nếu có kết quả trả về thì mã khách hàng đã tồn tại
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKH(String maKH) {
        try {
            String sql = "UPDATE KHACHHANG SET STATUS = 1 WHERE MAKH = ?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, maKH);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKH(CustomerDTO kh) {
        try {
            String sql = "UPDATE KHACHHANG SET TENKH = ?, SDT = ?, EMAIL = ?, PHAI = ?, NGAYSINH = ? WHERE MAKH = ?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1,kh.getTenKH());
            pre.setString(2, kh.getSDT());
            pre.setString(3, kh.getEmail());
            pre.setString(4, kh.getGioiTinh());
            pre.setString(5, kh.getNgaySinh());
            pre.setString(6, kh.getMaKH());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<CustomerDTO> searchCbb(String item, String value) {
        ArrayList<CustomerDTO> listKH = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KHACHHANG WHERE STATUS = 0";
    
            if (item.equals("Mã Khách Hàng")) {
                sql += " AND MAKH LIKE ?";
            } else if (item.equals("Tên Khách Hàng")) {
                // Tìm kiếm chỉ theo tên (phần cuối cùng của họ và tên)
                sql += " AND SUBSTRING_INDEX(TENKH, ' ', -1) LIKE ?";
            }
    
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, "%" + value + "%"); // Tìm kiếm gần đúng (LIKE)
    
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                CustomerDTO kh = new CustomerDTO();
                kh.setMaKH(res.getString("MAKH"));
                kh.setTenKH(res.getString("TENKH"));
                kh.setSDT(res.getString("SDT"));
                kh.setEmail(res.getString("EMAIL"));
                kh.setGioiTinh(res.getString("PHAI"));
                kh.setNgaySinh(res.getString("NGAYSINH"));
                listKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }
}
