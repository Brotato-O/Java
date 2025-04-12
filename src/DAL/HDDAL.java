/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.HD;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class HDDAL {
    public ArrayList<HD> selectAll(){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
        String pass= "admin123456";
        String user= "sa";
        ArrayList<HD> hd= new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công!");
            String query= "Select * from HOADON";
            PreparedStatement prestm= conn.prepareStatement(query);
            ResultSet rs= prestm.executeQuery();
            while(rs.next()){
                String maHD= rs.getString("MAHD");
                String maNV= rs.getString("MANV");
                String maKH= rs.getString("MAKH");
                String ngayLap= rs.getString("NGAYLAP");
                float tongTien= rs.getFloat("TONGTIEN");
                float tongSL= rs.getFloat("TONGSOLG");
                String hinhThuc= rs.getString("HINHTHUC");
                float thanhTien= rs.getFloat("THANHTIEN");
                float tongGG= rs.getFloat("TONGGG");
                
                HD temp= new HD(maHD, maNV, maKH, ngayLap, hinhThuc, tongGG, tongTien, tongSL, thanhTien);
                hd.add(temp);
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hd;
    }
}
