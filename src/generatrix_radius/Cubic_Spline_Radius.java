package generatrix_radius;

import cubic_spline.Cubic_Spline;
import math_primitives.Flat_Point;
import math_primitives.Radius;

import java.util.List;

public class Cubic_Spline_Radius extends Radius {

    Cubic_Spline cubic_spline;


    public Cubic_Spline_Radius(List<Flat_Point> flat_points, double left_slope, double right_slope){
        this.cubic_spline = new Cubic_Spline(flat_points,left_slope,right_slope);
        this.start = cubic_spline.start;
        this.end = cubic_spline.end;
    }

    public double get_radius(double x){

        try {
            return this.cubic_spline.value(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



}
