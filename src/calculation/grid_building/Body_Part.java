package calculation.grid_building;

import math.math_primitives.Point;
import math.math_primitives.Radius;
import math.separator.Separator;

import java.util.ArrayList;
import java.util.List;

public class Body_Part {


    double start;
    double end;
    double length;
    public Radius ox_radius;
    public Separator ox_separator;
    public Radius angle_radius;
    public Separator angle_separator;
    List<List<Point>> points;

    public Body_Part(Radius ox_radius, Separator ox_separation, Radius angle_radius, Separator angle_separation) {
        this.ox_radius = ox_radius;
        this.ox_separator = ox_separation;
        this.angle_radius = angle_radius;
        this.angle_separator = angle_separation;
        this.length = ox_radius.get_length();
        this.start = ox_radius.get_start();
        this.end = ox_radius.get_end();
    }


    public void init_points_on_part(int last_x_num, int lash_hash){

        List<Double> ox_separation = ox_separator.get_separation();
        List<Double> angle_separation = angle_separator.get_separation();

        this.points = new ArrayList<>();


        for(int i = 1; i < ox_separation.size(); i++){

            double x = ox_separation.get(i);
            List<Point> buf_list = new ArrayList<>();


            for(int j = 0; j < angle_separation.size() - 1; j++){
                double angle = angle_separation.get(j);
                double radius = ox_radius.get_radius(x) * angle_radius.get_radius(angle);

                Point cur = new Point(x, radius * Math.sin(angle), radius * Math.cos(angle),last_x_num + i,j,
                        lash_hash + 1 + (i-1) * (angle_separation.size() - 1) + j);

                buf_list.add(cur);
            }
            points.add(buf_list);
        }
    }

    public void set_start(double start){
        this.start = start;
        this.ox_radius.set_start(start);
        this.ox_separator.start = start;
    }

    public void set_end(double end){
        this.end = end;
        this.ox_radius.set_end(end);
        this.ox_separator.end = end;
    }

    public void set_length(double length){

        this.end = start + length;
        this.length = length;

        this.ox_radius.set_end(start + length);

        this.ox_separator.end = start + length;
        this.ox_separator.length = length;

    }

    public void shift(double shift){

        try {
            set_end(end + shift);
            set_start(start + shift);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public double get_end_radius(){
        return this.ox_radius.get_end_radius();
    }

    public double get_start_radius(){
        return this.ox_radius.get_start_radius();
    }

    public double get_start(){ return this.start; }

    public double get_end(){ return this.end; }

    public double get_length(){ return this.end - this.start; }

    public List<Double> get_ox_separation(){
        return this.ox_separator.get_separation();
    }

    public List<Double> get_angle_separation(){
        return this.angle_separator.get_separation();
    }

    public double get_ox_radius(double x){
        return this.ox_radius.get_radius(x);
    }
}
