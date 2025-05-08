/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CTPN;
import DTO.PN;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class PNDAL {
    getConnection get= new getConnection();
    
    public ArrayList<PN> selectAll(){
        ArrayList<PN> hd= new ArrayList<>();
        try{
            Connection conn= get.getConnection();
            String query= "Select * from PHIEUNHAP where STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maPN= rs.getString("MAPN");
                String maNV= rs.getString("MANV");
                String maNCC= rs.getString("MANCC");
                String ngayLap= rs.getString("NGAYNHAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                
                PN temp= new PN(maPN, maNV, maNCC, ngayLap, tongTien, tongSL);
                hd.add(temp);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public PN selectById(String id){
        PN hd= null;
        try{
            Connection conn= get.getConnection();
            String query= "Select * from PHIEUNHAP where MAPN= ? and STATUS= 0";
            PreparedStatement prestm= conn.prepareStatement(query);
            prestm.setString(1, id);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maPN= rs.getString("MAPN");
                String maNV= rs.getString("MANV");
                String maNCC= rs.getString("MANCC");
                String ngayLap= rs.getString("NGAYNHAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                
                hd= new PN(maPN, maNV, maNCC, ngayLap, tongTien, tongSL);
                
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return hd;
    }
    
    //chua biet lam gì nen de day
    public int updateTongTien(String maPN, float thanhTien){
        
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update PHIEUNHAP set TONGTIEN= TONGTIEN - ? where maPN=?";
            PreparedStatement prestm= conn.prepareStatement(query);
            row= get.prepareUpdate(query, maPN, thanhTien);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("row" + row);
        return row;
    }
    
    public int updateTongTien(CTPN ctpn){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update PHIEUNHAP set TONGTIEN= TONGTIEN - ?, TONGSOLG= TONGSOLG-? where maPN=?";
            row= get.prepareUpdate(query, ctpn.getThanhTien(), ctpn.getSoLg(), ctpn.getMaPN());
            
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    public int updateTongTienAdd(CTPN ctpn){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update PHIEUNHAP set TONGTIEN= TONGTIEN + ?, TONGSOLG= TONGSOLG+? where maPN=?";
            row= get.prepareUpdate(query, ctpn.getThanhTien(), ctpn.getSoLg(), ctpn.getMaPN());
            
            
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    
    public int delete(String maPN){
        int row= 0;
        try{
            Connection conn= get.getConnection();
            String query= "Update PHIEUNHAP set STATUS= 1 where MAPN= ?";
            row= get.prepareUpdate(query, maPN);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return row;
    }
    public ArrayList<PN> findPN(String maPN, String maNV, String maNCC, LocalDate bd, LocalDate kt, float tienTu, float tienDen){
        ArrayList<PN> hd= new ArrayList<>();
        try{
            String query= "Select * from PHIEUNHAP where 1=1" ;
            Connection conn= get.getConnection();
            if (!maPN.equals("")) query += " And maPN= '" + maPN +"' ";
            if (!maNV.equals("")) query += " and maNV= '" + maNV +"' ";
            if (!maNCC.equals("")) query += " and maNCC= '" +maNCC + "' ";
            if (bd != null) query += " AND CAST(ngaynhap AS DATE)>= '" + bd.toString() + "' ";
            if (kt != null) query += " AND CAST(ngaynhap AS DATE)<= '" + kt.toString() + "' ";
            if (tienTu != 0) query += " AND tongtien >= " + tienTu + " ";
            if (tienDen != 0) query += " AND tongtien <= " + tienDen + " ";
            PreparedStatement prestm= conn.prepareCall(query);
            ResultSet rs= prestm.executeQuery();
            while (rs.next()){
                String maPN1= rs.getString("MAPN");
                String maNV1= rs.getString("MANV");
                String maNCC1= rs.getString("MANCC");
                String ngayLap= rs.getString("NGAYNHAP");
                float tongTien= rs.getFloat("TONGTIEN");
                int tongSL= rs.getInt("TONGSOLG");
                
                PN temp= new PN(maPN1, maNV1, maNCC1, ngayLap, tongTien, tongSL);
                hd.add(temp);
               
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
    
    public int editPN(String maPN, String maNCC, String maNV, LocalDate ngayLap){
        String query= "Update PHIeunhap set maNV=?, maNCC=?, ngaynhap=? where maPN=?";
        int rs= 0;
        try{
            Connection conn= get.getConnection();
            rs= get.prepareUpdate(query, maNV, maNCC, ngayLap, maPN);
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
    public int addPN(PN pn){
        String query = "INSERT INTO PHIeunhap (maPN, maNV, maNCC, ngayNhap, tongSoLg, tongTien, status) VALUES (?, ?, ?, ?, ?, ?, 0)";
        int rs = 0;
        try{
            Connection conn = get.getConnection();
            rs = get.prepareUpdate(query, 
                    pn.getMaPN(), 
                    pn.getMaNV(), 
                    pn.getMaNCC(), 
                    pn.getNgayNhap(), 
                    pn.getTongSL(), 
                    pn.getTongTien()
            );
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }

}
