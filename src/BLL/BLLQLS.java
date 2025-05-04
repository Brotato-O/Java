package BLL;

import DTO.Book;
import DTO.map;

import java.util.ArrayList;


import DAL.DALQLS;
public class BLLQLS {
    private DALQLS dal =  new DALQLS();
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> listBook = dal.getAllBooks();
        return listBook;
    }
    public ArrayList<map> getAllTg(){
        ArrayList<map> listBook = dal.getAllTG();
        return listBook;
    }
    public ArrayList<map> getAllNcc(){
        ArrayList<map> listBook = dal.getAllNCC();
        return listBook;
    }
    public ArrayList<map> getAllLS(){
        ArrayList<map> listBook = dal.getAllLS();
        return listBook;
    }
    public String generateNewBookId(){
        return dal.generateNewBookId();
    }
    public boolean addBook(Book book){
        if(dal.addBook(book)){
            return true;
        }
        return false;
    }
    public boolean updateBook(Book book){
        if(dal.updateBook(book)){
            return true;
        }
        return false;
    }
    public boolean deleteBookSQL(Book book){
        if(dal.deleteBookSQL(book)){
            return true;
        }
        return false;
    }
    public boolean deleteBook(Book book){
        if(dal.deleteBook(book)){
            return true;
        }
        return false;
    }
    public boolean outputExcel(String filePath){
        if(dal.outputExcel(filePath)){
            return true;
        }
        return false;
    }
    public float getMaxPrice(){
        if (dal.getMaxPrice() >= 0 ){
            return dal.getMaxPrice();
        }
        return -1;
    }
    public float getMinPrice(){
        if (dal.getMinPrice() >= 0 ){
            return dal.getMinPrice();
        }
        return -1;
    }
    public int countBook(){
        ArrayList<Book> list = dal.getAllBooks();
        return list.size();
    }
    public int countTypeBook(){
        ArrayList<map> list = dal.getAllLS();
        return list.size();
    }
    public ArrayList<map> getoneType(){
        ArrayList<map> listBook = dal.typeBook();
        return listBook;
    }
    public ArrayList<map> getonencc(){
        ArrayList<map> listBook = dal.NXBBook();
        return listBook;
    }
    public ArrayList<map> getonetg(){
        ArrayList<map> listBook = dal.tgBook();
        return listBook;
    }
    public map getSachMuaNhieuNhat() {
        ArrayList<map> list = getonetg();
        map max = null;
        int maxSoLuong = Integer.MIN_VALUE;
    
        for (map m : list) {
            int sl = Integer.parseInt(m.getTen());
            if (sl > maxSoLuong) {
                maxSoLuong = sl;
                max = m;
            }
        }
    
        return max;
    }
    public boolean addBook1(Book book) {
        return dal.addBook1(book);
    }
}
