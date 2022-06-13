package radiis.generatrix_radius;

import math.math_primitives.Radius;

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
                return - Math.tan(Math.PI*(half_of_opening_angle)/180);
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
