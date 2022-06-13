package radiis.angular_radius;

import math.math_primitives.Radius;

public class Circle_Angular_Radius extends Radius {

    public Circle_Angular_Radius(){
        this.start = 0;
        this.end = 2*Math.PI;
        this.length = this.end - this.start;
    }

    @Override
    public double get_radius(double angle) {
        return 1;
    }


    @Override
    public void set_end_diameter(double diameter){}

    @Override
    public void set_start_diameter(double diameter){}


    @Override
    public double get_start() {
        return 0;
    }

    @Override
    public double get_end() {
        return 2*Math.PI;
    }

    @Override
    public Radius get_derivative() {
        Radius derivative = new Radius() {
            @Override
            public double get_radius(double x) {

                return 0;

            }

            @Override
            public void set_end_diameter(double diameter) {}

            @Override
            public void set_start_diameter(double diameter) {}

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
