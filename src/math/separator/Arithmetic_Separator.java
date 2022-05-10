package math.separator;

import math.arclenght.Arclenght_Calculator;
import math.math_primitives.Radius;

import java.util.ArrayList;
import java.util.List;

public class Arithmetic_Separator extends Separator{

    Radius radius;
    Arclenght_Calculator calculator;

    public Arithmetic_Separator(Radius radius, Arclenght_Calculator calculator, int num_of_separations) {
        this.radius = radius;
        this.start = radius.start;
        this.end = radius.end;
        this.length = this.end - this.start;
        this.num_of_separations = num_of_separations;

        this.calculator = calculator;
    }


    @Override
    public List<Double> get_separation() {
        //Precision for separation is hard-coded
        double precision = 0.0001;

        List<Double> separation = new ArrayList<>();
        separation.add(start);

        double full_arc = calculator.calculate_arclenght_precisely(start,end,precision);
        double arc_part = 2*full_arc/(num_of_separations*(num_of_separations + 1));
        double buf_arc = 0;
        double step = precision/2;

        for (int i = 0; i < num_of_separations - 1; i++) {
            double cur_x = separation.get(i);
            double direction = step;
            do{
                cur_x += direction;
                buf_arc = calculator.calculate_arclenght_precisely(separation.get(i),cur_x,precision);
                if(buf_arc > arc_part){
                    direction = -direction/2;
                }
            }while(Math.abs(arc_part-buf_arc)>precision);
            separation.add(cur_x);
            arc_part *= (double)(i+2)/(double)(i+1);
        }
        separation.add(end);

        return separation;
    }

}