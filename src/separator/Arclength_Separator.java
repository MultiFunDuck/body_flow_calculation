package separator;

import math_primitives.Radius;

import java.util.List;

public class Arclength_Separator extends Separator{

    Radius radius;

    public Arclength_Separator(Radius radius){
        this.radius = radius;
    }

    @Override
    public List<Double> get_separation(){
        return null;
    }

}
