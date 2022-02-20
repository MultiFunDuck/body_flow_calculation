package visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

import java.io.File;
import java.io.IOException;

public class Line_Graph_Drawer {

    int width, length;

    public Line_Graph_Drawer(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public void draw_function_graph(XYDataset xyDataset, String plot_name){



        JFreeChart chart = ChartFactory.createXYLineChart(
                plot_name, "x axis ", "y axis ",
                xyDataset, PlotOrientation.HORIZONTAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, length));
        chartPanel.setMouseZoomable(true, false);
        try {
            ChartUtilities.saveChartAsPNG(new File(plot_name + ".PNG"), chart, width, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
