/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.dialog.QLPN;

import GUI.controller.QLPNController;
import GUI.view.QLPN;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class addPN extends JDialog{
    public JButton yes= new JButton("Xác nhận");
    private JButton no= new JButton("Hủy");
    
    public JTextField maPN= new JTextField();
    public JTextField maNCC= new JTextField();
    public JTextField maNV= new JTextField();
    public JTextField ngayNhap= new JTextField();
    public JTextField tongTien= new JTextField();
    public JTextField tongSolg= new JTextField();
    
    public JTable tableSach= new JTable();
    public JButton xacNhan= new JButton("Xác nhận");
    
    QLPN view;
    QLPNController controller;
    
    public addPN(JFrame parent, QLPN qlhd){
        super(parent, "THÊM PHIẾU NHẬP");
        this.view= qlhd;
        controller= new QLPNController(view);
        JPanel top= new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(addPanel());
        JPanel temp= new JPanel();
        temp.add(yes);
        temp.add(no);
        top.add(temp);
        add(top);
        
        setSize(300, 300);
        setLocationRelativeTo(parent);
        setResizable(false);
    }
    
    public JPanel addPanel(){
        JPanel top= new JPanel();
        
        JPanel[] inp= new JPanel[8];
        JPanel[] inpPanel= new JPanel[8];
        JPanel leftTop= new JPanel();
        leftTop.setLayout(new BoxLayout(leftTop, BoxLayout.Y_AXIS));

        JPanel rightTop= new JPanel();
        rightTop.setLayout(new BoxLayout(rightTop, BoxLayout.Y_AXIS));
        
        top.setLayout(new BorderLayout());
        for (int i=0; i< 6; i++){
            inp[i]= new JPanel();
            inpPanel[i]= new JPanel();
            inpPanel[i].setLayout(new BoxLayout(inpPanel[i], BoxLayout.Y_AXIS));
            leftTop.add(inp[i]);
            rightTop.add(inpPanel[i]);
        }
        JPanel temp= new JPanel();
        inpPanel[0].add(maPN);
        inpPanel[1].add(maNV);
        inpPanel[2].add(maNCC);
        inpPanel[3].add(ngayNhap);
        inpPanel[4].add(tongTien);
        inpPanel[5].add(tongSolg);
        inp[0].add(new JLabel("Mã PN"), BorderLayout.WEST);
        inp[1].add(new JLabel("Mã NV"), BorderLayout.WEST);
        inp[2].add(new JLabel("Mã NCC"), BorderLayout.WEST);
        inp[3].add(new JLabel("Ngày nhập"), BorderLayout.WEST);
        inp[4].add(new JLabel("Tổng tiền"), BorderLayout.WEST);
        inp[5].add(new JLabel("Tổng số lượng"), BorderLayout.WEST);
        top.add(leftTop, BorderLayout.WEST);
        top.add(rightTop);
        tongSolg.setText("0");
        tongTien.setText("0");
        
        yes.setPreferredSize(new Dimension(100, 40));
        no.setPreferredSize(new Dimension(100, 40));
        no.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
//        ctMaPN.setEditable(false);
        tongTien.setEditable(false);
        tongSolg.setEditable(false);
        return top;
    }
    
    
}
