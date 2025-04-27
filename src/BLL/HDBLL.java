package BLL;

import DAL.HDDAL;
import DTO.CTHD;
import DTO.HD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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
    
    public ArrayList<HD> findHD(String maHD, String maNV, String maKH, String phuongThuc, String ngayBD, String ngayKT, String tienBD, String tienKT){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate BD;
        LocalDate KT;
        float BD1= 0;
        float KT1= 0;
        try{
            BD= LocalDate.parse(tienBD, formater);
            KT= LocalDate.parse(tienKT, formater);
            BD1= Float.parseFloat(tienBD);
            KT1= Float.parseFloat(tienKT);
        }
        catch (Exception e){
            BD= null;
            KT= null;
        }
        ArrayList<HD> rs= hd.findHD(maHD, maNV, maKH, phuongThuc, BD, KT, BD1, KT1);
        return rs;
    }
}
