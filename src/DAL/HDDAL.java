
package DAL;

import DTO.CTHD;
import DTO.HD;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
public class HDDAL {
    getConnection get= new getConnection();
    
    public ArrayList<HD> selectAll(){
        ArrayList<HD> hd= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
            String query= "Select * from HOADON where STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maNV= rs.getString("MANV");
                String maKH= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                HD temp= new HD(maHD, maNV, maKH, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                hd.add(temp);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public HD selectById(String id){
        HD hd= null;
        try{
            Connection conn= get.getConnection();
            String query= "Select * from HOADON where MAHD= ? and STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            prestm.setString(1, id);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maNV= rs.getString("MANV");
                String maKH= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                hd= new HD(maHD, maNV, maKH, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return hd;
    }
    
    //chua biet lam gì nen de day
    public int updateTongTien(String maHD, float thanhTien){
        
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ? where maHD=?";
            PreparedStatement prestm= conn.prepareStatement(query);
            row= get.prepareUpdate(query, maHD, thanhTien);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    
    public int updateTongTien(CTHD cthd){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set TONGTIEN= TONGTIEN - ?, TONGSOLG= TONGSOLG-?, TONGGG= TONGGG-? where maHD=?";
            String query1= "Update HOADON set THANHTIEN= TONGTIEN- TONGGG";
            row= get.prepareUpdate(query, cthd.getTongTien(), cthd.getSoLuong(),  cthd.getGiamGia(), cthd.getMaHD());
            
            PreparedStatement prestm= conn.prepareStatement(query1);
            prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    public int updateTongTienAdd(CTHD cthd){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set TONGTIEN= TONGTIEN + ?, TONGSOLG= TONGSOLG+?, TONGGG= TONGGG+? where maHD=?";
            String query1= "Update HOADON set THANHTIEN= TONGTIEN- TONGGG";
            row= get.prepareUpdate(query, cthd.getTongTien(), cthd.getSoLuong(),  cthd.getGiamGia(), cthd.getMaHD());
            
            PreparedStatement prestm= conn.prepareStatement(query1);
            prestm.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    
    public int delete(String maHD){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update HOADON set STATUS= 1 where MAHD= ?";
            row= get.prepareUpdate(query, maHD);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    public ArrayList<HD> findHD(String maHD, String maNV, String maKH, String phuongThuc, LocalDate bd, LocalDate kt, float tienTu, float tienDen){
        ArrayList<HD> hd= new ArrayList<>();
        try{
            String query= "Select * from HOADON where 1=1" ;
            Connection conn= get.getConnection();
            if (!phuongThuc.equals("Tat ca")) query += "and hinhthuc= '" + phuongThuc +"' ";
            if (!maHD.equals("")) query += " And maHD= '" + maHD +"' ";
            if (!maNV.equals("")) query += " and maNV= '" + maNV +"' ";
            if (!maKH.equals("")) query += " and maKH= '" +maKH + "' ";
            if (bd != null) query += " AND CAST(ngaylap AS DATE)>= '" + bd.toString() + "' ";
            if (kt != null) query += " AND CAST(ngaylap AS DATE)<= '" + kt.toString() + "' ";
            if (tienTu != 0) query += " AND tongtien >= " + tienTu + " ";
            if (tienDen != 0) query += " AND tongtien <= " + tienDen + " ";
            PreparedStatement prestm= conn.prepareCall(query);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maHD1= rs.getString("MAHD");
                String maNV1= rs.getString("MANV");
                String maKH1= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                HD temp= new HD(maHD1, maNV1, maKH1, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                hd.add(temp);
               
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public int editHD(String maHD, String maKH, String maNV, LocalDate ngayLap, String phuongThuc){
        String query= "Update HOadon set maNV=?, maKH=?, ngaylap=?, hinhthuc=? where maHD=?";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, maNV, maKH, ngayLap, phuongThuc, maHD);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public int addHD(HD hd){
        String query= "Insert into HOADON values (?, ?, ?, ? ,?, ?, ?, ?, ?, 0)";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, hd.getMaHD(), hd.getMaNV(), hd.getMaKH(), hd.getNgayLap(), hd.getTongTien(), hd.getTongSL(), hd.getPhuongThuc(), hd.getThanhTien(), hd.getTongGG());
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
}
