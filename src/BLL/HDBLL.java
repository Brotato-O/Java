package BLL;

import DAL.HDDAL;
import DTO.CTHD;
import DTO.HD;
import java.util.ArrayList;
import javax.swing.RowFilter;

public class HDBLL {
    HDDAL hd= new HDDAL();
    public ArrayList<HD> selectAll(){
        return hd.selectAll();
    }
    
    public int updateTongTien(String maHD, float thanhTien){
        return hd.updateTongTien(maHD, thanhTien);
    }
    
    public HD selectById(String id){
        return hd.selectById(id);
    }
    
    public int updateTongTien(CTHD cthd){
        String id= cthd.getMaHD();
        System.out.println("id" + id);
        int row= hd.updateTongTien(cthd);
        System.out.println("row" + row);
        HD temp= hd.selectById(id);
        int tongSl= temp.getTongSL();
        if (tongSl== 0) hd.delete(id);
        
        return row;
    }
}
