/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class getConnection {
    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBS;encrypt=true;trustServerCertificate=true";
    String pass= "admin123456";
    String user= "sa";
    Connection conn;
    
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB: " + e.getMessage());
            return null;
        }
    }
    
    public int prepareUpdate(String sql, Object...params) {
        try {
            conn= getConnection();
            PreparedStatement prestm= conn.prepareStatement(sql);
            for(int i=0;i<params.length;i++){
                prestm.setObject(i+1, params[i]);
            }
            return prestm.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
