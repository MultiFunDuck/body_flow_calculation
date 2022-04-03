package user_interface.visualization;

import calculation.grid_builder.Body_Part;
import math.math_primitives.Radius;
import math.separator.Separator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public void draw_radius(Radius radius, String chart_name){

        int size = 100;
        double start = radius.start;
        double end = radius.end;
        double step = (end - start)/size;

        double[] data = new double[size+1];
        double[] plots = new double[size+1];

        for(int i = 0; i <= size; i++){
            double cur_x = start + i*step;

            data[i] = cur_x;
            try {
                plots[i] = radius.get_radius(cur_x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        XYSeries dataset =  new XYSeries("Spline Data", false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(plots[i],data[i]);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                chart_name, "x axis ", "y axis ",
                new XYSeriesCollection(dataset), PlotOrientation.HORIZONTAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, length));
        chartPanel.setMouseZoomable(true, false);
        try {
            ChartUtilities.saveChartAsPNG(new File(chart_name + ".PNG"), chart, width, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw_radius(Radius radius, Separator separator, String destination){
        XYSeries dataset =  new XYSeries("Spline Data", false,true);


        List<Double> sep = separator.get_separation();
        for(double x: sep){
            double plot = radius.get_radius(x);
            dataset.add(plot,x);
        }


        JFreeChart chart = ChartFactory.createXYLineChart(
                "График образующей", "x axis ", "y axis ",
                new XYSeriesCollection(dataset), PlotOrientation.HORIZONTAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, length));
        chartPanel.setMouseZoomable(true, false);
        try {
            ChartUtilities.saveChartAsPNG(new File(destination + ".PNG"), chart, width, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw_full_generatix(List<Body_Part> parts, String destination){
        XYSeries dataset =  new XYSeries("Spline Data", false,true);

        for(Body_Part part : parts){
            List<Double> sep = part.get_ox_separation();
            for(double x: sep){
                double plot = part.get_ox_radius(x);
                dataset.add(plot,x);
            }
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График образующей", "x axis ", "y axis ",
                new XYSeriesCollection(dataset), PlotOrientation.HORIZONTAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, length));
        chartPanel.setMouseZoomable(true, false);
        try {
            ChartUtilities.saveChartAsPNG(new File(destination + ".PNG"), chart, width, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
