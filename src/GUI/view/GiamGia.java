/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class GiamGia extends JPanel{
    Dimension d= new Dimension(500, 25);
    public JTextField maGG= new JTextField();
    public JTextField tenGG= new JTextField();
    public JTextField tienGG= new JTextField();
    public JTextField ngayBD= new JTextField();
    public JTextField ngayKT= new JTextField();
    public JButton themGG= new JButton("THÊM");
    public JButton suaGG= new JButton("SỬA");
    public JButton xoaGG= new JButton("XÓA");
    public JButton allGG= new JButton("TẤT CẢ");
    public JTable tableGG= new JTable();
    public DefaultTableModel modelGG= new DefaultTableModel();
    
    public JTextField timBD= new JTextField();
    public JTextField timKT= new JTextField();
    public JButton tim= new JButton();
    
    public JTable tableCTGG= new JTable();
    public DefaultTableModel modelCT= new DefaultTableModel();
    public JButton ctSach= new JButton("...");
    public JTextField ctSachInp= new JTextField();
    public JTextField ctGiam= new JTextField();
    public JButton themCT= new JButton("THÊM");
    public JButton xoaCT= new JButton("XÓA");
    
    public GiamGia(){
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.add(new JLabel("CHƯƠNG TRÌNH GIẢM GIÁ"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.red);
        container.add(GG());
        container.add(CTGG());
        add(container, BorderLayout.CENTER);
    }
    
    public JPanel GG(){
        JPanel p =new JPanel();
        p.setLayout(new BorderLayout());
        JPanel top= new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        JPanel leftContainer= new JPanel(new BorderLayout());
        JPanel left= new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(Color.red);
        JPanel input= new JPanel(new GridLayout(5, 2));
        JPanel[] pTF= new JPanel[5];
        for (int i=0; i< 5; i++){
            pTF[i]= new JPanel();
            pTF[i].setLayout(new BoxLayout(pTF[i], BoxLayout.X_AXIS));
        }
        pTF[0].add(maGG);
        pTF[1].add(tenGG);
        pTF[2].add(tienGG);
        pTF[3].add(ngayBD);
        pTF[4].add(ngayKT);
        input.add(new JLabel("MÃ GIẢM GIÁ"));
        input.add(pTF[0]);
        input.add(new JLabel("TÊN CHƯƠNG TRÌNH"));
        input.add(pTF[1]);
        input.add(new JLabel("KHUYẾN MÃI"));
        input.add(pTF[2]);
        input.add(new JLabel("NGÀY BẮT ĐẦU"));
        input.add(pTF[3]);
        input.add(new JLabel("NGÀY KẾT THÚC"));
        input.add(pTF[4]);
        JPanel pButton= new JPanel();
        pButton.setLayout(new GridLayout(1, 4));
        JPanel[] temp= new JPanel[4];
        for (int i=0; i< 4; i++){
            temp[i]= new JPanel();
            temp[i].setLayout(new BoxLayout(temp[i], BoxLayout.X_AXIS));
            temp[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
        temp[0].add(themGG);
        temp[1].add(suaGG);
        temp[2].add(xoaGG);
        temp[3].add(allGG);
        for (int i=0; i< 4; i++){
            pButton.add(temp[i]);
        }
//        pButton.add(themGG);
//        pButton.add(suaGG);
//        pButton.add(xoaGG);
//        pButton.add(allGG);
        left.add(input);
        left.add(pButton);
        leftContainer.add(left);
        top.add(leftContainer);
        top.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        
        JPanel right= new JPanel(new BorderLayout());
        String[] column= {"Mã GG", "Tên chương trình", "Số tiền giảm", "Ngày bắt đầu", "Ngày kết thúc"};
        modelGG.setColumnIdentifiers(column);
        modelGG.setRowCount(20);
        tableGG.setModel(modelGG);
        JScrollPane pane= new JScrollPane(tableGG);
        right.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
        right.add(pane);
        top.add(right);
        p.add(top);
        
        JPanel bottom= new JPanel(new GridLayout(1, 2));
        Border thickBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.ORANGE); //ko biết là gì nhưng sẽ ôn sau
        TitledBorder tt= BorderFactory.createTitledBorder(thickBorder, "TÌM KIẾM");
        tt.setTitleColor(Color.GREEN);
        tt.setTitleFont(new Font("Arial", Font.PLAIN, 18));
        tt.setTitleJustification(TitledBorder.CENTER);
        Border bd= BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0), tt);
        bottom.setBorder(bd);
        
        JPanel pBD= new JPanel();
        pBD.setLayout(new BoxLayout(pBD, BoxLayout.X_AXIS));
        pBD.add(new JLabel("Ngày bắt đầu"));
        pBD.add(Box.createHorizontalStrut(10));
        JPanel pBD1= new JPanel();
        pBD1.setLayout(new BoxLayout(pBD1, BoxLayout.X_AXIS));
        pBD1.add(timBD);
        pBD.add(pBD1);
        pBD.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel pKT= new JPanel();
        pKT.setLayout(new BoxLayout(pKT, BoxLayout.X_AXIS));
        pKT.add(new JLabel("Ngày kết thúc"));
        pKT.add(Box.createHorizontalStrut(10));
        JPanel pKT2= new JPanel();
        pKT2.setLayout(new BoxLayout(pKT2, BoxLayout.X_AXIS));
        pKT2.add(timKT);
        pKT.add(pKT2);
        pKT.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel l= new JPanel();
        l.setLayout(new BoxLayout(l, BoxLayout.X_AXIS));
        l.add(pBD);
        l.add(pKT);
        
        JPanel r= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        r.add(tim);
        ImageIcon icon= new ImageIcon(getClass().getResource("../../img/find1.png"));
        Image img= icon.getImage();
        img= img.getScaledInstance(35, 35, Image.SCALE_AREA_AVERAGING);
        icon= new ImageIcon(img);
        tim.setIcon(icon);
        bottom.add(l);
        bottom.add(r);
        p.add(bottom, BorderLayout.SOUTH);
        
        Dimension dBtn= new Dimension(Integer.MAX_VALUE, 35);
        themGG.setMaximumSize(dBtn);
        xoaGG.setMaximumSize(dBtn);
        suaGG.setMaximumSize(dBtn);
        allGG.setMaximumSize(dBtn);
        themGG.setBackground(Color.decode("#019901"));
        suaGG.setBackground(Color.BLUE);
        xoaGG.setBackground(Color.RED);
        allGG.setBackground(Color.BLACK);
        xoaGG.setForeground(Color.white);
        themGG.setForeground(Color.white);
        suaGG.setForeground(Color.white);
        allGG.setForeground(Color.white);
        maGG.setMaximumSize(d);
        tenGG.setMaximumSize(d);
        tienGG.setMaximumSize(d);
        ngayBD.setMaximumSize(d);
        ngayKT.setMaximumSize(d);
        timBD.setMaximumSize(dBtn);
        timKT.setMaximumSize(dBtn);
        
        tim.setPreferredSize(new Dimension(50, 35));
        tim.setBackground(Color.decode("#cffdce"));
        return p;
    }
    
    public JPanel CTGG(){
        JPanel p= new JPanel(new BorderLayout());
        JPanel header= new JPanel();
        header.add(new JLabel("CHI TIẾT CHƯƠNG TRÌNH GIẢM GIÁ"){{setFont(new Font("Arial", Font.BOLD, 24));}});
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BorderLayout());
        
        JPanel left= new JPanel(new BorderLayout());
        String[] column= {"Mã GG", "%Giảm", "Sách"} ;
        modelCT.setColumnIdentifiers(column);
        modelCT.setRowCount(20);
        tableCTGG.setModel(modelCT);
        JScrollPane pane= new JScrollPane(tableCTGG);
        left.add(pane);
        container.add(left);
        
        JPanel containerRight= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        containerRight.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel right= new JPanel();
        right.setLayout(new GridLayout(4, 1));
        JPanel sach= new JPanel(new FlowLayout(FlowLayout.LEFT));
        sach.add(new JLabel("Sách:"));
        JPanel temp= new JPanel();
        temp.add(ctSach);
        sach.add(temp);
        right.add(sach);
//        right.add(Box.createVerticalStrut(10));
        
        JPanel temp1= new JPanel();
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
//        temp1.setBackground(Color.green);
        temp1.add(ctSachInp);
        right.add(temp1);
        
        JPanel giam= new JPanel();
        giam.setLayout(new BoxLayout(giam, BoxLayout.X_AXIS));
        giam.add(new JLabel("% GIẢM"));
        giam.add(Box.createHorizontalStrut(10));
        JPanel temp2= new JPanel();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.X_AXIS));
        temp2.add(ctGiam);
        giam.add(temp2);
        right.add(giam);
        
        JPanel pBtn= new JPanel();
        pBtn.setLayout(new BoxLayout(pBtn, BoxLayout.X_AXIS));
        pBtn.add(themCT);
        pBtn.add(Box.createHorizontalStrut(10));
        pBtn.add(xoaCT);
        right.add(pBtn);
        containerRight.add(right);
        container.add(containerRight, BorderLayout.EAST);
        p.add(container);
        container.setBackground(Color.red);
        
        ctSachInp.setPreferredSize(new Dimension(200, 25));
//        ctSachInp.setMaximumSize(new Dimension(200, 35));
        ctGiam.setMaximumSize(new Dimension(200, 25));
        System.out.println(containerRight.getSize().getHeight());
        themCT.setMaximumSize(new Dimension(75, 35));
        themCT.setBackground(Color.decode("#019901"));
        themCT.setForeground(Color.white);
        xoaCT.setMaximumSize(new Dimension(75, 35));
        xoaCT.setBackground(Color.red);
        xoaCT.setForeground(Color.white);
        return p;
    }
}
