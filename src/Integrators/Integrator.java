package Integrators;

import math_primitives.Radius;

public abstract class Integrator {

    double upper_bound, lower_bound;
    int num_of_separations;

    public Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        this.upper_bound = upper_bound;
        this.lower_bound = lower_bound;
        this.num_of_separations = num_of_separations;
    }


    public abstract double Integrate(Radius radius);
}
