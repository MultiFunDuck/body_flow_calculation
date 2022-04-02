package angular_radius;

import math_primitives.Radius;

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
    public double get_start() {
        return 0;
    }

    @Override
    public double get_end() {
        return 2*Math.PI;
    }

    @Override
    public Radius get_derivative() {
        return null;
    }


}
