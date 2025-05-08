package BLL;

import DAL.DALNCC;
import DAL.EmpDAL;
import DAL.PNDAL;
import DTO.CTHD;
import DTO.CTPN;
import DTO.PN;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class PNBLL {
    PNDAL hd= new PNDAL();
    EmpDAL nv= new EmpDAL();
    BLLNCC ncc= new BLLNCC();
    public ArrayList<PN> selectAll(){
        return hd.selectAll();
    }
    
    public int updateTongTien(String maPN, float thanhTien){
        return hd.updateTongTien(maPN, thanhTien);
    }
    
    public PN selectById(String id){
        return hd.selectById(id);
    }
    
    public int updateTongTien(CTPN cthd){
        String id= cthd.getMaPN();
        int row= hd.updateTongTien(cthd);
        PN temp= hd.selectById(id);
        int tongSl= temp.getTongSL();
        if (tongSl== 0) delete(id);
        
        return row;
    }
    
    public int delete( String maPN){
        return hd.delete(maPN);
    }
    
    public ArrayList<PN> findPN(String maPN, String maNV, String maNCC, String ngayBD, String ngayKT, String tienBD, String tienKT){
        
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BD;
        LocalDate KT;
        float BD1= 0;
        float KT1= 0;
        
        BD= ngayBD.equals("") ? null : LocalDate.parse(ngayBD, formater);
        KT= ngayKT.equals("") ? null : LocalDate.parse(ngayKT, formater);
        BD1= tienBD.equals("") ? 0 : Float.parseFloat(tienBD);
        KT1= tienKT.equals("") ? 0 : Float.parseFloat(tienKT);
        ArrayList<PN> rs= hd.findPN(maPN, maNV, maNCC, BD, KT, BD1, KT1);
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
    
    public int editPN(String maPN, String maNV, String maNCC, String ngayLap){
        if(maNV.equals("") || maNCC.equals("") || ngayLap.equals("")) return 0;
        if(nv.getNhanVien(maNV) == null) return -1;
        if(ncc.checkNCC(maNCC) == false) return -2;
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngayLap1;
        try{
            ngayLap1= LocalDate.parse(ngayLap, formater);
        }
        catch (Exception e){
            return -3;
        }
        maPN= maPN.trim();
        maNV= maNV.trim();
        maNCC= maNCC.trim();
        ngayLap= ngayLap.trim();
        int rs =hd.editPN(maPN, maNCC, maNV, ngayLap1);
        return rs;
    }
    public int editCTPN(CTPN cthdOld, CTPN cthdNew){
        int soLg= cthdNew.getSoLg()- cthdOld.getSoLg();
        float thanhTien= cthdNew.getThanhTien()- cthdOld.getThanhTien();
        
        cthdNew.setSoLg(soLg);
        cthdNew.setThanhTien(thanhTien);
        
        return  hd.updateTongTienAdd(cthdNew);
    }
    public int updateAdd(CTPN cthd){
        return  hd.updateTongTienAdd(cthd);
    }
    public int addPN(String maPN, String maNV, String maNCC, String ngayLap, float tongTien, int tongSolg ){
        if(maNV.equals("") || maNCC.equals("") || ngayLap.equals("")) return 0;
        if(nv.getNhanVien(maNV) == null) return -1;
        if(ncc.checkNCC(maNCC) == false) return -2;
        if(hd.selectById(maPN) != null) return -4;
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngayLap1;
        try{
            ngayLap1= LocalDate.parse(ngayLap, formater);
        }
        catch (Exception e){
            return -3;
        }
        maPN= maPN.trim();
        maNV= maNV.trim();
        maNCC= maNCC.trim();
        ngayLap= ngayLap.trim();
        PN pn = new PN(maPN, maNV, maNCC, ngayLap, 0, 0);
        return hd.addPN(pn);
    }
}
