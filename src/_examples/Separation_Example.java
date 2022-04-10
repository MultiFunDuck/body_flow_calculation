package _examples;

import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;
import math.arclenght.Arclenght_Calculator;
import math.arclenght.Cartesian_Arclenght_Calculator;
import math.math_primitives.Radius;
import math.separator.*;
import radiis.angular_radius.Circle_Angular_Radius;
import radiis.generatrix_radius.Front_Augive_Radius;

import java.io.File;

public class Separation_Example {

    String body_parts_storage;

    public Separation_Example(String separation_storage){
        this.body_parts_storage = separation_storage;
        File folder = new File(separation_storage);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run_example(){

        even_separation_example();
        arclength_separation_example();
        arithmetic_separation_example();
        geometric_separation_example();

    }

    public void even_separation_example(){

        Radius long_rad = new Front_Augive_Radius(0,2,1);
        Radius cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Even_Separator(long_rad,15);
        Separator cross_sep = new Even_Separator(cross_rad,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/even_separation_example.mv");
    }

    public void arclength_separation_example(){

        Radius long_rad = new Front_Augive_Radius(0,2,1);
        Radius cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Arclength_Separator(long_rad,calc,15);
        Separator cross_sep = new Even_Separator(cross_rad,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/arc_separation_example.mv");
    }

    public void arithmetic_separation_example(){

        Radius long_rad = new Front_Augive_Radius(0,2,1);
        Radius cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Arithmetic_Separator(long_rad,calc,15);
        Separator cross_sep = new Even_Separator(cross_rad,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/arithm_separation_example.mv");
    }

    public void geometric_separation_example(){

        Radius long_rad = new Front_Augive_Radius(0,2,1);
        Radius cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(long_rad);
        Separator long_sep = new Geometric_Separator(long_rad,calc,15,1.1);
        Separator cross_sep = new Even_Separator(cross_rad,32);

        Body_Part part = new Body_Part(long_rad,long_sep,cross_rad,cross_sep);
        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/geometric_separation_example.mv");
    }
}
