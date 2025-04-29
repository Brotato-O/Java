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
        bottom.setLayout(new BorderLayout());
        Border bd= BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder ttBd= new TitledBorder("Thông tin hóa đơn");
        ttBd.setTitleColor(Color.GREEN);
        ttBd.setTitleJustification(TitledBorder.CENTER);
        ttBd.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBd);
        bottom.setBorder(bd);
        
        bottom.add(left(), BorderLayout.WEST);
        bottom.add(right());
        
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
        JPanel out= new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
        JPanel img= new JPanel();
        img.setSize(300, 200);
        img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel[] input= new JPanel[5];
        out.add(img);
        ImageIcon icon= new ImageIcon(getClass().getResource("../../img/search.png"));
        Image img1= icon.getImage();
        img1= img1.getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);
        JLabel label = new JLabel(new ImageIcon(img1));
        img.add(label);
        for(int i=0; i< 5; i++){
            input[i]= new JPanel();
            input[i].setLayout(new GridLayout(1, 2));
            out.add(input[i]);
        }
        JPanel temp= new JPanel();
        temp.add(maSach);
        temp.add(ctMaSach);
        input[0].add(new JLabel("Mã sách"));
        input[0].add(temp);
        input[1].add(new JLabel("Tên sách"));
        input[1].add(tenSach);
        input[2].add(new JLabel("Số lượng"));
        input[2].add(soLg);
        input[3].add(new JLabel("Đơn giá"));
        input[3].add(donGia);
        input[4].add(new JLabel("Mã GG"));
        input[4].add(maGG);
        out.add(them);
        
        p.add(out);
        return p;
    }
    
    public JPanel right(){
        JPanel p= new JPanel();
        p.setLayout(new BorderLayout());
        
        DefaultTableModel model= (DefaultTableModel) table.getModel();
        String[] column= new String[]{"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"};
        model.setColumnIdentifiers(column);
        model.setRowCount(20);
        JScrollPane pane= new JScrollPane(table);
        p.add(pane);
        
        JPanel bottom= new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        JPanel up= new JPanel();
        up.setLayout(new GridLayout(1, 6));
        up.add(new JLabel("Tổng tiền"));
        up.add(tongTien);
        up.add(new JLabel("Giảm giá"));
        up.add(giamGia);
        up.add(new JLabel("Thành tiền"));
        up.add(thanhTien);
        bottom.add(up);
        JPanel down= new JPanel();
        down.setLayout(new GridLayout(1, 4));
        down.add(xacNhan);
        down.add(huy);
        down.add(sua);
        down.add(xoa);
        bottom.add(down);
        p.add(bottom, BorderLayout.SOUTH);
        
        return p;
    }
}
