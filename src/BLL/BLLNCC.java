package BLL;

import java.util.ArrayList;

import DAL.DALNCC;
import DTO.NCC;

public class BLLNCC {
    private DALNCC dalncc = new DALNCC();
    public ArrayList<NCC> getAllNCC() {
        return dalncc.getAllNCC();
    }
    
}
