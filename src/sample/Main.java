package sample;

import Grid_Builder.Body;
import Grid_Builder.Body_Part;
import Grid_Builder.ChangeAble_Body;
import Grid_Builder.Grid;
import Jama.Matrix;
import angular_radius.Circle_Angular_Radius;
import generatrix_radius.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import math_primitives.Flat_Point;
import math_primitives.Radius;
import math_primitives.Vector;
import separator.Even_Separator;
import separator.Separator;

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

  *//*      System.out.println((conic_rad.get_end_radius() - conic_rad.get_radius(conic_rad.end-0.000001))/0.000001);
        System.out.println(-(cosine_rad.get_start_radius()-cosine_rad.get_radius(cosine_rad.get_start() + 0.000001))/0.000001);
        System.out.println(Math.asin(-Math.PI*20/180));
        System.out.println(Math.atan(-Math.PI*20/180));*//*

        Ch_body.init_Grid();
        Ch_body.curve_tail(2,Math.PI/4);
        Grid grid = Ch_body.get_Grid();
        grid.to_File("Grid.txt");

        Vector V_inf = new Vector(1,0,1);
        grid.write_down_data(V_inf);
        grid.to_File_with_data("grid_with_data.txt");

    }*/

    public static void main(String[] args) {

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


        double angle = Math.PI/6;
        Ch_body.init_Grid();
        Ch_body.curve_tail(2,angle);
        Grid grid = Ch_body.get_Grid();
        grid.to_File("Grid.txt");

        Vector V_inf = new Vector(2*Math.cos(angle),0,Math.sin(angle));
        double inner_pressure = 1.0;
        double inner_density = 1.0;
        grid.write_down_data(V_inf,inner_pressure,inner_density);
        grid.to_File_with_data("grid_with_data.txt");

    }
}
