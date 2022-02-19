package generatrix_radius;

import math_primitives.Radius;

public class Cylinder_Radius extends Radius {

    double diameter;

    public Cylinder_Radius(double start, double end, double diameter){
        this.start = start;
        this.end = end;
        this.diameter = diameter;
        this.length = end - start;
    }

    @Override
    public double get_radius(double x) {
        return diameter/2;
    }

}