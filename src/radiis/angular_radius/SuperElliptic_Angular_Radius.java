package radiis.angular_radius;

import math.math_primitives.Radius;

public class SuperElliptic_Angular_Radius extends Radius {

    public double a,b,n;

    public SuperElliptic_Angular_Radius(double a, double b, double n){
        this.start = 0;
        this.end = 2*Math.PI;
        this.length = this.end - this.start;
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public void set_end_diameter(double diameter){}

    @Override
    public void set_start_diameter(double diameter){}


    @Override
    public double get_radius(double angle) {

        double cos_a_n_pow = Math.pow(Math.abs(Math.cos(angle)) / a,n);
        double sin_a_n_pow = Math.pow(Math.abs(Math.sin(angle)) / b,n);

        return 1/Math.pow(cos_a_n_pow + sin_a_n_pow, 1/n);

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
                double cos_a_n_min_one_pow = Math.pow(Math.abs(Math.cos(x)) / a,n-1);
                double sin_a_n_min_one_pow = Math.pow(Math.abs(Math.sin(x)) / b,n-1);
                double cos_a_n_pow = Math.pow(Math.abs(Math.cos(x)) / a,n);
                double sin_a_n_pow = Math.pow(Math.abs(Math.sin(x)) / b,n);
                return Math.pow(cos_a_n_pow + sin_a_n_pow, 1/n - 1)*(cos_a*sin_a_n_min_one_pow/b - sin_a*cos_a_n_min_one_pow/a);

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
