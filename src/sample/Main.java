package sample;

import Grid_Builder.Body;
import Grid_Builder.Body_Part;
import Grid_Builder.ChangeAble_Body;
import Grid_Builder.Grid;
import Jama.Matrix;
import angular_radius.Circle_Angular_Radius;
import cubic_spline.Cubic_Function;
import cubic_spline.Cubic_Spline;
import cubic_spline.Smoothed_Spline_Operator;
import cubic_spline.Smoothing_Cubic_Spline;
import generatrix_radius.*;
import math_primitives.Flat_Point;
import math_primitives.Radius;
import math_primitives.Vector;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import separator.Even_Separator;
import separator.Separator;
import visualization.Line_Graph_Drawer;

import java.util.ArrayList;
import java.util.List;

public class Main  {


    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
*/

   /* public static void main(String[] args) {
        Radius ang_rad = new Circle_Angular_Radius();
        Separator ang_sep = new Even_Separator(ang_rad,32);

        Radius head_rad = new Front_Augive_Radius(0,1,1);
        Separator head_sep = new Even_Separator(head_rad,20);

        Radius cyl_rad = new Cylinder_Radius(1,2,1);
        Separator cyl_sep = new Even_Separator(cyl_rad,20);

        Radius conic_rad = new Conic_Radius(2,2.5,1,20);
        Separator conic_sep = new Even_Separator(conic_rad,10);

        List<Flat_Point> pts = new ArrayList<>();
        pts.add(new Flat_Point(2,conic_rad.get_end_radius()));
        pts.add(new Flat_Point(3.5,0.3/2));
        Radius cosine_rad = new Cosine_Radius(2,3.5,1,0.3);
        Separator cosine_sep = new Even_Separator(cosine_rad,30);


        Radius cyl2_rad = new Cylinder_Radius(3.5,4,0.3);
        Separator cyl2_sep = new Even_Separator(cyl2_rad,15);

        Radius back_rad = new Back_Augive_Radius(4,5,0.3);
        Separator back_sep = new Even_Separator(back_rad,20);

        Body_Part head_part = new Body_Part(head_rad,head_sep,ang_rad,ang_sep);
        Body_Part cyl_part = new Body_Part(cyl_rad,cyl_sep,ang_rad,ang_sep);
        Body_Part conic_part = new Body_Part(conic_rad,conic_sep,ang_rad,ang_sep);
        Body_Part cosine_part = new Body_Part(cosine_rad,cosine_sep,ang_rad,ang_sep);
        Body_Part cyl2_part = new Body_Part(cyl2_rad,cyl2_sep,ang_rad,ang_sep);
        Body_Part back_part = new Body_Part(back_rad,back_sep,ang_rad,ang_sep);


        ChangeAble_Body Ch_body = new ChangeAble_Body();
        Ch_body.add_part(head_part);
        Ch_body.add_part(cyl_part);
        // Ch_body.add_part(conic_part);
        Ch_body.add_part(cosine_part);
        Ch_body.add_part(cyl2_part);
        Ch_body.add_part(back_part);

        System.out.println((conic_rad.get_end_radius() - conic_rad.get_radius(conic_rad.end-0.000001))/0.000001);
        System.out.println(-(cosine_rad.get_start_radius()-cosine_rad.get_radius(cosine_rad.get_start() + 0.000001))/0.000001);
        System.out.println(Math.asin(-Math.PI*20/180));
        System.out.println(Math.atan(-Math.PI*20/180));



        double angle = 0*Math.PI/4;
        Ch_body.init_Grid();
        //Ch_body.curve_tail(2,angle);
        Grid grid = Ch_body.get_Grid();
        grid.to_File("Grid.txt");



        Vector V_inf = new Vector(2*Math.cos(angle),0,Math.sin(angle));
        double inner_pressure = 1.0;
        double inner_density = 1.0;

        grid.write_down_data(V_inf,inner_pressure,inner_density);
        grid.to_File_with_data("grid_with_data.txt");


        int window_width = 1200;
        int window_length = 840;

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        int size = grid.panels.size()-35;

        double[] data = new double[size];
        double[] plots = new double[size];

        for(int i = 0; i < size; i++){
            data[i] = grid.panels.get(i).get(0).dimless_pressure;
            plots[i] = grid.panels.get(i).get(0).middle.x;
        }
        XYSeries dataset =  new XYSeries("Function_Data", false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(data[i],plots[i]);
        }

        drawer.draw_function_graph(new XYSeriesCollection(dataset), "dimless_pressure_distribution");
    }*/

//    public static void main(String[] args) {
//
//        int k = 3;
//
//        Radius ang_rad = new Circle_Angular_Radius();
//        Separator ang_sep = new Even_Separator(ang_rad,64);
//
//        Radius head_rad = new Front_Augive_Radius(0,1,1);
//        Separator head_sep = new Even_Separator(head_rad,10*k);
//        Body_Part head_part = new Body_Part(head_rad,head_sep,ang_rad,ang_sep);
//
//
//        Radius cyl_rad = new Cylinder_Radius(1,3,1);
//        Separator cyl_sep = new Even_Separator(cyl_rad,20*k);
//        Body_Part cyl_part = new Body_Part(cyl_rad,cyl_sep,ang_rad,ang_sep);
//
//        Radius cosine_rad = new Cosine_Radius(3,5,1,0.3);
//        Separator cosine_sep = new Even_Separator(cosine_rad,20*k);
//        Body_Part cosine_part = new Body_Part(cosine_rad,cosine_sep,ang_rad,ang_sep);
//
//        Radius cyl2_rad = new Back_Augive_Radius(5,5.5,0.3);
//        Separator cyl2_sep = new Even_Separator(cyl2_rad,5*k);
//        Body_Part cyl2_part = new Body_Part(cyl2_rad,cyl2_sep,ang_rad,ang_sep);
//
//
//        ChangeAble_Body Ch_body = new ChangeAble_Body();
//        Ch_body.add_part(head_part);
//        Ch_body.add_part(cyl_part);
//        Ch_body.add_part(cosine_part);
//        //Ch_body.add_part(cyl2_part);
//
//
//        double angle = 0*Math.PI/6;
//        Ch_body.init_Grid();
//        //Ch_body.curve_tail(2,angle);
//        Grid grid = Ch_body.get_Grid();
//        grid.to_File("Grid.txt");
//
//        Vector V_inf = new Vector(1*Math.cos(angle),0,Math.sin(angle));
//        double inner_pressure = 1.0;
//        double inner_density = 1.0;
//        grid.write_down_data(V_inf,inner_pressure,inner_density);
//        grid.to_File_with_data("grid_with_data.txt");
//
//
//
//        int window_width = 1200;
//        int window_length = 840;
//
//        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);
//
//        int size = grid.panels.size()-15;
//
//        double[] data = new double[size];
//        double[] plots = new double[size];
//
//        for(int i = 0; i < size; i++){
//            data[i] = grid.panels.get(i).get(0).dimless_pressure;
//            plots[i] = grid.panels.get(i).get(0).middle.x;
//        }
//        XYSeries dataset =  new XYSeries("Function_Data", false,true);
//        for(int i = 0; i < data.length; i++){
//            dataset.add(data[i],plots[i]);
//        }
//
//        drawer.draw_function_graph(new XYSeriesCollection(dataset), "dimless_pressure_distribution");
//    }


    public static void main(String[] args) {


        List<Flat_Point> list = new ArrayList<Flat_Point>();

        list.add(new Flat_Point(0,0 ));
        for(int i = 1; i < 10; i++){
            list.add(new Flat_Point(i,i*(10-i) + 0*Math.random()*Math.random()));
        }
        list.add(new Flat_Point(10,0 ));

        Smoothed_Spline_Operator o = new Smoothed_Spline_Operator(list,0.5);
        Cubic_Spline ssp = new Cubic_Spline(o.smoothed_points(),0,0);

        System.out.println(o.smoothed_points().toString());



        for(int i =0; i < ssp.functions.size()-1; i++){
            Cubic_Function cur = ssp.functions.get(i);
            Cubic_Function next = ssp.functions.get(i+1);
            System.out.println(cur.first_derivative(cur.end) + "    " + next.first_derivative(next.start));
            System.out.println(cur.second_derivative(cur.end) + "    " + next.second_derivative(next.start));
        }




        int window_width = 1200;
        int window_length = 840;

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        int size = 100;

        double[] data = new double[size+1];
        double[] plots = new double[size+1];

        for(int i = 0; i <= size; i++){
            double a = i;
            data[i] = a/10;
            try {
                plots[i] = ssp.value(a/10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        XYSeries dataset =  new XYSeries("Spline Data", false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(plots[i],data[i]);
        }

        drawer.draw_function_graph(new XYSeriesCollection(dataset), "Spline_graph");
    }
}
