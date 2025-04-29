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
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ADMIN
 */
public class QLBH extends JPanel{
    Dimension d= new Dimension(100, 25);
    public JTextField maHD= new JTextField();
    public JTextField maNV= new JTextField();
    public JButton ctMaNV= new JButton("...");
    public JTextField maKH= new JTextField();
    public JButton ctMaKH= new JButton("...");
    public JTextField ngayLap= new JTextField();
    public JButton taoHD= new JButton("TẠO HÓA ĐƠN");
    
    public JTextField maSach= new JTextField();
    public JButton ctMaSach= new JButton("...");
    public JTextField tenSach= new JTextField();
    public JTextField soLg= new JTextField();
    public JTextField donGia= new JTextField();
    public JTextField maGG= new JTextField();
    public JButton them= new JButton("Thêm");
    
    public JButton xacNhan= new JButton("Xác nhận");
    public JButton huy= new JButton("Hủy");
    public JTable table=new JTable();
    public JTextField thanhTien= new JTextField();
    public JTextField giamGia= new JTextField();
    public JTextField tongTien= new JTextField();
    public JButton sua= new JButton("Sửa");
    public JButton xoa= new JButton("Xóa");
    
    public QLBH(){
        setLayout(new BorderLayout());
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("QUẢN LÝ BÁN HÀNG"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        
        container.setLayout(new BorderLayout());
        container.add(HD(), BorderLayout.NORTH);
        
        JPanel bottom= new JPanel();
        Border bd= BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder ttBd= new TitledBorder("Thông tin hóa đơn");
        ttBd.setTitleColor(Color.GREEN);
        ttBd.setTitleJustification(TitledBorder.CENTER);
        ttBd.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBd);
        bottom.setBorder(bd);
        
        container.add(bottom, BorderLayout.CENTER);
        add(container, BorderLayout.CENTER);
    }
    
    public JPanel HD(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JPanel top= new JPanel();
        top.setLayout(new GridLayout(1, 3));
        JPanel[] left= new JPanel[4];
        for (int i=0; i< 4; i++){
            left[i]= new JPanel();
            left[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
        }
        left[0].add(new JLabel("Mã HD"));
        left[1].add(new JLabel("Mã KH"));
        left[2].add(new JLabel("Mã NV"));
        left[3].add(new JLabel("Ngày lập"));
        
        JPanel[] container= new JPanel[4];
        for (int i=0; i< 4; i++){
            container[i]= new JPanel();
            container[i].setLayout(new GridLayout(1, 2));
        }
        JPanel[] right= new JPanel[2];
        for (int i=0; i< 2; i++)
            right[i]= new JPanel();
        right[0].add(maNV);
        right[0].add(ctMaNV);
        right[1].add(maKH);
        right[1].add(ctMaKH);
        
        for(int i=0; i< 3; i++)
            top.add(container[i]);
        
        container[0].add(left[0]);
        container[0].add(maHD);
        container[1].add(left[1]);
        container[1].add(right[0]);
        container[2].add(left[2]);
        container[2].add(right[1]);
        
        container[3].add(left[3]);
        container[3].add(ngayLap);
        
        JPanel bottom= new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        bottom.add(container[3]);
        bottom.add(taoHD);
        
        JPanel temp= new JPanel();
        temp.add(new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------"));
        temp.setLayout(new FlowLayout());
        
        p.add(top);
        p.add(bottom);
        p.add(temp);
        
        return p;
    }
    
    public JPanel left(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JPanel img= new JPanel();
        img.setSize(300, 200);
        img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel[] input= new JPanel[4];
        for(int i=0; i< 4; i++){
            input[i]= new JPanel();
            input[i].setLayout(new GridLayout(1, 2));
        }
        
        return p;
    }
}
