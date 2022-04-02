package Integrators;

import math_primitives.Radius;

public class Parabola_Integrator extends Integrator {

    public Parabola_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Radius radius) {


        double step = (upper_bound - lower_bound)/(2*num_of_separations);

        double sum = (radius.get_radius(lower_bound) + radius.get_radius(upper_bound));
        for(int i = 1; i < num_of_separations; i++){
            sum += 2*radius.get_radius(lower_bound + step * (2*i));
        }

        for(int i = 1; i <= num_of_separations; i++){
            sum += 4*radius.get_radius( lower_bound + step * (2*i - 1));
        }

        return sum*step/3;
    }

}
