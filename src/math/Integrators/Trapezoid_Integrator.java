package math.Integrators;

import math.math_primitives.Radius;

public class Trapezoid_Integrator extends Integrator {

    public Trapezoid_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Radius radius) {
        double step = (upper_bound - lower_bound)/num_of_separations;

        double sum = (radius.get_radius(lower_bound) + radius.get_radius(upper_bound))/2;
        for(int i = 1; i < num_of_separations; i++){
            sum += radius.get_radius(lower_bound + step * i);
        }

        return sum*step;
    }
}
