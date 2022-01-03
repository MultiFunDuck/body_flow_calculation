package generatrix_radius;

import math_primitives.Radius;

public class Conic_Radius extends Radius {

    double diameter;
    double half_of_opening_angle;

    public Conic_Radius(double start, double end, double diameter, double half_of_opening_angle){
        this.start = start;
        this.end = end;
        this.diameter = diameter;
        this.half_of_opening_angle = half_of_opening_angle;
        this.length = end - start;
    }

    @Override
    public double get_radius(double x) {

        double x1 = start;
        double R = diameter/2*(length - (x-x1))/length + (diameter/2 - length*Math.tan(Math.PI*(half_of_opening_angle)/180))*(x-x1)/length;
        return R;
    }


}
