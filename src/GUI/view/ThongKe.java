package GUI.view;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class ThongKe extends JPanel {

    public ThongKe() {
        // Dữ liệu biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String[] tenSach = {"Lập trình Java", "Cơ sở dữ liệu", "HTML & CSS", "Python cơ bản"};
        int[] soLuongBan = {120, 90, 150, 110};

        for (int i = 0; i < tenSach.length; i++) {
            dataset.addValue(soLuongBan[i], "Số lượng bán", tenSach[i]);
        }

        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê số lượng sách đã bán",
                "Tên sách",
                "Số lượng",
                dataset
        );

        // Gắn biểu đồ vào JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        this.setLayout(new java.awt.BorderLayout());
        this.add(chartPanel, java.awt.BorderLayout.CENTER);
    }
}
