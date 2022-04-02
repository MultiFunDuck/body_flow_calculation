package _examples;

import radiis.angular_radius.Elliptic_Angular_Radius;
import math.arclenght.Arclenght_Calculator;
import math.arclenght.Cartesian_Arclenght_Calculator;
import math.arclenght.Polar_Arclenght_Calculator;
import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;
import radiis.angular_radius.Circle_Angular_Radius;
import radiis.generatrix_radius.Cosine_Radius;
import radiis.generatrix_radius.Cylinder_Radius;
import radiis.generatrix_radius.Front_Augive_Radius;
import radiis.generatrix_radius.Smoothed_Cubic_Spline_Radius;
import math.math_primitives.Flat_Point;
import math.math_primitives.Radius;
import math.separator.Arclength_Separator;
import math.separator.Even_Separator;
import math.separator.Separator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bodies_Example {

    String body_parts_storage;

    public Bodies_Example(String pics_storage){
        this.body_parts_storage = pics_storage;
        File folder = new File(pics_storage);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run_example(){

        simple_body_example();
        curved_body_example();
        spline_body_example();
        elliptic_body_example();
        tapering_body_example();
        //tapering_curved_body_example();


    }

    public ChangeAble_Body simple_body_example(){

        Radius front_long_rad = new Front_Augive_Radius(0,2,1);
        Radius front_cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator front_calculator = new Cartesian_Arclenght_Calculator(front_long_rad);
        Separator front_long_sep = new Arclength_Separator(front_long_rad,front_calculator,20);
        Separator front_cross_sep = new Even_Separator(front_cross_rad,32);

        Body_Part front_part = new Body_Part(front_long_rad,front_long_sep,front_cross_rad,front_cross_sep);


        Radius middle_long_rad = new Cylinder_Radius(2,3,1);
        Radius middle_cross_rad = new Circle_Angular_Radius();
        Separator middle_long_sep = new Even_Separator(middle_long_rad,10);
        Separator middle_cross_sep = new Even_Separator(middle_cross_rad,32);

        Body_Part middle_part = new Body_Part(middle_long_rad,middle_long_sep,middle_cross_rad,middle_cross_sep);

        Radius middle_cos_long_rad = new Cosine_Radius(3,5,1,0.3);
        Radius middle_cos_cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator middle_calculator = new Cartesian_Arclenght_Calculator(middle_cos_long_rad);
        Separator middle_cos_long_sep = new Arclength_Separator(middle_cos_long_rad, middle_calculator,20);
        Separator middle_cos_cross_sep = new Even_Separator(middle_cos_cross_rad,32);

        Body_Part middle_cos_part = new Body_Part(middle_cos_long_rad,middle_cos_long_sep,middle_cos_cross_rad,middle_cos_cross_sep);


        Radius back_long_rad = new Cylinder_Radius(5,6,0.3);
        Radius back_cross_rad = new Circle_Angular_Radius();
        Separator back_long_sep = new Even_Separator(back_long_rad,10);
        Separator back_cross_sep = new Even_Separator(back_cross_rad,32);

        Body_Part back_part = new Body_Part(back_long_rad,back_long_sep,back_cross_rad,back_cross_sep);


        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(front_part);
        body.add_part(middle_part);
        body.add_part(middle_cos_part);
        body.add_part(back_part);
        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/simple_body.txt");

        return body;
    }


    public ChangeAble_Body spline_body_example(){

        List<Flat_Point> points = new ArrayList<Flat_Point>();

        for(int i = 0; i <= 3; i++){
            double x = ((double)i)/ 3;
            points.add(new Flat_Point(x,x*(2-x)/2));
        }


        //Spline front part
        Radius _1L = new Smoothed_Cubic_Spline_Radius(points,1,0,0.99);
        Arclenght_Calculator _1Lc = new Cartesian_Arclenght_Calculator(_1L);
        Separator _1Ls = new Arclength_Separator(_1L,_1Lc,10);

        Radius _1P = new Circle_Angular_Radius();
        Separator _1Ps = new Even_Separator(_1P,32);

        Body_Part _1BP = new Body_Part(_1L,_1Ls,_1P,_1Ps);


        //Cylinder mid part
        Radius _2L = new Cylinder_Radius(1,2,1);
        Separator _2Ls = new Even_Separator(_2L,10);
        Radius _2P = new Circle_Angular_Radius();
        Separator _2Ps = new Even_Separator(_2P,32);

        Body_Part _2BP = new Body_Part(_2L,_2Ls,_2P,_2Ps);


        //Cosine mid part
        Radius _3L = new Cosine_Radius(2,3,1,0.3);
        Arclenght_Calculator _3Lc = new Cartesian_Arclenght_Calculator(_3L);
        Separator _3Ls = new Arclength_Separator(_3L,_3Lc,10);

        Radius _3P = new Circle_Angular_Radius();
        Separator _3Ps = new Even_Separator(_3P,32);

        Body_Part _3BP = new Body_Part(_3L,_3Ls,_3P,_3Ps);


        //End cylinder radius
        Radius _4L = new Cylinder_Radius(3,4,0.3);
        Separator _4Ls = new Even_Separator(_4L,20);
        Radius _4P = new Circle_Angular_Radius();
        Separator _4Ps = new Even_Separator(_4P,32);

        Body_Part _4BP = new Body_Part(_4L,_4Ls,_4P,_4Ps);


        ChangeAble_Body body = new ChangeAble_Body();

        body.add_part(_1BP);
        body.add_part(_2BP);
        body.add_part(_3BP);
        body.add_part(_4BP);

        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/spline_body.txt");

        return body;
    }


    public ChangeAble_Body curved_body_example(){

        Radius front_long_rad = new Front_Augive_Radius(0,2,1);
        Radius front_cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator front_calculator = new Cartesian_Arclenght_Calculator(front_long_rad);
        Separator front_long_sep = new Arclength_Separator(front_long_rad, front_calculator,20);
        Separator front_cross_sep = new Even_Separator(front_cross_rad,32);

        Body_Part front_part = new Body_Part(front_long_rad,front_long_sep,front_cross_rad,front_cross_sep);


        Radius middle_long_rad = new Cylinder_Radius(2,3,1);
        Radius middle_cross_rad = new Circle_Angular_Radius();
        Separator middle_long_sep = new Even_Separator(middle_long_rad,10);
        Separator middle_cross_sep = new Even_Separator(middle_cross_rad,32);

        Body_Part middle_part = new Body_Part(middle_long_rad,middle_long_sep,middle_cross_rad,middle_cross_sep);

        Radius middle_cos_long_rad = new Cosine_Radius(3,5,1,0.3);
        Radius middle_cos_cross_rad = new Circle_Angular_Radius();

        Arclenght_Calculator middle_calculator = new Cartesian_Arclenght_Calculator(middle_cos_long_rad);
        Separator middle_cos_long_sep = new Arclength_Separator(middle_cos_long_rad,middle_calculator,20);
        Separator middle_cos_cross_sep = new Even_Separator(middle_cos_cross_rad,32);

        Body_Part middle_cos_part = new Body_Part(middle_cos_long_rad,middle_cos_long_sep,middle_cos_cross_rad,middle_cos_cross_sep);


        Radius back_long_rad = new Cylinder_Radius(5,6,0.3);
        Radius back_cross_rad = new Circle_Angular_Radius();
        Separator back_long_sep = new Even_Separator(back_long_rad,10);
        Separator back_cross_sep = new Even_Separator(back_cross_rad,32);

        Body_Part back_part = new Body_Part(back_long_rad,back_long_sep,back_cross_rad,back_cross_sep);


        ChangeAble_Body body = new ChangeAble_Body();
        body.add_part(front_part);
        body.add_part(middle_part);
        body.add_part(middle_cos_part);
        body.add_part(back_part);



        body.init_Grid();
        body.curve_tail(2,30*Math.PI/180);
        body.get_Grid().to_File(body_parts_storage + "/curved_body.txt");

        return body;

    }


    public ChangeAble_Body elliptic_body_example(){


        //Augive front part
        Radius _1L = new Front_Augive_Radius(0,2,1);
        Arclenght_Calculator _1Lc = new Cartesian_Arclenght_Calculator(_1L);
        Separator _1Ls = new Arclength_Separator(_1L,_1Lc,20);

        Radius _1P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _1Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _1Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _1BP = new Body_Part(_1L,_1Ls,_1P,_1Ps);


        //Cylinder mid part
        Radius _2L = new Cylinder_Radius(2,3,1);
        Separator _2Ls = new Even_Separator(_2L,10);

        Radius _2P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _2Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _2Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _2BP = new Body_Part(_2L,_2Ls,_2P,_2Ps);


        //Cosine mid part
        Radius _3L = new Cosine_Radius(3,4,1,0.5);
        Arclenght_Calculator _3Lc = new Cartesian_Arclenght_Calculator(_3L);
        Separator _3Ls = new Arclength_Separator(_3L,_3Lc,10);

        Radius _3P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _3Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _3Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _3BP = new Body_Part(_3L,_3Ls,_3P,_3Ps);


        //End cylinder radius
        Radius _4L = new Cylinder_Radius(4,5,0.5);
        Separator _4Ls = new Even_Separator(_4L,20);

        Radius _4P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _4Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _4Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _4BP = new Body_Part(_4L,_4Ls,_4P,_4Ps);


        ChangeAble_Body body = new ChangeAble_Body();

        body.add_part(_1BP);
        body.add_part(_2BP);
        body.add_part(_3BP);
        body.add_part(_4BP);

        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/elliptic_body.txt");

        return body;

    }


    public ChangeAble_Body tapering_body_example(){

        ChangeAble_Body body = new ChangeAble_Body();

        Radius _1L = new Front_Augive_Radius(0,2,1);
        Arclenght_Calculator _1Lc = new Cartesian_Arclenght_Calculator(_1L);
        Separator _1Ls = new Arclength_Separator(_1L,_1Lc,20);

        Radius _1P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _1Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _1Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _1BP = new Body_Part(_1L,_1Ls,_1P,_1Ps);

        body.add_part(_1BP);



        //Cylinder mid part
        Radius _2L = new Cylinder_Radius(2,3,1);
        Separator _2Ls = new Even_Separator(_2L,10);

        Radius _2P = new Elliptic_Angular_Radius(1,1.5);
        Arclenght_Calculator _2Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _2Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _2BP = new Body_Part(_2L,_2Ls,_2P,_2Ps);

        body.add_part(_2BP);


        //Transfering cosine mid part

        Radius cos = new Cosine_Radius(3,5,1,0.5);

        double cur_x = 3;
        double prev_x = 3;

        for(int i = 1; i <= 20; i++){

            cur_x = 3.0 + ((double)i)/10;

            Radius _3L = new Radius() {
                @Override
                public double get_radius(double x) {
                    return cos.get_radius(x);
                }

                @Override
                public Radius get_derivative() {
                    return cos.get_derivative();
                }

            };
            _3L.start = prev_x;
            _3L.end = cur_x;

            Arclenght_Calculator _3Lc = new Cartesian_Arclenght_Calculator(_3L);
            Separator _3Ls = new Arclength_Separator(_3L,_3Lc,1);

            Radius _3P = new Elliptic_Angular_Radius(1,1.5 - 0.5*(i-1)/19);
            Arclenght_Calculator _3Pc = new Polar_Arclenght_Calculator(_1P);
            Separator _3Ps = new Arclength_Separator(_1P,_1Pc,32);


            Body_Part _3BP = new Body_Part(_3L,_3Ls,_3P,_3Ps);

            body.add_part(_3BP);

            prev_x = cur_x;
        }



        //End cylinder radius
        Radius _4L = new Cylinder_Radius(5,6,0.5);
        Separator _4Ls = new Even_Separator(_4L,10);

        Radius _4P = new Circle_Angular_Radius();
        Arclenght_Calculator _4Pc = new Polar_Arclenght_Calculator(_1P);
        Separator _4Ps = new Arclength_Separator(_1P,_1Pc,32);

        Body_Part _4BP = new Body_Part(_4L,_4Ls,_4P,_4Ps);

        body.add_part(_4BP);

        body.init_Grid();
        body.get_Grid().to_File(body_parts_storage + "/tapering_body.txt");

        return body;
    }


//    public ChangeAble_Body tapering_curved_body_example(){
//
//        ChangeAble_Body body = new ChangeAble_Body();
//
//        Radius _1L = new Front_Augive_Radius(0,2,1);
//        Arclenght_Calculator _1Lc = new Cartesian_Arclenght_Calculator(_1L);
//        Separator _1Ls = new Arclength_Separator(_1L,_1Lc,20);
//
//        Radius _1P = new Elliptic_Angular_Radius(1,1.5);
//        Arclenght_Calculator _1Pc = new Polar_Arclenght_Calculator(_1P);
//        Separator _1Ps = new Arclength_Separator(_1P,_1Pc,32);
//
//        Body_Part _1BP = new Body_Part(_1L,_1Ls,_1P,_1Ps);
//
//        body.add_part(_1BP);
//
//
//
//        //Cylinder mid part
//        Radius _2L = new Cylinder_Radius(2,3,1);
//        Separator _2Ls = new Even_Separator(_2L,10);
//
//        Radius _2P = new Elliptic_Angular_Radius(1,1.5);
//        Arclenght_Calculator _2Pc = new Polar_Arclenght_Calculator(_1P);
//        Separator _2Ps = new Arclength_Separator(_1P,_1Pc,32);
//
//        Body_Part _2BP = new Body_Part(_2L,_2Ls,_2P,_2Ps);
//
//        body.add_part(_2BP);
//
//
//        //Transfering cosine mid part
//
//        Radius cos = new Cosine_Radius(3,5,1,0.5);
//
//        double cur_x = 3;
//        double prev_x = 3;
//
//        for(int i = 1; i <= 20; i++){
//
//            cur_x = 3.0 + ((double)i)/10;
//
//            Radius _3L = new Radius() {
//                @Override
//                public double get_radius(double x) {
//                    return cos.get_radius(x);
//                }
//
//                @Override
//                public Radius get_derivative() {
//                    return cos.get_derivative();
//                }
//
//            };
//            _3L.start = prev_x;
//            _3L.end = cur_x;
//
//            Arclenght_Calculator _3Lc = new Cartesian_Arclenght_Calculator(_3L);
//            Separator _3Ls = new Arclength_Separator(_3L,_3Lc,1);
//
//            Radius _3P = new Elliptic_Angular_Radius(1,1.5 - 0.5*(i-1)/19);
//            Arclenght_Calculator _3Pc = new Polar_Arclenght_Calculator(_1P);
//            Separator _3Ps = new Arclength_Separator(_1P,_1Pc,32);
//
//
//            Body_Part _3BP = new Body_Part(_3L,_3Ls,_3P,_3Ps);
//
//            body.add_part(_3BP);
//
//            prev_x = cur_x;
//        }
//
//
//
//        //End cylinder radius
//        Radius _4L = new Cylinder_Radius(5,6,0.5);
//        Separator _4Ls = new Even_Separator(_4L,10);
//
//        Radius _4P = new Circle_Angular_Radius();
//        Arclenght_Calculator _4Pc = new Polar_Arclenght_Calculator(_1P);
//        Separator _4Ps = new Arclength_Separator(_1P,_1Pc,32);
//
//        Body_Part _4BP = new Body_Part(_4L,_4Ls,_4P,_4Ps);
//
//        body.add_part(_4BP);
//
//        body.init_Grid();
//        for(int i = 2; i <= 21; i++){
//            body.curve_tail(i,(30*Math.PI/180)/20);
//        }
//
//        body.get_Grid().to_File(body_parts_storage + "/tapering_curved_body.txt");
//
//        return body;
//
//    }
}
