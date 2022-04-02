package math.arclenght;

import math.math_primitives.Radius;

public abstract class Arclenght_Calculator {

    Radius radius;

    public Arclenght_Calculator(Radius radius){
        this.radius = radius;
    }

    public double calculate_arclenght_precisely(double left_bound, double right_bound, double precision){

        double arclenght;
        double buf_arclenght = 0;
        int steps_num = 2;

        do {
            arclenght = calculate_arclenght(left_bound,right_bound,steps_num);
            if (Math.abs(arclenght - buf_arclenght) > precision) {
                steps_num *= 2;
                buf_arclenght = arclenght;
            } else {
                break;
            }
        } while (true);

        return arclenght;

    }

    public abstract double calculate_arclenght(double left_bound, double right_bound,int num_of_separations);
}
