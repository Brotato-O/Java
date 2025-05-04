package GUI.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import main.main;

import BLL.BLLQLS;
import BLL.CTHDBLL;
import BLL.HDBLL;
import DTO.map;

public class TK extends JPanel {
    BLLQLS bllqls = new BLLQLS();
    HDBLL hdbll = new HDBLL();
    CTHDBLL cthdbll = new CTHDBLL();
    int height= main.height;
    int width= main.width;
    public TK(){
         setLayout(new BorderLayout());
         setLayout(new BorderLayout());
         JPanel header= new JPanel();
         header.setPreferredSize(new Dimension(100, 50));
         header.add(new JLabel("THỐNG KÊ"){{setFont(new Font("Arial", Font.BOLD, 26));}});
         add(header, BorderLayout.NORTH);
          JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(tkSach());
        container.add(tkHD());
        add(container, BorderLayout.CENTER);
    }
  public JPanel tkSach() {
    JPanel tks = new JPanel(new BorderLayout());
    tks.setPreferredSize(new Dimension(0, (int)(0.2 * height)));

    JLabel TotalSach = new JLabel("Tổng số sách: " + bllqls.countBook());
    TotalSach.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    tks.add(TotalSach, BorderLayout.NORTH);

    // Lấy dữ liệu
    ArrayList<map> list = bllqls.getoneType();
    ArrayList<map> list1 = bllqls.getonencc();
    ArrayList<map> list2 = bllqls.getonetg();

    // Panel trái: thể loại + NXB
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    // Thể loại
    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setBorder(BorderFactory.createTitledBorder("Theo thể loại"));

    for (map bts : list) {
        String line = "Thể loại: " + bts.getMa() + " - Số lượng: " + bts.getTen();
        listPanel.add(new JLabel(line));
    }

    // NXB
    JPanel listPanel1 = new JPanel();
    listPanel1.setLayout(new BoxLayout(listPanel1, BoxLayout.Y_AXIS));
    listPanel1.setBorder(BorderFactory.createTitledBorder("Theo NXB"));

    for (map bts : list1) {
        String line = "NXB: " + bts.getMa() + " - Số lượng: " + bts.getTen();
        listPanel1.add(new JLabel(line));
    }

    // Panel phải: tác giả
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBorder(BorderFactory.createTitledBorder("Theo Tác giả"));

    for (map bts : list2) {
        String line = "Tác giả: " + bts.getMa() + " - Số lượng: " + bts.getTen();
        rightPanel.add(new JLabel(line));
    }

    // Thêm vào các panel trái/phải
    leftPanel.add(listPanel);
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new JSeparator());
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(listPanel1);

    // Gộp 2 bên vào 1 container chia đôi
    JPanel mainContent = new JPanel(new GridLayout(1, 2, 10, 0)); // 2 cột, khoảng cách ngang
    mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainContent.add(leftPanel);
    mainContent.add(rightPanel);

    tks.add(mainContent, BorderLayout.CENTER);
    return tks;
}
public JPanel tkHD() {
    JPanel tkhd = new JPanel(new BorderLayout());
    tkhd.setPreferredSize(new Dimension(0, (int)(0.2 * height)));

    JLabel TotalSach = new JLabel("Tổng số hóa đơn: " + hdbll.conutAll());
    TotalSach.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    tkhd.add(TotalSach, BorderLayout.NORTH);

    ArrayList<map> list = cthdbll.getSoluongSach();

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

 
    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setBorder(BorderFactory.createTitledBorder("Số lượng của từng sách đã bán"));

    for (map bts : list) {
        String line = "Mã sách: " + bts.getMa() + " - Số lượng: " + bts.getTen();
        listPanel.add(new JLabel(line));
    }

    JScrollPane scrollPane = new JScrollPane(listPanel);
    scrollPane.setPreferredSize(new Dimension(400, 200)); // kích thước cuộn (điều chỉnh tùy UI)
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    leftPanel.add(scrollPane);
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(new JSeparator());

    JPanel mainContent = new JPanel(new GridLayout(1, 1, 10, 0));
    mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainContent.add(leftPanel);

    tkhd.add(mainContent, BorderLayout.CENTER);
    return tkhd;
}

    
}
