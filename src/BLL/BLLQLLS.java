package BLL;

import java.util.ArrayList;
import DAL.DALQLLS;
import DTO.*;

public class BLLQLLS {
    private DALQLLS dal = new DALQLLS();
    public ArrayList <LoaiSach> getAllLoai(){
        ArrayList<LoaiSach> list = dal.getAllLoai();
        return list;
    }
    public boolean addLoai(LoaiSach book){
        if(dal.addLS(book)){
            return true;
        }
        return false;
    }
    public boolean updateLoai(LoaiSach book){
        if(dal.updateLS(book)){
            return true;
        }
        return false;
    }
    public boolean deleteLSSQL(LoaiSach book){
        if(dal.deleteLSSQL(book)){
            return true;
        }
        return false;
    }
    public ArrayList<Book> getAllBook(String id){
        ArrayList<Book> listBook = dal.selectSach(id);
        return listBook;
    }
}
