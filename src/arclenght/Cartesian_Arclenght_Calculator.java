package arclenght;

import Integrators.Parabola_Integrator;
import math_primitives.Radius;

public class Cartesian_Arclenght_Calculator extends Arclenght_Calculator{


    public Cartesian_Arclenght_Calculator(Radius radius) {
        super(radius);
    }


    @Override
    public double calculate_arclenght(double left_bound, double right_bound,int num_of_separations) {

        Parabola_Integrator integrator = new Parabola_Integrator(
                left_bound,
                right_bound,
                num_of_separations);

        Radius elementary_arclenght = new Radius() {
            @Override
            public double get_radius(double x) {
                double f_prime = radius.get_derivative().get_radius(x);
                return Math.sqrt(1 + f_prime*f_prime);
            }

            @Override
            public Radius get_derivative() {
                return null;
            }
        };
        elementary_arclenght.start = radius.start;
        elementary_arclenght.end = radius.end;

        return integrator.Integrate(elementary_arclenght);

    }
}
