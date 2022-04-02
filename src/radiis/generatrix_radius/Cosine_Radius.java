package radiis.generatrix_radius;

import math.math_primitives.Radius;

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

    @Override
    public Radius get_derivative() {
        Radius derivative = new Radius() {
            @Override
            public double get_radius(double x) {
                double R = -Math.PI*(start_diameter - end_diameter)*Math.sin(Math.PI*(x - (start)) / (end-start))/4;

                return R;
            }

            @Override
            public Radius get_derivative() {
                return null;
            }
        };

        derivative.start = start;
        derivative.end = end;
        return derivative;
    }

}
