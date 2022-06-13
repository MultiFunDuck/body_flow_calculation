package radiis.angular_radius;

import math.math_primitives.Radius;

public class Elliptic_Angular_Radius extends Radius {

    public double a,b;

    public Elliptic_Angular_Radius(double a, double b){
        this.start = 0;
        this.end = 2*Math.PI;
        this.length = this.end - this.start;
        this.a = a;
        this.b = b;
    }


    @Override
    public void set_end_diameter(double diameter){}

    @Override
    public void set_start_diameter(double diameter){}


    @Override
    public double get_radius(double angle) {

        double cos_a = Math.cos(angle);
        double sin_a = Math.sin(angle);

        return 1/Math.sqrt(cos_a*cos_a/(a*a) + sin_a*sin_a/(b*b));

    }

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
                double cos_a = Math.cos(x);
                double sin_a = Math.sin(x);
                double denom = Math.sqrt(cos_a*cos_a/(a*a) + sin_a*sin_a/(b*b));
                return -(1/(a*a)+1/(b*b))*sin_a*cos_a/(denom*denom*denom);

            }

            @Override
            public void set_end_diameter(double diameter){}

            @Override
            public void set_start_diameter(double diameter){}

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
