package math.separator;

import math.math_primitives.Radius;

import java.util.ArrayList;
import java.util.List;

public class Even_Separator extends Separator{

    Radius radius;

    public Even_Separator(Radius radius, int num_of_separations){
        this.radius = radius;
        this.start = radius.start;
        this.end = radius.end;
        this.length = this.end - this.start;
        this.num_of_separations = num_of_separations;
    }


    @Override
    public List<Double> get_separation(){
        List<Double> separation = new ArrayList<>();
        double start = radius.get_start();
        double end = radius.get_end();

        double step = (end - start)/num_of_separations;


        for(int i = 0; i <= num_of_separations; i++){
            separation.add(start + i*step);
        }
        return separation;
    }

}
