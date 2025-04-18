/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.dialog.QLHD;

import GUI.controller.QLHDController;
import GUI.view.QLHD;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class addQLHD extends JDialog{
    private JButton yes= new JButton("Xác nhận");
    private JButton no= new JButton("Hủy");
    
    public JTextField ctMaHD= new JTextField();
    public JTextField ctMaSach= new JTextField();
    public JTextField ctSolg= new JTextField();
    public JTextField ctTongtien= new JTextField();
    public JTextField ctGiamGia= new JTextField();
    public JTextField ctDonGia= new JTextField();
    public JTextField ctThanhTien= new JTextField();
    
    QLHD view;
    QLHDController controller;
    
    public addQLHD(JFrame parent, QLHD qlhd){
        super(parent, "THÊM CHI TIẾT HÓA ĐƠN", true);
        this.view= qlhd;
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
        setVisible(true);
        
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
        for (int i=0; i< 7; i++){
            inp[i]= new JPanel();
            inpPanel[i]= new JPanel();
            inpPanel[i].setLayout(new BoxLayout(inpPanel[i], BoxLayout.Y_AXIS));
            leftTop.add(inp[i]);
            rightTop.add(inpPanel[i]);
        }
        inpPanel[0].add(ctMaHD);
        inpPanel[1].add(ctMaSach);
        inpPanel[2].add(ctSolg);
        inpPanel[3].add(ctDonGia);
        inpPanel[4].add(ctTongtien);
        inpPanel[5].add(ctGiamGia);
        inpPanel[6].add(ctThanhTien);
        inp[0].add(new JLabel("Mã HD"), BorderLayout.WEST);
        inp[1].add(new JLabel("Mã sách"), BorderLayout.WEST);
        inp[2].add(new JLabel("Số lượng"), BorderLayout.WEST);
        inp[3].add(new JLabel("Đơn giá"), BorderLayout.WEST);
        inp[4].add(new JLabel("Tổng tiền"), BorderLayout.WEST);
        inp[5].add(new JLabel("Giảm giá"), BorderLayout.WEST);
        inp[6].add(new JLabel("Thành tiền"), BorderLayout.WEST);
        top.add(leftTop, BorderLayout.WEST);
        top.add(rightTop);
        
        yes.setPreferredSize(new Dimension(100, 40));
        no.setPreferredSize(new Dimension(100, 40));
        no.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        return top;
    }
}
