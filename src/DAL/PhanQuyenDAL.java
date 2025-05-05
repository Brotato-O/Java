package DAL;

import DTO.PhanQuyenDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanQuyenDAL {
    
    public ArrayList<PhanQuyenDTO> getListQuyen() {
        try {
            String sql = "SELECT * FROM phanquyen";
            Statement st = Connect.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<PhanQuyenDTO> dspq = new ArrayList<PhanQuyenDTO>();
            while (rs.next()) {
                PhanQuyenDTO phanQuyen = new PhanQuyenDTO();
                phanQuyen.setQuyen(rs.getString("QUYEN"));
                phanQuyen.setNhapHang(rs.getInt("NhapHang"));
                phanQuyen.setQlSanPham(rs.getInt("QLSanPham"));
                phanQuyen.setQlNhanVien(rs.getInt("QLNhanVien"));
                phanQuyen.setQlKhachHang(rs.getInt("QLKhachHang"));
                phanQuyen.setThongKe(rs.getInt("ThongKe"));
                dspq.add(phanQuyen);
            }
            return dspq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Lấy theo tên Quyen -> để load dữ liệu lên bảng
    public PhanQuyenDTO getQuyen(String quyen) {
        try {
            String sql = "SELECT * FROM phanquyen WHERE quyen='" + quyen + "'";
            Statement st = Connect.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                PhanQuyenDTO phanQuyenDTO = new PhanQuyenDTO();
                phanQuyenDTO.setQuyen(quyen);
                phanQuyenDTO.setNhapHang(rs.getInt("NhapHang"));
                phanQuyenDTO.setQlSanPham(rs.getInt("QLSanPham"));
                phanQuyenDTO.setQlNhanVien(rs.getInt("QLNhanVien"));
                phanQuyenDTO.setQlKhachHang(rs.getInt("QLKhachHang"));
                phanQuyenDTO.setThongKe(rs.getInt("ThongKe"));
                return phanQuyenDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Thêm quyền mới
    public boolean themQuyen(PhanQuyenDTO pq) {
        try {
            String sql = "INSERT INTO phanquyen(QUYEN, NhapHang, QLSanPham, QLNhanVien, QLKhachHang, ThongKe) VALUES(?, 0, 0, 0, 0, 0)";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, pq.getQuyen());
            
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật quyền
    public boolean suaQuyen(PhanQuyenDTO pq) {
        try {
            String sql = "UPDATE phanquyen SET NhapHang=?, QLSanPham=?, QLNhanVien=?, QLKhachHang=?, ThongKe=? WHERE QUYEN=?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setInt(1, pq.getNhapHang());
            pre.setInt(2, pq.getQlSanPham());
            pre.setInt(3, pq.getQlNhanVien());
            pre.setInt(4, pq.getQlKhachHang());
            pre.setInt(5, pq.getThongKe());
            pre.setString(6, pq.getQuyen());
            
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xóa quyền
    public boolean xoaQuyen(String quyen) {
        try {
            String sql = "DELETE FROM phanquyen WHERE QUYEN=?";
            PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
            pre.setString(1, quyen);
            
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}