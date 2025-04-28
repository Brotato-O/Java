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
        int row= hd.updateTongTien(cthd);
        HD temp= hd.selectById(id);
        int tongSl= temp.getTongSL();
        if (tongSl== 0) delete(id);
        
        return row;
    }
    
    public int delete( String maHD){
        return hd.delete(maHD);
    }
    
    public ArrayList<HD> findHD(String maHD, String maNV, String maKH, String phuongThuc, String ngayBD, String ngayKT, String tienBD, String tienKT){
        switch (phuongThuc){
                    case "Tất cả":
                        phuongThuc= "Tat ca";
                        break;
                    case "Tiền mặt":
                        phuongThuc= "Tien mat";
                        break;
                    case "Chuyển khoản":
                        phuongThuc= "Chuyen khoan";
                        break;
                    case "Quẹt thẻ":
                        phuongThuc= "Quet the";
                        break;
                }
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BD;
        LocalDate KT;
        float BD1= 0;
        float KT1= 0;
        
        BD= ngayBD.equals("") ? null : LocalDate.parse(ngayBD, formater);
        KT= ngayKT.equals("") ? null : LocalDate.parse(ngayKT, formater);
        BD1= tienBD.equals("") ? 0 : Float.parseFloat(tienBD);
        KT1= tienKT.equals("") ? 0 : Float.parseFloat(tienKT);
        ArrayList<HD> rs= hd.findHD(maHD, maNV, maKH, phuongThuc, BD, KT, BD1, KT1);
        return rs;
    }
    
    public int check(String ngayBD, String ngayKT, String tienBD, String tienKT){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BD;
        LocalDate KT;
        float BD1= 0;
        float KT1= 0;
        if (!ngayBD.equals(""))
            try{
                BD= LocalDate.parse(ngayBD, formater);
            }
            catch (Exception e){
                return 1;
            }
        if (!ngayKT.equals(""))
            try{
                KT= LocalDate.parse(ngayKT, formater);
            }
            catch (Exception e){
                return 1;
            }
        if (!tienBD.equals(""))
            try{
                BD1= Float.parseFloat(tienBD);
            }
            catch (Exception e){
                return 2;
            }
        if (!tienKT.equals(""))
            try{
                KT1= Float.parseFloat(tienKT);
            }
            catch (Exception e){
                return 2;
            }
        return 0;
    }
}
