package _examples;

import radiis.angular_radius.Circle_Angular_Radius;
import radiis.generatrix_radius.Back_Augive_Radius;
import radiis.generatrix_radius.Cosine_Radius;
import radiis.generatrix_radius.Cylinder_Radius;
import radiis.generatrix_radius.Front_Augive_Radius;
import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;
import calculation.grid_builder.Grid;
import math.math_primitives.Radius;
import math.math_primitives.Vector;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import math.separator.Even_Separator;
import math.separator.Separator;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class Calculation_Example {

    String storage;

    public Calculation_Example(String calcs_example_storage){
        this.storage = calcs_example_storage;
        File folder = new File(calcs_example_storage);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run_example(){
        simplest_body_calc_example();
        simple_body_calc_example();
        curved_body_calc_example();
    }


    public void simplest_body_calc_example(){
        Radius ang_rad = new Circle_Angular_Radius();
        Separator ang_sep = new Even_Separator(ang_rad,4);


        Radius head_rad = new Front_Augive_Radius(0,1,1);
        Separator head_sep = new Even_Separator(head_rad,6);

        Radius cyl_rad = new Cylinder_Radius(1,2,1);
        Separator cyl_sep = new Even_Separator(cyl_rad,6);

        Radius cosine_rad = new Cosine_Radius(2,3.5,1,0.3);
        Separator cosine_sep = new Even_Separator(cosine_rad,9);

        Radius cyl2_rad = new Cylinder_Radius(3.5,4,0.3);
        Separator cyl2_sep = new Even_Separator(cyl2_rad,3);



        Body_Part head_part = new Body_Part(head_rad,head_sep,ang_rad,ang_sep);
        Body_Part cyl_part = new Body_Part(cyl_rad,cyl_sep,ang_rad,ang_sep);
        Body_Part cosine_part = new Body_Part(cosine_rad,cosine_sep,ang_rad,ang_sep);
        Body_Part cyl2_part = new Body_Part(cyl2_rad,cyl2_sep,ang_rad,ang_sep);


        ChangeAble_Body Ch_body = new ChangeAble_Body();
        Ch_body.add_part(head_part);
        Ch_body.add_part(cyl_part);
        Ch_body.add_part(cosine_part);
        Ch_body.add_part(cyl2_part);


        Ch_body.init_Grid();
        Grid grid = Ch_body.get_Grid();
        grid.to_File(storage +"/simplest_body_grid.mv");



        Vector V_inf = new Vector(1,0,0);
        double inner_pressure = 1.0;
        double inner_density = 1.0;

        grid.write_down_data(V_inf,inner_pressure,inner_density);
        grid.to_File_with_data(storage + "/simplest_body_grid_with_data.mv");


        int window_width = 1200;
        int window_length = 840;

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        int size = grid.panels.size();

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

        drawer.draw_function_graph(new XYSeriesCollection(dataset), storage + "/simplest_body_dimless_pressure_distribution");
    }


    public void simple_body_calc_example(){
        Radius ang_rad = new Circle_Angular_Radius();
        Separator ang_sep = new Even_Separator(ang_rad,12);


        Radius head_rad = new Front_Augive_Radius(0,1,1);
        Separator head_sep = new Even_Separator(head_rad,10);

        Radius cyl_rad = new Cylinder_Radius(1,2,1);
        Separator cyl_sep = new Even_Separator(cyl_rad,10);

        Radius cosine_rad = new Cosine_Radius(2,3.5,1,0.3);
        Separator cosine_sep = new Even_Separator(cosine_rad,15);

        Radius cyl2_rad = new Cylinder_Radius(3.5,4,0.3);
        Separator cyl2_sep = new Even_Separator(cyl2_rad,5);



        Body_Part head_part = new Body_Part(head_rad,head_sep,ang_rad,ang_sep);
        Body_Part cyl_part = new Body_Part(cyl_rad,cyl_sep,ang_rad,ang_sep);
        Body_Part cosine_part = new Body_Part(cosine_rad,cosine_sep,ang_rad,ang_sep);
        Body_Part cyl2_part = new Body_Part(cyl2_rad,cyl2_sep,ang_rad,ang_sep);


        ChangeAble_Body Ch_body = new ChangeAble_Body();
        Ch_body.add_part(head_part);
        Ch_body.add_part(cyl_part);
        Ch_body.add_part(cosine_part);
        Ch_body.add_part(cyl2_part);


        Ch_body.init_Grid();
        Grid grid = Ch_body.get_Grid();
        grid.to_File(storage +"/simple_body_grid.mv");



        Vector V_inf = new Vector(1,0,0);
        double inner_pressure = 1.0;
        double inner_density = 1.0;

        grid.write_down_data(V_inf,inner_pressure,inner_density);
        grid.to_File_with_data(storage + "/simple_body_grid_with_data.mv");


        int window_width = 1200;
        int window_length = 840;

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        int size = grid.panels.size();

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

        drawer.draw_function_graph(new XYSeriesCollection(dataset), storage + "/simple_body_dimless_pressure_distribution");
    }


    public void curved_body_calc_example(){
        int k = 2;

        Radius ang_rad = new Circle_Angular_Radius();
        Separator ang_sep = new Even_Separator(ang_rad,64);

        Radius head_rad = new Front_Augive_Radius(0,1,1);
        Separator head_sep = new Even_Separator(head_rad,10*k);
        Body_Part head_part = new Body_Part(head_rad,head_sep,ang_rad,ang_sep);


        Radius cyl_rad = new Cylinder_Radius(1,3,1);
        Separator cyl_sep = new Even_Separator(cyl_rad,20*k);
        Body_Part cyl_part = new Body_Part(cyl_rad,cyl_sep,ang_rad,ang_sep);

        Radius cosine_rad = new Cosine_Radius(3,5,1,0.3);
        Separator cosine_sep = new Even_Separator(cosine_rad,20*k);
        Body_Part cosine_part = new Body_Part(cosine_rad,cosine_sep,ang_rad,ang_sep);

        Radius cyl2_rad = new Back_Augive_Radius(5,5.5,0.3);
        Separator cyl2_sep = new Even_Separator(cyl2_rad,5*k);
        Body_Part cyl2_part = new Body_Part(cyl2_rad,cyl2_sep,ang_rad,ang_sep);


        ChangeAble_Body Ch_body = new ChangeAble_Body();
        Ch_body.add_part(head_part);
        Ch_body.add_part(cyl_part);
        Ch_body.add_part(cosine_part);
        Ch_body.add_part(cyl2_part);


        double angle = Math.PI/12;
        Ch_body.init_Grid();
        Ch_body.curve_tail(2,angle);
        Grid grid = Ch_body.get_Grid();
        grid.to_File(storage + "/curved_body_grid.mv");

        Vector V_inf = new Vector(Math.cos(angle),0,Math.sin(angle));
        double inner_pressure = 1.0;
        double inner_density = 1.0;
        grid.write_down_data(V_inf,inner_pressure,inner_density);
        grid.to_File_with_data(storage + "/curved_body_grid_with_data.mv");



        int window_width = 1200;
        int window_length = 840;

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        int size = grid.panels.size()-15;

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

        drawer.draw_function_graph(new XYSeriesCollection(dataset), storage + "/curved_body_dimless_pressure_distribution");
    }

}
