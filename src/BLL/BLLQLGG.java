package BLL;

import java.util.ArrayList;

import DAL.DALGG;

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
}
