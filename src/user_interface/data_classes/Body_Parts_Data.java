package user_interface.data_classes;


import calculation.grid_builder.Body_Part;

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
}
