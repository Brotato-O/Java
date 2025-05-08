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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BLLQLGG;
import BLL.BLLQLS;
import DTO.Book;
import DTO.CTGG;
import DTO.GG;




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
    public JTextField timMa= new JTextField();
    public JButton tim= new JButton();
    
    public JTable tableCTGG= new JTable();
    public DefaultTableModel modelCT= new DefaultTableModel();
    public JButton ctSach= new JButton("...");
    public JTextField ctSachInp= new JTextField();
    public JComboBox ctGiam= new JComboBox<>();
    public JButton themCT= new JButton("THÊM");
    public JButton xoaCT= new JButton("XÓA");
    private  ArrayList<GG> list = new ArrayList<>() ;
    private BLLQLGG bllqlgg = new BLLQLGG();

    
    
    public GiamGia(){
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.add(new JLabel("CHƯƠNG TRÌNH GIẢM GIÁ"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.red);
         for (GG item : bllqlgg.getAllGG()) {
            ctGiam.addItem(item.getMaGG());
        }
        container.add(GG());
        container.add(CTGG());
        add(container, BorderLayout.CENTER);
    tableGG.getSelectionModel().addListSelectionListener(e -> {
    
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableGG.getSelectedRow();
                if (selectedRow >= 0) {
                    String magg = tableGG.getValueAt(selectedRow, 0).toString();
                    maGG.setText(tableGG.getValueAt(selectedRow, 0).toString());
                    tenGG.setText(tableGG.getValueAt(selectedRow, 1).toString());
                    tienGG.setText(tableGG.getValueAt(selectedRow, 2).toString());
                    ngayBD.setText(tableGG.getValueAt(selectedRow,3).toString());
                    ngayKT.setText(tableGG.getValueAt(selectedRow, 4).toString());
                    loadCTGG(magg);
                   
                }
            }
        });
    themGG.addActionListener(event  ->{
            if (!isInputValid(container)) return;
            String strNgayBD = ngayBD.getText();
            String strNgayKT = ngayKT.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate, endDate;
            
            try {
                startDate = LocalDate.parse(strNgayBD, formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(container, "Ngày bắt đầu phải có định dạng yyyy-MM-dd");
                ngayBD.requestFocus();
                return;
            }
            
            try {
                endDate = LocalDate.parse(strNgayKT, formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(container, "Ngày kết thúc phải có định dạng yyyy-MM-dd");
                ngayKT.requestFocus();
                return;
            }
            
            // So sánh ngày
            if (startDate.isAfter(endDate)) {
                JOptionPane.showMessageDialog(container, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc");
                ngayBD.requestFocus();
                return;
            }
            
            GG GG = new GG();
            GG.setMaGG(maGG.getText());
            GG.setTenGG(tenGG.getText());
            GG.setLuongGiam(Float.parseFloat(tienGG.getText()));
            GG.setNgayBD(strNgayBD);
            GG.setNgayKT(strNgayKT);
            if(bllqlgg.addGG(GG)){
                JOptionPane.showMessageDialog(container, "thêm thành công");
            }else JOptionPane.showMessageDialog(container, "Trùng mã sách");
        
        });
    suaGG.addActionListener(event -> {
            if (!isInputValid(container)) return;  
            GG GG = new GG();
            GG.setMaGG(maGG.getText());
            GG.setTenGG(tenGG.getText());
            GG.setLuongGiam(Float.parseFloat(tienGG.getText()));
            GG.setNgayBD(ngayBD.getText());
            GG.setNgayKT(ngayKT.getText());
            if (bllqlgg.updateGG(GG)) {
                JOptionPane.showMessageDialog(container, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(container, "Cập nhật thất bại, mã sách không tồn tại");
            }
        });
        
    xoaGG.addActionListener(event  ->{
            GG GG = new GG();
            GG.setMaGG(maGG.getText());
            if(bllqlgg.deleteGGSQL(GG)){
                int a = JOptionPane.showConfirmDialog(container, "Bạn có chắc muốn xóa chứ?","Xác nhận", JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(container, "Xóa thành công");
                int selectedRow = tableGG.getSelectedRow();
                if (selectedRow >= 0) {
                list.remove(selectedRow);
                showTable();
                }
            }
            }else JOptionPane.showMessageDialog(container, "Xóa thất bại");
        
        });
    allGG.addActionListener(event -> {

            maGG.setText("");
            tenGG.setText("");
            tienGG.setText("");
            ngayBD.setText("");
            ngayKT.setText("");
           
            list = bllqlgg.getAllGG();  
            showTable(); 
        });
    ctSach.addActionListener(even -> {
        ctSach(); 
    });
    themCT.addActionListener(even -> {
         if (ctSachInp.getText().trim().isEmpty() && ctGiam.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(container, "Vui lòng nhập đầy đủ thông tin.");
            return;
         }
    CTGG ctgg = new CTGG();
    ctgg.setMaSach( ctSachInp.getText());
    String selectedMaGG = (String) ctGiam.getSelectedItem();
        if (selectedMaGG != null) {
            ctgg.setMaGG(selectedMaGG);
        }
    if(bllqlgg.addCTGG(ctgg)){
        JOptionPane.showMessageDialog(container, "thêm thành công");
        modelCT.addRow(new Object[]{
            ctgg.getMaGG(), ctgg.getMaSach()
        });
    }else JOptionPane.showMessageDialog(container, "Đã có!!!");
    });
    xoaCT.addActionListener(even -> {
       CTGG ctgg = new CTGG();
       int selectedRow = tableCTGG.getSelectedRow();
       String magg = modelCT.getValueAt(selectedRow, 0).toString();
       String masach = modelCT.getValueAt(selectedRow, 1).toString();
        ctgg.setMaGG(magg);
        ctgg.setMaSach(masach);
        if(bllqlgg.deleteCTGGSQL(ctgg)){
        int a = JOptionPane.showConfirmDialog(container, "Bạn có chắc muốn xóa chứ?","Xác nhận", JOptionPane.YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION){
        JOptionPane.showMessageDialog(container, "Xóa thành công");
        modelCT.removeRow(selectedRow);
    }
    }else JOptionPane.showMessageDialog(container, "Xóa thất bại");
    });
    tim.addActionListener(even -> {
        ArrayList<GG> kq = timKiemGG();
        list = kq;
        showTable();
    });
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
        list = bllqlgg.getAllGG();
        String[] column= {"Mã GG", "Tên chương trình", "Số tiền giảm", "Ngày bắt đầu", "Ngày kết thúc"};
        modelGG.setColumnIdentifiers(column);
        showTable();
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

        JPanel pMa= new JPanel();
        pMa.setLayout(new BoxLayout(pMa, BoxLayout.X_AXIS));
        pMa.add(new JLabel("Mã giảm giá"));
        pMa.add(Box.createHorizontalStrut(10));
        JPanel pKT3= new JPanel();
        pKT3.setLayout(new BoxLayout(pKT3, BoxLayout.X_AXIS));
        pKT3.add(timMa);
        pMa.add(pKT3);
        pMa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel l= new JPanel();
        l.setLayout(new BoxLayout(l, BoxLayout.X_AXIS));
        l.add(pBD);
        l.add(pKT);
        l.add(pMa);
        
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
    public void showTable() {
        modelGG.setRowCount(0); 
        int i = 1;
        for (GG s : list) {
            modelGG.addRow(new Object[]{
                s.getMaGG(),
                s.getTenGG(),
                s.getLuongGiam(),        
                s.getNgayBD(),
                s.getNgayKT(),
                ""                   
            });
        }
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
        String[] column= {"Mã GG", "Sách"} ;
        modelCT.setColumnIdentifiers(column);
        
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
        giam.add(new JLabel("Mã giảm"));
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
    private boolean isInputValid( JPanel container ) {
        if (
            // txtMaSach.getText().trim().isEmpty() ||
            maGG.getText().trim().isEmpty() ||
            tenGG.getText().trim().isEmpty() ||
            ngayBD.getText().trim().isEmpty() ||
            ngayKT.getText().trim().isEmpty()) {
    
            JOptionPane.showMessageDialog(container, "Vui lòng nhập đầy đủ thông tin.");
            return false;
        }
    
        try {
            Float.parseFloat(tienGG.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(container, "Khuyễn mại phải là số hợp lệ.");
            return false;
        }
    
        return true;
    }
    private void ctSach(){
    JFrame fr1 = new JFrame();
        fr1.dispose();
		fr1.setSize(400, 550);
        fr1.setLayout(new BorderLayout());
       
    JPanel tbQLS= new JPanel();
    ArrayList<Book> list1 = new ArrayList<>() ;  
    DefaultTableModel modelHD= new DefaultTableModel();
    JTable tableHD= new JTable();
        list1 = new BLLQLS().getAllBooks();

       
        tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
    String[] colums= {"MÃ SÁCH","TÊN SÁCH","MÃ THỂ LOẠI", "TÊN TÁC GIẢ", "NĂM XUẤT BẢN", "SỐ LƯỢNG", "ĐƠN GIÁ"};
        modelHD.setColumnIdentifiers(colums);
        modelHD.setRowCount(0); 
        for (Book s : list1) {
            modelHD.addRow(new Object[]{
                s.getMaSach(),
                s.getTenSach(),      
                s.getMaLoai(),
                s.getMaTacGia(),
                s.getNamXB(),                
                s.getSoLuong(),
                s.getDonGia(),
                ""                   
            });
        }
        tableHD.setModel(modelHD);
    JPanel pTable= new JPanel(new BorderLayout());
    JScrollPane p1= new JScrollPane(tableHD);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        tbQLS.add(pTable);
    JButton chon = new JButton("Chọn");

        fr1.add(tbQLS,BorderLayout.NORTH);
        fr1.add(chon, BorderLayout.CENTER);
        fr1.setVisible(true);
        chon.addActionListener(even -> {
            int selectedRow = tableHD.getSelectedRow();
            if (selectedRow >= 0) {
                String maSach = tableHD.getValueAt(selectedRow, 0).toString(); 
                ctSachInp.setText(maSach); 
                fr1.dispose(); 
            } else {
                JOptionPane.showMessageDialog(fr1, "Vui lòng chọn một dòng sách!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        
    }
    
    public ArrayList<GG> timKiemGG() {
        String maGG = timMa.getText().trim();
        String ngayTu = timBD.getText().trim();
        String ngayDen = timKT.getText().trim();

    
        ArrayList<GG> ketQua = new ArrayList<>();
        for (GG s : bllqlgg.getAllGG()) {
            if (!maGG.isEmpty() && !s.getMaGG().contains(maGG)) continue;
            // nên dùng DateTimeFormatter formatter và LocalDate để so sánh ngày chuẩn hơn 
            if (!ngayTu.isEmpty() && !s.getNgayBD().toLowerCase().contains(ngayTu.toLowerCase())) continue;
            if (!ngayDen.isEmpty() && !s.getNgayKT().toLowerCase().contains(ngayDen.toLowerCase())) continue;
    
            ketQua.add(s);
        }
        return ketQua;
    }
    private void loadCTGG(String magg) {
        ArrayList<CTGG> listCTGG = bllqlgg.getAllCTGG(magg);
    
        // Xóa sạch dữ liệu cũ trong model
        modelCT.setRowCount(0);
    
        // Thêm lại dữ liệu mới
        for (CTGG ct : listCTGG) {
            modelCT.addRow(new Object[]{
                ct.getMaGG(), ct.getMaSach()
            });
        }
    }
    
}
