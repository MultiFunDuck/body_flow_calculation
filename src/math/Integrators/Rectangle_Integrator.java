package math.Integrators;

import math.math_primitives.Radius;

public class Rectangle_Integrator extends Integrator {

    public Rectangle_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Radius radius) {
        double step = (upper_bound - lower_bound)/num_of_separations;

        double sum = 0;
        for(int i = 0; i < num_of_separations; i++){
            sum += radius.get_radius(step * i);
        }

        return sum*step;
    }
}
