package BLL;

import DAL.HDDAL;
import DTO.CTHD;
import DTO.HD;
import java.util.ArrayList;

public class HDBLL {
    HDDAL hd= new HDDAL();
    public ArrayList<HD> selectAll(){
        return hd.selectAll();
    }
    
    public int updateTongTien(String maHD, float thanhTien){
        return hd.updateTongTien(maHD, thanhTien);
    }
    
    public int updateTongTien(CTHD cthd){
        return hd.updateTongTien(cthd);
    }
}
