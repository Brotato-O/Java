package GUI.controller;
import GUI.view.QLHD;
import javax.swing.*;

public class QLHDController {
    private QLHD qlhd;

    public QLHDController(QLHD qlhd){
        this.qlhd= qlhd;
    }

    public int isSelectedRow(){
        JTable tableHD = qlhd.getTableHD();
        int row = tableHD.getSelectedRow();
        if (row >= 0) return row;
        return -1;
    }
}
