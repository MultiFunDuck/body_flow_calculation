package generatrix_radius;

import math_primitives.Radius;

public class Cosine_Radius extends Radius {

    double start_diameter;
    double end_diameter;

    public Cosine_Radius(double start, double end, double start_diameter, double end_diameter) {
        this.start = start;
        this.end = end;
        this.start_diameter = start_diameter;
        this.end_diameter = end_diameter;
        this.length = end - start;
    }


    @Override
    public double get_radius(double x) {


        double R = ((start_diameter - end_diameter)*Math.cos(Math.PI*(x - (start)) / length) +
                (start_diameter + end_diameter))  /  4;
        return R;
    }

}
