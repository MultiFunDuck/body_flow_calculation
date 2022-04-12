package user_interface.data_classes;


import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;

import java.util.ArrayList;
import java.util.List;

public class Body_Data {

    private static Body_Data instance;

    private Body_Data(){
        parts = new ArrayList<>();
    }

    public static Body_Data getInstance(){
        if(instance == null){
            instance = new Body_Data();
        }
        return instance;
    }

    public ChangeAble_Body body;

    public List<Body_Part> parts;

}
