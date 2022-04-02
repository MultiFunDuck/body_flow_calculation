package generatrix_radius;

import math_primitives.Radius;

public class Back_Augive_Radius extends Radius {


    double diameter;

    public Back_Augive_Radius(double start, double end, double diameter) {
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
        double R = (d/4 - l*l/d + Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - (x-x1)*((x-x1))));

        return R;
    }

    @Override
    public Radius get_derivative() {
        Radius derivative = new Radius() {
            @Override
            public double get_radius(double x) {
                double x1 = start;
                double x2 = x1 + end - start;
                double d = diameter;
                double l = x2-x1;
                double denom = Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - (x-x1)*((x-x1)));

                if(denom == 0){
                    return 0;
                }else{
                    return (x1-x)/denom;
                }
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
