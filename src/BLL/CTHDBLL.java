/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CTHDDAL;
import DTO.CTHD;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTHDBLL {
    CTHDDAL cthd= new CTHDDAL();
    public ArrayList<CTHD> selectAll(){
        return cthd.selectAll();
    }
    public ArrayList<CTHD> selectById(String id){
        return cthd.selectById(id);
    }
    public ArrayList<CTHD> selectById(String id, String maSach){
        return cthd.selectById(id, maSach);
    }
    public int delete(String maHD, String maSach){
        return cthd.delete(maHD, maSach);
    }
}
