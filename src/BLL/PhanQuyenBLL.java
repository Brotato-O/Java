package BLL;

import DAL.PhanQuyenDAL;
import DTO.PhanQuyenDTO;
import java.util.ArrayList;

public class PhanQuyenBLL {
    
    public static PhanQuyenDTO quyenTK = null;
    private PhanQuyenDAL phanQuyenDAL = new PhanQuyenDAL();
    private ArrayList<PhanQuyenDTO> listPhanQuyen = null;
    
    public void docDanhSachQuyen() {
        // Đọc lại dữ liệu từ database
        this.listPhanQuyen = phanQuyenDAL.getListQuyen();
    }
    
    public void kiemTraQuyen(String quyen) {
        quyenTK = phanQuyenDAL.getQuyen(quyen);
    }
    
    public ArrayList<PhanQuyenDTO> getListQuyen() {
        if (listPhanQuyen == null)
            docDanhSachQuyen();
        return this.listPhanQuyen;
    }
    
    public ArrayList<String> getDSQuyen() {
        // Đọc lại dữ liệu từ database để đảm bảo thông tin mới nhất
        docDanhSachQuyen();
        
        ArrayList<String> dsQuyen = new ArrayList<>();
        if (listPhanQuyen != null) {
            for (PhanQuyenDTO pq : listPhanQuyen) {
                dsQuyen.add(pq.getQuyen());
            }
        }
        return dsQuyen;
    }
    
    public boolean themQuyen(String quyen) {
        try {
            // Tạo đối tượng PhanQuyenDTO với các giá trị mặc định
            PhanQuyenDTO pq = new PhanQuyenDTO();
            pq.setQuyen(quyen);
            pq.setNhapHang(0); // Mặc định là không có quyền
            pq.setQlSanPham(0);
            pq.setQlNhanVien(0);
            pq.setQlKhachHang(0);
            pq.setThongKe(0);
            
            // Thêm quyền mới vào database
            boolean result = phanQuyenDAL.themQuyen(pq);
            
            if (result) {
                // Nếu thêm thành công, đọc lại dữ liệu
                docDanhSachQuyen();
            }
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean suaQuyen(PhanQuyenDTO pq) {
        boolean result = phanQuyenDAL.suaQuyen(pq);
        if (result) {
            // Nếu sửa thành công, đọc lại dữ liệu
            docDanhSachQuyen();
        }
        return result;
    }
    
    public boolean xoaQuyen(String quyen) {
        boolean result = phanQuyenDAL.xoaQuyen(quyen);
        if (result) {
            // Nếu xóa thành công, đọc lại dữ liệu
            docDanhSachQuyen();
        }
        return result;
    }
}