package _examples;

import arclenght.Arclenght_Calculator;
import arclenght.Cartesian_Arclenght_Calculator;
import arclenght.Polar_Arclenght_Calculator;
import grid_builder.Body_Part;
import grid_builder.ChangeAble_Body;
import angular_radius.Circle_Angular_Radius;
import angular_radius.Elliptic_Angular_Radius;
import generatrix_radius.*;
import math_primitives.Flat_Point;
import math_primitives.Radius;
import separator.Arclength_Separator;
import separator.Even_Separator;
import separator.Separator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Body_Part_Example {
    String body_parts_storage;

    public Body_Part_Example(String pics_storage){
        this.body_parts_storage = pics_storage;
        File folder = new File(pics_storage);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run_example(){

        front_aug_body_example();
        back_aug_body_example();
        cylinder_body_example();
        cosine_body_example();
        smoothed_cubic_spline_body_example();

    }


    public void front_aug_body_example(){

        Radius long_rad = new Front_Augive_Radius(0,2,1);
        Radius cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Arclength_Separator(long_rad,calc,20);
        Separator cross_sep = new Even_Separator(cross_rad,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/front_aug_body.txt");
    }

    public void back_aug_body_example(){

        Radius long_rad = new Back_Augive_Radius(0,2,1);
        Radius cross_rad = new Elliptic_Angular_Radius(1,1.75);

        Arclenght_Calculator long_calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Arclength_Separator(long_rad,long_calc,20);

        Arclenght_Calculator cross_calc = new Polar_Arclenght_Calculator(cross_rad);
        Separator cross_sep = new Arclength_Separator(cross_rad,cross_calc,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/back_aug_body.txt");

    }

    public void cylinder_body_example(){

        Radius long_rad = new Cylinder_Radius(0,2,1);
        Radius cross_rad = new Elliptic_Angular_Radius(1,1.75);

        Separator long_sep = new Even_Separator(long_rad,20);

        Arclenght_Calculator cross_calc = new Polar_Arclenght_Calculator(cross_rad);
        Separator cross_sep = new Arclength_Separator(cross_rad, cross_calc,16);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/cylinder_body.txt");


    }

    public void cosine_body_example(){

        Radius long_rad = new Cosine_Radius(0,2,1,0.5);
        Radius cross_rad = new Elliptic_Angular_Radius(1,1.75);

        Separator long_sep = new Even_Separator(long_rad,20);

        Arclenght_Calculator cross_calc = new Polar_Arclenght_Calculator(cross_rad);
        Separator cross_sep = new Arclength_Separator(cross_rad, cross_calc,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/cosine_body.txt");
    }


    public void smoothed_cubic_spline_body_example(){
        List<Flat_Point> points = new ArrayList<Flat_Point>();

        for(int i = 0; i <= 10; i++){
            points.add(new Flat_Point(i,Math.sqrt(i)));
        }

        Radius long_rad = new Smoothed_Cubic_Spline_Radius(points,0.5,0,0.01);
        Radius cross_rad = new Elliptic_Angular_Radius(1,1.75);


        Arclenght_Calculator long_calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Arclength_Separator(long_rad,long_calc,20);

        Arclenght_Calculator cross_calc = new Polar_Arclenght_Calculator(cross_rad);
        Separator cross_sep = new Arclength_Separator(cross_rad,cross_calc,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/smoother_spline_body.txt");
    }
}
