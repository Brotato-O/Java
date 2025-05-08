package GUI.view;
import BLL.CTHDBLL;
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

import DTO.HD;
import DTO.CTHD;
import BLL.HDBLL;
import GUI.controller.QLHDController;
import java.awt.Insets;
import java.util.ArrayList;
import main.main;

public class QLHD extends JPanel {
    Dimension d= new Dimension(Integer.MAX_VALUE, 25);
    int height= main.height;
    int width= main.width;
    SupermarketUI sp= new SupermarketUI();
    public JFrame frame = sp.getSuperMarketUI();
    public JTextField maHD= new JTextField();
    public JTextField maNV= new JTextField();
    public JButton ctMaNV= new JButton("...");
    public JTextField maKH= new JTextField();
    public JButton ctMaKH= new JButton("...");
    public JTextField ngayLap = new JTextField();
    public JComboBox phuongThuc= new JComboBox();
    public DefaultComboBoxModel modelPT= new DefaultComboBoxModel();
    public JTextField tongGG= new JTextField();
    public JTextField tongTien= new JTextField();
    public JTextField tongSL= new JTextField();
    public JButton xoa= new JButton("XÓA");
    public JButton sua= new JButton("SỬA");
    public JButton xuat= new JButton("XUẤT");
    public JButton lamMoi= new JButton("LÀM MỚI");
    
    public JTextField timMaHD= new JTextField();
    public JTextField timMaNV= new JTextField();
    public JTextField timMaKH= new JTextField();
    public JComboBox timPhuongThuc= new JComboBox();
    public DefaultComboBoxModel modelTimPT= new DefaultComboBoxModel();
    public JTextField tgianBD= new JTextField();
    public JTextField tgianKT= new JTextField();
    public JTextField tienBD= new JTextField();
    public JTextField tienKT= new JTextField();
    public JButton tim= new JButton();
    
    public JTextField tongHD= new JTextField();
    public JTextField tongTienHD= new JTextField();
    private JTable tableHD= new JTable();
    
    
    public JTextField ctTimMaHD= new JTextField();
    public JTextField ctTimMaSach = new JTextField();
    public JTextField ctTimTongSlBD = new JTextField();
    public JTextField ctTimTongSlKT = new JTextField();
    public JTextField ctTimTongTienBD = new JTextField();
    public JTextField ctTimTongTienKT = new JTextField();
    public JTextField ctTimGGBD = new JTextField();
    public JTextField ctTimGGKT= new JTextField();
//    public JButton timCTHD= new JButton("TÌM KIẾM");
    
    public JTable tableCTHD= new JTable();
    public JButton ctTong= new JButton("LÀM MỚI");
    
    public JButton suaCTHD= new JButton("SỬA");
    public JButton xoaCTHD= new JButton("XÓA");
    public JButton themCTHD= new JButton("THÊM");
    
    
    QLHDController controller;
    
    public QLHD(){
        setLayout(new BorderLayout());
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("QUẢN LÝ HÓA ĐƠN"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(inputFields());
        container.add(findFields());
        container.add(HD());
        container.add(CTHD());
        add(container, BorderLayout.CENTER);
        addEvent();
    }
    
    public JPanel inputFields(){
        JPanel p= new JPanel();
        p.setLayout(new GridLayout(1, 5));
        p.setPreferredSize(new Dimension(0, (int)(0.2*height)));
        modelPT.addElement("Tien mat");
        modelPT.addElement("Chuyen khoan");
        modelPT.addElement("Quet the");
        phuongThuc.setModel(modelPT);
        JPanel[] c= new JPanel[5];
        JPanel[] r= new JPanel[8];
        JPanel[] t= new JPanel[8];
        JPanel[] t1= new JPanel[8];
        for (int i=0; i< 5; i++){
            c[i]= new JPanel();
            c[i].setLayout(new BoxLayout(c[i], BoxLayout.Y_AXIS));
        }
        for (int i=0; i< 8; i++){
            r[i]= new JPanel(new GridLayout(1, 2));
        }
        for (int i=0; i< 8; i++){
            t[i]= new JPanel();
            t[i].setLayout(new BoxLayout(t[i], BoxLayout.Y_AXIS));
        }
        for (int i=0; i< 8; i++){
            t1[i]= new JPanel();
            t1[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
        }
        t1[0].add(new JLabel("Mã HĐ"));
        t1[1].add(new JLabel("Mã KH"));
        t1[2].add(new JLabel("Mã NV"));
        t1[3].add(new JLabel("ngày lập"));
        t1[4].add(new JLabel("Phương thức tt"));
        t1[5].add(new JLabel("Tổng thành tiền"));
        t1[6].add(new JLabel("Tổng số lượng"));
        t1[7].add(new JLabel("Tổng giảm giá"));
        
        t[0].add(maHD);
        t[1].add(maKH);
        t[2].add(maNV);
        t[3].add(ngayLap);
        t[4].add(phuongThuc);
        t[5].add(tongTien);
        t[6].add(tongSL);
        t[7].add(tongGG);
        
        for (int i=0; i< 8; i++){
            r[i].add(t1[i]);
            r[i].add(t[i]);
        }
        for (int i=0; i< 4; i++){
            c[i].add(r[i*2]);
            c[i].add(r[i*2+1]);
        }
        
        JPanel[] pBtn= new JPanel[4];
        for (int i=0; i< 4; i++){
            pBtn[i]= new JPanel(new BorderLayout());
            pBtn[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        }
        c[4].setLayout(new GridLayout(2, 2));
        pBtn[0].add(xoa);
        pBtn[1].add(xuat);
        pBtn[2].add(sua);
        pBtn[3].add(lamMoi);
        for (int i=0; i< 5; i++){
            if (i< 4) c[4].add(pBtn[i]);
            p.add(c[i]);
        }
        System.out.println((int)(0.7*r[0].getSize().getWidth()));
        maHD.setMaximumSize(d);
        maKH.setMaximumSize(d);
        maNV.setMaximumSize(d);
        phuongThuc.setMaximumSize(d);
        ngayLap.setMaximumSize(d);
        tongGG.setMaximumSize(d);
        tongSL.setMaximumSize(d);
        tongTien.setMaximumSize(d);
        
        xoa.setBackground(Color.red);
        xoa.setForeground(Color.white);
        xuat.setBackground(Color.CYAN);
//        xuat.setForeground(Color.white);
        sua.setBackground(Color.orange);
        lamMoi.setBackground(Color.GREEN);
        
        maHD.setEditable(false);
        tongTien.setEditable(false);
        tongSL.setEditable(false);
        tongGG.setEditable(false);
        return p;
    }
    
    public JPanel findFields(){
        JPanel p= new JPanel();
//        JPanel p1= new JPanel();
//        p1.setLayout(new GridLayout(1, 1));
        modelTimPT.addElement("Tat ca");
        modelTimPT.addElement("Tien mat");
        modelTimPT.addElement("Chuyen khoan");
        modelTimPT.addElement("Quet the");
        modelTimPT.setSelectedItem("Tat ca");
        timPhuongThuc.setModel(modelTimPT);
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
        c1[0].add(new JLabel("Mã HĐ"));
        c2[0].add(timMaHD);
        c1[1].add(new JLabel("Mã NV"));
        c2[1].add(timMaNV);
        c1[2].add(new JLabel("Mã KH"));
        c2[2].add(timMaKH);
        c1[3].add(new JLabel("Phương thức"));
        c2[3].add(timPhuongThuc);
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
        TitledBorder ttBD= BorderFactory.createTitledBorder(thickBorder, "TÌM KIẾM HÓA ĐƠN");
        ttBD.setTitleJustification(TitledBorder.CENTER);
        ttBD.setTitleColor(Color.BLUE);
        ttBD.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBD);
        p.setBorder(bd);
        
        timMaHD.setMaximumSize(d);
        timMaKH.setMaximumSize(d);
        timMaNV.setMaximumSize(d);
        timPhuongThuc.setMaximumSize(d);
        tgianBD.setMaximumSize(d);
        tgianKT.setMaximumSize(d);
        tienBD.setMaximumSize(d);
        tienKT.setMaximumSize(d);
//        p1.add(p);
        return p;
    }
    
    public JPanel HD(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        String[] colums= {"Mã HD", "Mã KH", "Mã NV", "Ngày lập", "Tổng tiền", "Tổng giảm giá", "Tổng SL", "Phương thức", "Thành tiền"};
        DefaultTableModel modelHD= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelHD.setColumnIdentifiers(colums);
        tableHD.setModel(modelHD);
        setTable();
        JPanel pTable= new JPanel(new BorderLayout());
        JScrollPane p1= new JScrollPane(tableHD);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        
        JPanel p2= new JPanel(new GridLayout(1, 2));
        JPanel temp1= new JPanel();
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        temp1.add(new JLabel("Số lượng hóa đơn"));
        temp1.add(Box.createHorizontalStrut(10));
        temp1.add(tongHD);
        JPanel temp2= new JPanel();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.X_AXIS));
        temp2.add(new JLabel("Tổng tiền các hóa đơn"));
        temp2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        temp2.add(Box.createHorizontalStrut(10));
        temp2.add(tongTienHD);
        p.add(pTable);
        p2.add(temp1);
        p2.add(temp2);
        p.add(p2);
        tongHD.setMaximumSize(new Dimension(125, 25));
        tongTienHD.setMaximumSize(new Dimension(125, 25));
        return p;
    }
    
    public JPanel CTHD(){
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
        
        temp1.add(themCTHD);
        temp1.add(suaCTHD);
        temp1.add(xoaCTHD);
//        temp1.add(timCTHD);
        temp1.add(ctTong);
        temp1.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        
        JPanel temp2 = new JPanel(new BorderLayout());
        String[] colums= {"Mã HĐ", "Mã sách", "Số lượng", "Đơn giá", "Tổng tiền", "Giảm giá", "Thành tiền"};
        DefaultTableModel modelCTHD= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelCTHD.setColumnIdentifiers(colums);
        tableCTHD.setModel(modelCTHD);
        setTableCTHD();
        JScrollPane pane= new JScrollPane(tableCTHD);
        JPanel pTable= new JPanel(new BorderLayout());
        pTable.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        pTable.add(pane);
        temp2.add(pTable);
    
        themCTHD.setPreferredSize(new Dimension(50, 40));
        themCTHD.setBackground(Color.CYAN);
        xoaCTHD.setBackground(Color.red);
        suaCTHD.setBackground(Color.orange);
        ctTong.setBackground(Color.green);
        p.add(temp1);
        p.add(temp2);
        p.setBorder(bd);
        return p;
    }
    
    public void setTable(){
        HDBLL hdbll= new HDBLL();
        ArrayList<HD> hd= hdbll.selectAll();
        DefaultTableModel modelHD= (DefaultTableModel) tableHD.getModel();
        modelHD.setRowCount(0);
        for (int i=0; i< hd.size(); i++){
            HD item= hd.get(i);
            Object[] row= new Object[] {item.getMaHD(), 
                item.getMaKH(), 
                item.getMaNV(), 
                item.getNgayLap(), 
                item.getTongTien(), 
                item.getTongGG(), 
                item.getTongSL(), 
                item.getPhuongThuc(), 
                item.getThanhTien() };
            modelHD.addRow(row);
        }
    }
    
    public void setTableCTHD(){
        CTHDBLL cthdbll= new CTHDBLL();
        ArrayList<CTHD> cthd= cthdbll.selectAll();
        DefaultTableModel modelCTHD= (DefaultTableModel) tableCTHD.getModel();
        modelCTHD.setRowCount(0);
        for (int i=0; i< cthd.size(); i++){
            CTHD item= cthd.get(i);
            Object[] row= new Object[]{item.getMaHD(), 
                item.getMaSach(), 
                item.getSoLuong(), 
                item.getGiaTien(),
                item.getTongTien(),
                item.getGiamGia(),
                item.getThanhTien()};
            modelCTHD.addRow(row);
        }
    }

    public ArrayList<JButton> getAllButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(xoa);
        buttons.add(sua);
        buttons.add(xuat);
        buttons.add(lamMoi);
        return buttons;
    }
    
    public void addEvent(){
        controller = new QLHDController(this);
        tableHD.addMouseListener(controller.cthdAdapter);
        ctTong.addActionListener(controller.showCTHD);
        xoaCTHD.addActionListener(controller.confirmDelete);
        themCTHD.addActionListener(controller.addCTHD);
        tim.addActionListener(controller.findHD);
        lamMoi.addActionListener(controller.showHD);
        xoa.addActionListener(controller.deleteHD);
        sua.addActionListener(controller.editHD);
        ctMaNV.addActionListener(controller.ctNV);
        ctMaKH.addActionListener(controller.ctSach);
        suaCTHD.addActionListener(controller.editCTHD);
        xuat.addActionListener(controller.xuatHD);
    }
    
    public JTable getTableHD() {
    return tableHD;
}

    public JTable getTableCTHD() {
        return tableCTHD;
    }
    
}