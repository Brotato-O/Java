package BLL;

import java.util.ArrayList;

import DAL.DALNCC;
import DTO.NCC;

public class BLLNCC {
    private DALNCC dalncc = new DALNCC();
    public ArrayList<NCC> getAllNCC() {
        return dalncc.getAllNCC();
    }
    public boolean checkNCC(String maNCC) {
        ArrayList<NCC> ncc= getAllNCC();
        for (int i=0; i< ncc.size(); i++)
            if(ncc.get(i).getMaNCC().equalsIgnoreCase(maNCC))
                return false;
        return true;
    }
    public boolean addNCC(NCC ncc) {
        if (isValidNCC(ncc)) {
            return dalncc.insertNCC(ncc);
        }
        return false;
    }

    // Sửa NCC theo mã
    public boolean updateNCC(NCC ncc) {
        if (isValidNCC(ncc)) {
            return dalncc.updateNCC(ncc);
        }
        return false;
    }

    // Xóa mềm NCC theo mã
    public boolean deleteNCC(String maNCC) {
        if (maNCC != null && !maNCC.trim().isEmpty()) {
            return dalncc.deleteNCC(maNCC);
        }
        return false;
    }

    // Kiểm tra dữ liệu hợp lệ
    private boolean isValidNCC(NCC ncc) {
        return ncc != null
            && ncc.getMaNCC() != null && !ncc.getMaNCC().trim().isEmpty()
            && ncc.getTenNCC() != null && !ncc.getTenNCC().trim().isEmpty()
            && ncc.getDiaChi() != null && !ncc.getDiaChi().trim().isEmpty()
            && ncc.getEmail() != null && !ncc.getEmail().trim().isEmpty()
            && ncc.getSoDienThoai() != null && !ncc.getSoDienThoai().trim().isEmpty();
    }
    
}
