package GUI.dialog.QLHD;
import GUI.controller.QLHDController;
import GUI.view.QLHD;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class deleteQLHD extends JDialog {
    private JButton yes= new JButton("Xác nhận");
    private JButton no= new JButton("Hủy");
    QLHD view;
    QLHDController controller;
    
    public deleteQLHD(JFrame parent, QLHD qlhd){
        super(parent, "Xác nhận xóa", true);
        this.view= qlhd;
        JPanel temp= new JPanel();
        temp.add(yes);
        temp.add(no);
        add(temp);
        setLocationRelativeTo(parent);
        setSize(200, 100);
        event();
        setVisible(true);
        
    }
    
    public void event(){
        controller= new QLHDController(view);
        yes.addActionListener(controller.deleteCTHD);
    }
}
