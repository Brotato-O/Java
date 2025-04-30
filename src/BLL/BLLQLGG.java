package BLL;

import java.util.ArrayList;
import DAL.DALGG;
import DTO.CTGG;
import DTO.GG;

public class BLLQLGG {
     private DALGG dal =  new DALGG();
    
    public ArrayList<GG> getAllGG(){
        ArrayList<GG> listGG = dal.getAllGG();
        return listGG;
    }
    public boolean addGG(GG GG){
        if(dal.addGG(GG)){
            return true;
        }
        return false;
    }
    public boolean updateGG(GG GG){
        if(dal.updateGG(GG)){
            return true;
        }
        return false;
    }
    public boolean deleteGGSQL(GG GG){
        if(dal.deleteGGSQL(GG)){
            return true;
        }
        return false;
    }
    public ArrayList<CTGG> getAllCTGG(String id){
        ArrayList<CTGG> listCTGG = dal.seleCtgg(id);
        return listCTGG;
    }
    public ArrayList<GG> getAllGGByBook(String id){
        ArrayList<GG> listGG = dal.selectGGByBook(id);
        return listGG;
    }
    public boolean addCTGG(CTGG ctgg){
        if(dal.addCTGG(ctgg)){
            return true;
        }
        return false;
    }
    public boolean deleteCTGGSQL(CTGG ctgg){
        if(dal.deleteCTGGSQL(ctgg)){
            return true;
        }
        return false;
    }
}
