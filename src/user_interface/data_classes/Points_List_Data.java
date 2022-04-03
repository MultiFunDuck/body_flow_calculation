package user_interface.data_classes;

import math.math_primitives.Flat_Point;

import java.util.ArrayList;
import java.util.List;

public class Points_List_Data {

    private static Points_List_Data instance;

    private Points_List_Data(){
        points = new ArrayList<>();
    }

    public static Points_List_Data getInstance(){
        if(instance == null){
            instance = new Points_List_Data();
        }
        return instance;
    }

    public boolean is_there_the_same(Flat_Point p){
        boolean is_there = false;
        for(Flat_Point point : points){
            if(point.isXTheSame(p)){
                is_there = true;
            }
        }
        return is_there;
    }

    public List<Flat_Point> points;
}
