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
    public ArrayList<CTHD> getCTHDDAL(){
        return cthd.selectAll();
    }
    public ArrayList<CTHD> getCTHĐALById(String id){
        return cthd.selectById(id);
    }
}
