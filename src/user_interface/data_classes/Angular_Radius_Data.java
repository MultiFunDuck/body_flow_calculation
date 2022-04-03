package user_interface.data_classes;

import math.math_primitives.Radius;
import math.separator.Separator;

public class Angular_Radius_Data {

    private static Angular_Radius_Data instance;

    private Angular_Radius_Data(){}

    public static Angular_Radius_Data getInstance(){
        if(instance == null){
            instance = new Angular_Radius_Data();
        }
        return instance;
    }

    public Radius radius;
    public Separator separator;

}
