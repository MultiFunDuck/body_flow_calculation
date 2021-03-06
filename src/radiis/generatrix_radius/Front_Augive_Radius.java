package radiis.generatrix_radius;

import math.math_primitives.Radius;

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

    @Override
    public void set_start_diameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void set_end_diameter(double diameter) {

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
                double denom = Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - ((x-x1) - l)*((x-x1) - l));

                if(denom == 0){
                    return 0;
                }else{
                    return (l + x1 - x)/denom;
                }
            }

            @Override
            public Radius get_derivative() {
                return null;
            }

            @Override
            public void set_end_diameter(double diameter) {}

            @Override
            public void set_start_diameter(double diameter) {}
        };

        derivative.start = start;
        derivative.end = end;
        return derivative;
    }


}
