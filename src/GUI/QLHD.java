package GUI;
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
import main.main;

public class QLHD extends JPanel {
    Dimension d= new Dimension(Integer.MAX_VALUE, 25);
    int height= main.height;
    int width= main.width;
    private JTextField maHD= new JTextField();
    private JTextField maNV= new JTextField();
    private JTextField maKH= new JTextField();
    private JTextField ngayLap = new JTextField();
    private JComboBox phuongThuc= new JComboBox();
    private DefaultComboBoxModel modelPT= new DefaultComboBoxModel();
    private JTextField tongGG= new JTextField();
    private JTextField tongTien= new JTextField();
    private JTextField tongSL= new JTextField();
    private JButton xoa= new JButton("XÓA");
    private JButton sua= new JButton("SỬA");
    private JButton xuat= new JButton("XUẤT");
    private JButton lamMoi= new JButton("LÀM MỚI");
    
    private JTextField timMaHD= new JTextField();
    private JTextField timMaNV= new JTextField();
    private JTextField timMaKH= new JTextField();
    private JComboBox timPhuongThuc= new JComboBox();
    private DefaultComboBoxModel modelTimPT= new DefaultComboBoxModel();
    private JTextField tgianBD= new JTextField();
    private JTextField tgianKT= new JTextField();
    private JTextField tienBD= new JTextField();
    private JTextField tienKT= new JTextField();
    private JButton tim= new JButton();
    
    private JTextField tongHD= new JTextField();
    private JTextField tongTienHD= new JTextField();
    private JTable tableHD= new JTable();
    private DefaultTableModel modelHD= new DefaultTableModel();
    
    private JTextField ctMaHD= new JTextField();
    private JTextField ctTongSL = new JTextField();
    private JTextField ctTongThanhTien= new JTextField();
    private JTable tableCTHD= new JTable();
    private JButton ctTong= new JButton("TẤT CẢ CHI TIẾT HÓA ĐƠN");
    private DefaultTableModel modelCTHD= new DefaultTableModel();
    private JButton suaCTHD= new JButton("SỬA");
    private JButton xoaCTHD= new JButton("XÓA");
    
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
    }
    
    public JPanel inputFields(){
        JPanel p= new JPanel();
        p.setLayout(new GridLayout(1, 5));
        p.setPreferredSize(new Dimension(0, (int)(0.2*height)));
        modelPT.addElement("Tiền mặt");
        modelPT.addElement("Chuyển khoản");
        modelPT.addElement("Quẹt thẻ");
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
        xuat.setBackground(Color.BLUE);
        xuat.setForeground(Color.white);
        sua.setBackground(Color.orange);
        lamMoi.setBackground(Color.GREEN);
        return p;
    }
    
    public JPanel findFields(){
        JPanel p= new JPanel();
//        JPanel p1= new JPanel();
//        p1.setLayout(new GridLayout(1, 1));
        modelTimPT.addElement("Tiền mặt");
        modelTimPT.addElement("Chuyển khoản");
        modelTimPT.addElement("Quẹt thẻ");
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
        
        ImageIcon icon= new ImageIcon(getClass().getResource("../img/search.png"));
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
        String[] colums= {"Mã HD", "Mã KH", "Mã NV", "Ngày lập", "Tổng tiền", "Tổng giảm giá", "Phương thức"};
        modelHD.setColumnIdentifiers(colums);
        modelHD.setNumRows(20);
        tableHD.setModel(modelHD);
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
        p. setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        Border thickBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK); //ko biết là gì nhưng sẽ ôn sau
        TitledBorder ttbd= BorderFactory.createTitledBorder(thickBorder, "CHI TIẾT HÓA ĐƠN");
        ttbd.setTitleJustification(TitledBorder.CENTER);
        ttbd.setTitleColor(Color.magenta);
        ttbd.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        Border bd= BorderFactory.createLineBorder(Color.BLACK);
        bd= BorderFactory.createTitledBorder(ttbd);
        
        JPanel temp1= new JPanel();
        temp1.setLayout(new GridLayout(1, 2));
        JPanel l= new JPanel(new GridLayout(1, 3));
        JPanel[] t= new JPanel[3];
        for (int i=0; i< 3; i++){
            t[i]= new JPanel();
            t[i].setLayout(new BoxLayout(t[i], BoxLayout.Y_AXIS));
            t[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }
        t[0].add(new JLabel("Mã HĐ"));
        t[0].add(ctMaHD);
        t[1].add(new JLabel("Tổng số lượng"));
        t[1].add(ctTongSL);
        t[2].add(new JLabel("Tổng thành tiền"));
        t[2].add(ctTongThanhTien);
        for (int i=0; i< 3; i++){
            l.add(t[i]);
        }
        temp1.add(l);
        JPanel tempBtn= new JPanel();
        tempBtn.setLayout(new BoxLayout(tempBtn, BoxLayout.X_AXIS));
        tempBtn.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        tempBtn.add(ctTong);
        ctTong.setPreferredSize(new Dimension(150, 35));
        ctTong.setAlignmentY(CENTER_ALIGNMENT);
        ctTong.setBackground(Color.GRAY);
        ctTong.setForeground(Color.white);
//        temp1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        temp1.add(tempBtn);
        
        JPanel temp2 = new JPanel(new BorderLayout());
        String[] colums= {"Mã HĐ", "Mã sách", "Số lượng", "Đơn giá", "Tổng tiền", "Giảm giá", "Thành tiền"};
        modelCTHD.setColumnIdentifiers(colums);
        modelCTHD.setRowCount(20);
        tableCTHD.setModel(modelCTHD);
        JScrollPane pane= new JScrollPane(tableCTHD);
        JPanel pTable= new JPanel(new BorderLayout());
        pTable.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        pTable.add(pane);
        temp2.add(pTable);
        JPanel r= new JPanel();
        r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
        r.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        suaCTHD.setAlignmentX(CENTER_ALIGNMENT);
        suaCTHD.setMaximumSize(new Dimension(100, 35));
        suaCTHD.setFont(new Font("Arial", Font.PLAIN, 18));
        suaCTHD.setBackground(Color.ORANGE);
        r.add(suaCTHD);
        r.add(Box.createVerticalStrut(5));
        r.add(xoaCTHD);
        xoaCTHD.setAlignmentX(CENTER_ALIGNMENT);
        xoaCTHD.setMaximumSize(new Dimension(100, 35));
        xoaCTHD.setFont(new Font("Arial", Font.PLAIN, 18));
        xoaCTHD.setBackground(Color.RED);
        xoaCTHD.setForeground(Color.white);
        temp2.add(r, BorderLayout.EAST);
        
        p.add(temp1);
        p.add(temp2);
        p.setBorder(bd);
        return p;
    }
}