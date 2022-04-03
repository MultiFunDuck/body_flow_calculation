package user_interface.data_classes;


import calculation.grid_builder.Body_Part;
import math.math_primitives.Radius;
import math.separator.Separator;

import java.util.ArrayList;
import java.util.List;

public class Body_Parts_Data {

    private static Body_Parts_Data instance;

    private Body_Parts_Data(){
        parts = new ArrayList<>();
    }

    public static Body_Parts_Data getInstance(){
        if(instance == null){
            instance = new Body_Parts_Data();
        }
        return instance;
    }

    public List<Body_Part> parts;

    public List<Radius> radii;

    public List<Separator> separators;
}
