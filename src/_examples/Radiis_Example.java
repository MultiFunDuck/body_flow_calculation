package _examples;

import radiis.angular_radius.Circle_Angular_Radius;
import math.math_primitives.Flat_Point;
import math.math_primitives.Radius;
import radiis.generatrix_radius.*;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Radiis_Example {

    String pics_storage;

    public Radiis_Example(String pics_storage){
        this.pics_storage = pics_storage;
        File folder = new File(pics_storage);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run_example(){

        circular_rad_example();
        front_aug_rad_example();
        back_aug_rad_example();
        cylinder_rad_example();
        cosine_rad_example();
        cubic_spline_rad_example();
        smoothed_cubic_spline_rad_approx_example();
        smoothed_cubic_spline_rad_sqrt_example();

    }

    public void circular_rad_example(){
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);
        Radius rad = new Circle_Angular_Radius();
        drawer.draw_radius(rad,pics_storage+"/circle_radius_graph");
    }

    public void front_aug_rad_example(){
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);
        Radius rad = new Front_Augive_Radius(0,2,1);
        drawer.draw_radius(rad,pics_storage+"/front_augive_radius_graph");
    }

    public void back_aug_rad_example(){
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);
        Radius rad = new Back_Augive_Radius(0,2,1);
        drawer.draw_radius(rad,pics_storage+"/back_augive_radius_graph");
    }

    public void cylinder_rad_example(){
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);
        Radius rad = new Cylinder_Radius(0,2,1);
        drawer.draw_radius(rad,pics_storage+"/cylinder_radius_graph");
    }

    public void cosine_rad_example(){
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);
        Radius rad = new Cosine_Radius(0,2,1,0.5);
        drawer.draw_radius(rad,pics_storage+"/cosine_radius_graph");
    }

    public void cubic_spline_rad_example(){
        List<Flat_Point> points = new ArrayList<Flat_Point>();
        Flat_Point p1 = new Flat_Point(0,0);
        Flat_Point p2 = new Flat_Point(1,1);
        Flat_Point p3 = new Flat_Point(2,0);
        Flat_Point p4 = new Flat_Point(3,-1);
        Flat_Point p5 = new Flat_Point(4,0);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        Cubic_Spline_Radius rad = new Cubic_Spline_Radius(points,1,-1);

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);

        drawer.draw_radius(rad,pics_storage+"/cubic_spline_radius_graph");
    }

    public void smoothed_cubic_spline_rad_approx_example(){
        List<Flat_Point> points = new ArrayList<Flat_Point>();

        points.add(new Flat_Point(0,0 ));
        for(int i = 1; i < 10; i++){
            points.add(new Flat_Point(i,i*(10-i) + 15*Math.random()*Math.random()));
        }
        points.add(new Flat_Point(10,0 ));

        Smoothed_Cubic_Spline_Radius rad = new Smoothed_Cubic_Spline_Radius(points,4,-4,0.01);

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);

        drawer.draw_radius(rad,pics_storage+"/smoothed_cubic_spline_parabola_radius_graph");
    }


    public void smoothed_cubic_spline_rad_sqrt_example(){
        List<Flat_Point> points = new ArrayList<Flat_Point>();

        for(int i = 0; i <= 10; i++){
            points.add(new Flat_Point(i,Math.sqrt(i + 1) - 1));
        }


        Smoothed_Cubic_Spline_Radius rad = new Smoothed_Cubic_Spline_Radius(points,0.5,0,0.1);

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(1200,800);

        drawer.draw_radius(rad,pics_storage+"/smoothed_cubic_spline_sqrt_radius_graph");
    }
}
