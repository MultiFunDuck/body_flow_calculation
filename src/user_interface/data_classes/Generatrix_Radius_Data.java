package user_interface.data_classes;

import math.math_primitives.Radius;
import math.separator.Separator;

public class Generatrix_Radius_Data {

    private static Generatrix_Radius_Data instance;

    private Generatrix_Radius_Data() {}

    public static Generatrix_Radius_Data getInstance(){
        if(instance == null){
            instance = new Generatrix_Radius_Data();
        }
        return instance;
    }

    public Radius radius;
    public Separator separator;

}
