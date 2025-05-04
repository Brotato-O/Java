package GUI.view;
import BLL.CTPNBLL;
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

import DTO.PN;
import DTO.CTPN;
import BLL.PNBLL;
import GUI.controller.QLPNController;
import java.util.ArrayList;
import main.main;

public class QLPN extends JPanel {
    Dimension d= new Dimension(Integer.MAX_VALUE, 25);
    int height= main.height;
    int width= main.width;
    SupermarketUI sp= new SupermarketUI();
    public JFrame frame = sp.getSuperMarketUI();
    public JTextField maPN= new JTextField();
    public JTextField maNV= new JTextField();
    public JTextField maNCC= new JTextField();
    public JTextField ngayLap = new JTextField();
    public JTextField tongTien= new JTextField();
    public JTextField tongSL= new JTextField();
    public JButton xoa= new JButton("XÓA");
    public JButton sua= new JButton("SỬA");
    public JButton xuat= new JButton("THÊM");
    public JButton lamMoi= new JButton("LÀM MỚI");
    
    public JTextField timMaPN= new JTextField();
    public JTextField timMaNV= new JTextField();
    public JTextField timMaNCC= new JTextField();
    public JTextField tgianBD= new JTextField();
    public JTextField tgianKT= new JTextField();
    public JTextField tienBD= new JTextField();
    public JTextField tienKT= new JTextField();
    public JButton tim= new JButton();
    
    public JTextField tongPN= new JTextField();
    public JTextField tongTienPN= new JTextField();
    private JTable tablePN= new JTable();
    
    
    public JTextField ctTimMaPN= new JTextField();
    public JTextField ctTimMaSach = new JTextField();
    public JTextField ctTimTongSlBD = new JTextField();
    public JTextField ctTimTongSlKT = new JTextField();
    public JTextField ctTimTongTienBD = new JTextField();
    public JTextField ctTimTongTienKT = new JTextField();
    public JTextField ctTimGGBD = new JTextField();
    public JTextField ctTimGGKT= new JTextField();
//    public JButton timCTPN= new JButton("TÌM KIẾM");
    
    public JTable tableCTPN= new JTable();
    public JButton ctTong= new JButton("LÀM MỚI");
    
    public JButton suaCTPN= new JButton("SỬA");
    public JButton xoaCTPN= new JButton("XÓA");
    public JButton themCTPN= new JButton("THÊM");
    public QLPNController controller;
    
    
    public QLPN(){
        setLayout(new BorderLayout());
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("QUẢN LÝ HÓA ĐƠN"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(inputFields());
        container.add(findFields());
        container.add(PN());
        container.add(CTPN());
        add(container, BorderLayout.CENTER);
        addEvent();
    }
    
    public JPanel inputFields(){
        JPanel p= new JPanel();
        p.setLayout(new GridLayout(1, 4));
        p.setPreferredSize(new Dimension(0, (int)(0.2*height)));
        
        JPanel[] c= new JPanel[4];
        JPanel[] r= new JPanel[6];
        JPanel[] t= new JPanel[6];
        JPanel[] t1= new JPanel[6];
        for (int i=0; i< 4; i++){
            c[i]= new JPanel();
            c[i].setLayout(new BoxLayout(c[i], BoxLayout.Y_AXIS));
        }
        for (int i=0; i< 6; i++){
            r[i]= new JPanel(new GridLayout(1, 2));
        }
        for (int i=0; i< 6; i++){
            t[i]= new JPanel();
            t[i].setLayout(new BoxLayout(t[i], BoxLayout.Y_AXIS));
        }
        for (int i=0; i< 6; i++){
            t1[i]= new JPanel();
            t1[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
        }
        t1[0].add(new JLabel("Mã PN"));
        t1[1].add(new JLabel("Mã NV"));
        t1[2].add(new JLabel("Mã NCC"));
        t1[3].add(new JLabel("ngày lập"));
        t1[4].add(new JLabel("Tổng thành tiền"));
        t1[5].add(new JLabel("Tổng số lượng"));
        
        t[0].add(maPN);
        t[1].add(maNV);
        t[2].add(maNCC);
        t[3].add(ngayLap);
        t[4].add(tongTien);
        t[5].add(tongSL);
        
        for (int i=0; i< 6; i++){
            r[i].add(t1[i]);
            r[i].add(t[i]);
        }
        for (int i=0; i< 3; i++){
            c[i].add(r[i*2]);
            c[i].add(r[i*2+1]);
        }
        
        JPanel[] pBtn= new JPanel[4];
        for (int i=0; i< 4; i++){
            pBtn[i]= new JPanel(new BorderLayout());
            pBtn[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        }
        c[3].setLayout(new GridLayout(2, 2));
        pBtn[0].add(xoa);
        pBtn[1].add(xuat);
        pBtn[2].add(sua);
        pBtn[3].add(lamMoi);
        for (int i=0; i< 4; i++){
            if (i< 4) c[3].add(pBtn[i]);
            p.add(c[i]);
        }
        System.out.println((int)(0.7*r[0].getSize().getWidth()));
        maPN.setMaximumSize(d);
        maNV.setMaximumSize(d);
        maNCC.setMaximumSize(d);
        ngayLap.setMaximumSize(d);
        tongSL.setMaximumSize(d);
        tongTien.setMaximumSize(d);
        
        xoa.setBackground(Color.red);
        xoa.setForeground(Color.white);
        xuat.setBackground(Color.CYAN);
//        xuat.setForeground(Color.white);
        sua.setBackground(Color.orange);
        lamMoi.setBackground(Color.GREEN);
        
        maPN.setEditable(false);
        tongTien.setEditable(false);
        tongSL.setEditable(false);
        return p;
    }
    
    public JPanel findFields(){
        JPanel p= new JPanel();
        p.setLayout(new BorderLayout());
        p.setPreferredSize(new Dimension(0, (int)(0.2*height)));
        JPanel center= new JPanel();
        center.setLayout(new GridLayout(2, 4));
        center.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JPanel[] t= new JPanel[8];
        JPanel[] c1= new JPanel[8];
        JPanel[] c2= new JPanel[8];
        for (int i=0; i< 8; i++)
            t[i]= new JPanel(new GridLayout(1,2));
        for (int i=0; i< 8; i++){
            c1[i]= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        }
        for (int i=0; i< 8; i++){
            c2[i]= new JPanel();
            c2[i].setLayout(new BoxLayout(c2[i], BoxLayout.Y_AXIS));
        }
        c1[0].add(new JLabel("Mã PN"));
        c2[0].add(timMaPN);
        c1[1].add(new JLabel("Mã NV"));
        c2[1].add(timMaNV);
        c1[2].add(new JLabel("Mã NCC"));
        c2[2].add(timMaNCC);
        c1[3].add(new JPanel());
        c2[3].add(new JPanel());
        c1[4].add(new JLabel("Thời gian từ"));
        c2[4].add(tgianBD);
        c1[5].add(new JLabel("Thời gian đến"));
        c2[5].add(tgianKT);
        c1[6].add(new JLabel("Giá tiền từ"));
        c2[6].add(tienBD);
        c1[7].add(new JLabel("Giá tiền đến"));
        c2[7].add(tienKT);
        for (int i=0; i< 8; i++){
            t[i].add(c1[i]);
            t[i].add(c2[i]);
            center.add(t[i]);
        }
        p.add(center, BorderLayout.CENTER);
        
        ImageIcon icon= new ImageIcon(getClass().getResource("../../img/search.png"));
        Image img= icon.getImage();
        img= img.getScaledInstance(35, 35, Image.SCALE_AREA_AVERAGING);
        tim.setIcon(new ImageIcon(img));
        tim.setBackground(Color.GRAY);
        p.add(tim, BorderLayout.EAST);
        
        Border thickBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK); //ko biết là gì nhưng sẽ ôn sau
        Border bd= BorderFactory.createLineBorder(Color.BLACK,5);
        TitledBorder ttBD= BorderFactory.createTitledBorder(thickBorder, "TÌM KIẾM PHIẾU NHẬP");
        ttBD.setTitleJustification(TitledBorder.CENTER);
        ttBD.setTitleColor(Color.BLUE);
        ttBD.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBD);
        p.setBorder(bd);
        
        timMaPN.setMaximumSize(d);
        timMaNCC.setMaximumSize(d);
        timMaNV.setMaximumSize(d);
        tgianBD.setMaximumSize(d);
        tgianKT.setMaximumSize(d);
        tienBD.setMaximumSize(d);
        tienKT.setMaximumSize(d);
//        p1.add(p);
        return p;
    }
    
    public JPanel PN(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        String[] colums= {"Mã PN", "Mã NV", "Mã NCC", "Ngày lập", "Tổng tiền", "Tổng SL"};
        DefaultTableModel modelPN= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelPN.setColumnIdentifiers(colums);
        tablePN.setModel(modelPN);
        setTable();
        JPanel pTable= new JPanel(new BorderLayout());
        JScrollPane p1= new JScrollPane(tablePN);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        
        p.add(pTable);
        tongPN.setMaximumSize(new Dimension(125, 25));
        tongTienPN.setMaximumSize(new Dimension(125, 25));
        return p;
    }
    
    public JPanel CTPN(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        Border thickBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK); //ko biết là gì nhưng sẽ ôn sau
        TitledBorder ttbd= BorderFactory.createTitledBorder(thickBorder, "CHI TIẾT HÓA ĐƠN");
        ttbd.setTitleJustification(TitledBorder.CENTER);
        ttbd.setTitleColor(Color.magenta);
        ttbd.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        Border bd= BorderFactory.createLineBorder(Color.BLACK);
        bd= BorderFactory.createTitledBorder(ttbd);
        
        JPanel temp1= new JPanel(new GridLayout(1, 4, 10, 0));
        temp1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        temp1.add(themCTPN);
        temp1.add(suaCTPN);
        temp1.add(xoaCTPN);
//        temp1.add(timCTPN);
        temp1.add(ctTong);
        temp1.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        
        JPanel temp2 = new JPanel(new BorderLayout());
        String[] colums= {"Mã PN", "Mã sách", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel modelCTPN= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelCTPN.setColumnIdentifiers(colums);
        tableCTPN.setModel(modelCTPN);
        setTableCTPN();
        JScrollPane pane= new JScrollPane(tableCTPN);
        JPanel pTable= new JPanel(new BorderLayout());
        pTable.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        pTable.add(pane);
        temp2.add(pTable);
    
        themCTPN.setPreferredSize(new Dimension(50, 40));
        themCTPN.setBackground(Color.CYAN);
        xoaCTPN.setBackground(Color.red);
        suaCTPN.setBackground(Color.orange);
        ctTong.setBackground(Color.green);
        p.add(temp1);
        p.add(temp2);
        p.setBorder(bd);
        return p;
    }
    
    
    public void addEvent(){
        controller = new QLPNController(this);
        tablePN.addMouseListener(controller.cthdAdapter);
        ctTong.addActionListener(controller.showCTPN);
        xoaCTPN.addActionListener(controller.confirmDelete);
        themCTPN.addActionListener(controller.addCTPN);
        tim.addActionListener(controller.findPN);
        lamMoi.addActionListener(controller.showPN);
        xoa.addActionListener(controller.deletePN);
        sua.addActionListener(controller.editPN);
//        ctMaNV.addActionListener(controller.ctNV);
//        ctMaKH.addActionListener(controller.ctSach);
        suaCTPN.addActionListener(controller.editCTPN);
        xuat.addActionListener(controller.themPN);
    }
   
    public void setTable(){
        PNBLL pnbll= new PNBLL();
        ArrayList<PN> pn= pnbll.selectAll();
        DefaultTableModel modelPN= (DefaultTableModel) tablePN.getModel();
        modelPN.setRowCount(0);
        for (int i=0; i< pn.size(); i++){
            PN item= pn.get(i);
            Object[] row= new Object[] {
                item.getMaPN(), 
                item.getMaNV(), 
                item.getMaNCC(), 
                item.getNgayNhap(), 
                item.getTongTien(), 
                item.getTongSL()
            };
            modelPN.addRow(row);
        }
    }
    
    public void setTableCTPN(){
        CTPNBLL ctpnbll= new CTPNBLL();
        ArrayList<CTPN> ctpn= ctpnbll.selectAll();
        DefaultTableModel modelCTPN= (DefaultTableModel) tableCTPN.getModel();
        modelCTPN.setRowCount(0);
        for (int i=0; i< ctpn.size(); i++){
            CTPN item= ctpn.get(i);
            Object[] row= new Object[]{
                item.getMaPN(), 
                item.getMaSach(), 
                item.getSoLg(), 
                item.getDonGia(),
                item.getThanhTien()};
            modelCTPN.addRow(row);
        }
    }
    
    public JTable getTablePN() {
    return tablePN;
}

    public JTable getTableCTPN() {
        return tableCTPN;
    }
    
}