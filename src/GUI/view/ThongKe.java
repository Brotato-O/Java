package GUI.view;

import BLL.PNBLL;
import DTO.PN;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;

public class ThongKe extends JPanel {
    JComboBox<String> cbbNam = new JComboBox<>();

    public ThongKe() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("THỐNG KÊ NHẬP HÀNG"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header);

        // Lấy model để thêm dữ liệu năm
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbbNam.getModel();
        Set<String> set = new HashSet<>();  //ko biết là gì nhưng sẽ ôn sau =)))))

        PNBLL pnbll = new PNBLL();
        ArrayList<PN> pnList = pnbll.selectAll();

        // Lấy các năm duy nhất từ danh sách phiếu nhập
        for (int i=0; i< pnList.size(); i++) {
            String ngayNhap = pnList.get(i).getNgayNhap(); 
            String nam = ngayNhap.substring(0, 4);
            set.add(nam);
        }

        ArrayList<String> listNam = new ArrayList<>(set);
        Collections.sort(listNam); // =VVVVVVVv
        for (String nam : listNam) {
            model.addElement(nam);
        }

        JPanel temp= new JPanel();
        temp.add(new JLabel("Năm:"));
        temp.add(cbbNam);
        add(temp);

        // Gọi biểu đồ ngay khi khởi tạo nếu có năm
        if (!listNam.isEmpty()) {
            thongKeTheoQuy(listNam.get(0));
        }

        // Khi chọn năm khác
        cbbNam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nam = (String) cbbNam.getSelectedItem();
                thongKeTheoQuy(nam);
            }
        });
    }

    // Phương thức thống kê số lượng phiếu nhập theo quý
    public void thongKeTheoQuy(String namDuocChon) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int[] soLuong = new int[4];

        ArrayList<PN> danhSach = new PNBLL().selectAll();
        for (int i=0; i< danhSach.size(); i++){
            String ngayNhap= danhSach.get(i).getNgayNhap();
            String nam = ngayNhap.substring(0, 4);
            if (namDuocChon.equals(nam)){
                String thang= ngayNhap.substring(5, 7);
                int index= Integer.parseInt(thang)/3;
                soLuong[index]++;
            }
        }

        String[] quy = {"Quý 1", "Quý 2", "Quý 3", "Quý 4"};
        for (int i = 0; i < 4; i++) {
            dataset.addValue(soLuong[i], "Số phiếu nhập", quy[i]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Thống kê phiếu nhập theo quý năm " + namDuocChon,
            "Quý",
            "Số lượng",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

        removeAll();
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("THỐNG KÊ NHẬP HÀNG"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header);
        JPanel temp= new JPanel();
        temp.add(new JLabel("Năm:"));
        temp.add(cbbNam);
        add(temp);
        add(chartPanel);
        revalidate();
        repaint();
    }
}
