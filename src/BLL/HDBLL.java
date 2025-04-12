package BLL;

import DAL.HDDAL;
import DTO.HD;
import java.util.ArrayList;

public class HDBLL {
    HDDAL hd= new HDDAL();
    public ArrayList<HD> getHDBUS(){
        return hd.selectAll();
    }
}
