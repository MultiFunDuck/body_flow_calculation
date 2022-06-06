package user_interface.visualization;

import calculation.grid_building.Body;
import calculation.grid_building.Body_Part;
import math.math_primitives.Operator;
import math.math_primitives.Panel;
import math.math_primitives.Radius;
import math.math_primitives.Vector;
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

    public void draw_polar_radius(Radius radius, Separator separator, String destination){
        XYSeries dataset =  new XYSeries("Spline Data", false,true);


        List<Double> sep = separator.get_separation();
        for(double ang: sep){
            double r = radius.get_radius(ang);
            double y = r*Math.sin(ang);
            double x = r*Math.cos(ang);
            dataset.add(y,x);
        }


        JFreeChart chart = ChartFactory.createXYLineChart(
                "График формы", "x axis ", "y axis ",
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



    public void draw_results_graph(String value_name, String destination, Body body, int angle_num){

        List<List<Panel>> panels = body.grid.panels;

        int angle_size = panels.get(0).size();
        int ox_size = panels.size();


        double[] data = new double[ox_size];
        double[] plots = new double[ox_size];

        for(int i = 0; i < ox_size; i++){

            data[i] = get_panel_value_by_name(panels.get(i).get(angle_num%angle_size), value_name);
            plots[i] = panels.get(i).get(angle_num%angle_size).middle.x;
        }




        XYSeries dataset =  new XYSeries(value_name, false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(data[i],plots[i]);
        }



        JFreeChart chart = ChartFactory.createXYLineChart(
                value_name,  value_name,"Ось Ох вдоль тела ",
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

    public void draw_central_results_graph(String value_name, String destination, Body body){

        List<Panel> cross_sections = body.grid.cross_sections;

        int ox_size = cross_sections.size();


        double[] data = new double[ox_size];
        double[] plots = new double[ox_size];

        for(int i = 0; i < ox_size; i++){

            data[i] = get_cross_section_value_by_name(cross_sections.get(i), value_name);
            plots[i] = cross_sections.get(i).middle.x;
        }




        XYSeries dataset =  new XYSeries(value_name, false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(data[i],plots[i]);
        }



        JFreeChart chart = ChartFactory.createXYLineChart(
                value_name,  value_name,"Ось Ох вдоль тела ",
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

    private double get_cross_section_value_by_name(Panel cross_section, String value_name){

        double value = 0;

        switch (value_name){

            case ("V0.x_central"):
                value = cross_section.flow_velocity.x;
                break;
            case ("V0.y_central"):
                value = cross_section.flow_velocity.y;
                break;
            case ("V0.z_central"):
                value = cross_section.flow_velocity.z;
                break;
            case ("V0_central"):
                value = cross_section.flow_velocity.length();
                break;


        }


        return value;
    }

    private double get_panel_value_by_name(Panel panel, String value_name){

        Operator o = Operator.getInstance();
        Vector V0 = o.diff(panel.flow_velocity, panel.tau_velocity);
        Vector Vplus = o.sum(V0,panel.tau_velocity);
        Vector Vminus = o.diff(V0,panel.tau_velocity);
        Vector dV = o.diff(Vplus, Vminus);
        double value = 0;

        switch (value_name){

            case ("Gamma"):
                value = panel.gamma;
                break;
            case ("V+.x"):
                value = Vplus.x;
                break;
            case ("V+.y"):
                value = Vplus.y;
                break;
            case ("V+.z"):
                value = Vplus.z;
                break;
            case ("V+"):
                value = Vplus.length();
                break;



            case ("V-.x"):
                value = Vminus.x;
                break;
            case ("V-.y"):
                value = Vminus.y;
                break;
            case ("V-.z"):
                value = Vminus.z;
                break;
            case ("V-"):
                value = Vminus.length();
                break;



            case ("V0.x"):
                value = V0.x;
                break;
            case ("V0.y"):
                value = V0.y;
                break;
            case ("V0.z"):
                value = V0.z;
                break;
            case ("V0"):
                value = V0.length();
                break;



            case ("dV.x"):
                value = dV.x;
                break;
            case ("dV.y"):
                value = dV.y;
                break;
            case ("dV.z"):
                value = dV.z;
                break;
            case ("dV"):
                value = dV.length();
                break;



            case ("V+.n"):
                value = panel.normal_velocity;
                break;
            case ("V-.n"):
                value = o.scalar_mul(panel.normal,Vminus);
                break;
            case ("V0.n"):
                value = o.scalar_mul(panel.normal,V0);
                break;
            case ("dV.n"):
                value = o.scalar_mul(panel.normal,dV);
                break;



            case ("sum(Gamma_w).x"):
                value = panel.circ_velocity.x;
                break;
            case ("sum(Gamma_w).y"):
                value = panel.circ_velocity.y;
                break;
            case ("sum(Gamma_w).z"):
                value = panel.circ_velocity.z;
                break;
            case ("sum(Gamma_w)"):
                value = panel.circ_velocity.length();
                break;



            case ("Cp"):
                value = panel.dimless_pressure;
                break;
            case ("Cp-"):
                value = 1 - (1-panel.dimless_pressure) * Vminus.length()*Vminus.length() / (Vplus.length()*Vplus.length());
                break;
            case ("Cp+"):
                value = panel.dimless_pressure;
                break;
            case ("P"):
                value = panel.pressure;
                break;


        }


        return value;
    }

}
