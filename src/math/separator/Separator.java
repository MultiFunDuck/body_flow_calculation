package math.separator;

import math.math_primitives.Radius;

import java.util.List;

public abstract class Separator {

    public Radius radius;
    public double start;
    public double end;
    public double length;
    public int num_of_separations;

    public abstract List<Double> get_separation();

    public void set_num_of_separations(int num_of_separations){
        this.num_of_separations = num_of_separations;
    }
}
