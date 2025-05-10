package BLL;

import DAL.CustomerDAL;
import DTO.CustomerDTO; 
import java.util.ArrayList;

public class CustomerBLL {
    
    private CustomerDAL customerDAL = new CustomerDAL(); //Lấy sử lí database
    private ArrayList<CustomerDTO> listKH = null; //Thay đổi dữ liệu đồ

    public CustomerBLL() {
        this.listKH = getDSKH();
    }

    public ArrayList<CustomerDTO> getListDSKhoa() {
        return this.customerDAL.getListKBbiKHoa();
    }

    public boolean khoiPhucTaiKhoan(String maKH) {
        return this.customerDAL.khoiPhucTaiKhoan(maKH);
    }

    public boolean xoaVinhVien(String maKH) {
        return this.customerDAL.xoaVinhVien(maKH);
    }

    public ArrayList<CustomerDTO> getDSKH() {
        return this.customerDAL.getDSKH();
    }   

    public CustomerDTO getKH(String maKH) {
        return this.customerDAL.getKH(maKH);
    }

    public boolean addKH(CustomerDTO kh) {
        if(!this.customerDAL.checkMaKH(kh.getMaKH())) {
            return this.customerDAL.addKH(kh);
        }
        return false;
    }

    public boolean deleteKH(String maKH) {
        return this.customerDAL.deleteKH(maKH);
    }

    public boolean updateKH(CustomerDTO kh) {
        return this.customerDAL.updateKH(kh);
    }

    public ArrayList<CustomerDTO> searchKH(String item,String value) {
        return this.customerDAL.searchCbb(item, value);
    }

}
