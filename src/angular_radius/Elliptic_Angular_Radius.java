package angular_radius;

import math_primitives.Radius;

public class Elliptic_Angular_Radius extends Radius {

    public Elliptic_Angular_Radius get_Instance(){
        return new Elliptic_Angular_Radius();
    }

    private Elliptic_Angular_Radius(){
        this.start = 0;
        this.end = 2*Math.PI;
        this.length = this.end - this.start;
    }

    @Override
    public double get_radius(double angle) {
        return Math.sin(4*angle);
    }

    @Override
    public double get_start() {
        return 0;
    }

    @Override
    public double get_end() {
        return 2*Math.PI;
    }

}
