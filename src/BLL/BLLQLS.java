package BLL;

import DTO.Book;
import DTO.map;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

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
    public ArrayList<String> getAllTl(){
        ArrayList<String> listBook = dal.getAllTL();
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
}
