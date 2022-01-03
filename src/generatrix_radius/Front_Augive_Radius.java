package generatrix_radius;

import math_primitives.Radius;

public class Front_Augive_Radius extends Radius {

    double diameter;

    public Front_Augive_Radius(double start, double end, double diameter) {
        this.start = start;
        this.end = end;
        this.diameter = diameter;
        this.length = end - start;
    }


    @Override
    public double get_radius(double x) {
        double x1 = start;
        double x2 = x1 + length;
        double d = diameter;
        double l = x2-x1;
        double R = (d/4 - l*l/d + Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - ((x-x1) - l)*((x-x1) - l)));

        return R;
    }


}
