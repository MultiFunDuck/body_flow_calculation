package grid_builder;


import math_primitives.Panel;
import math_primitives.Point;

import java.util.ArrayList;
import java.util.List;

public class Body {


    List<Body_Part> Parts;
    Grid grid;

    public void add_part(Body_Part part){
        if(Parts == null){
            Parts = new ArrayList<>();
        }
        else{
            Parts.get(Parts.size() - 1).end = part.start;
        }

        Parts.add(part);
    }

    public Grid get_Grid(){
        this.grid.panels = init_panels(this.grid.points);
        return this.grid;
    }

    public void init_Grid(){
        List<List<Point>> points = init_points();
        List<List<Panel>> panels = init_panels(points);
        this.grid = new Grid(points,panels);
    }

    private List<List<Point>> init_points(){
        List<List<Point>> points = init_first_circle_of_points();


        for(int i = 0; i < Parts.size(); i++){
            int ox_points_size = points.size();
            int angle_points_size = points.get(0).size();


            int last_x_num = points.get(ox_points_size - 1).get(0).line_num;
            int last_hash = points.get(ox_points_size - 1).get(angle_points_size - 1).hash;

            Parts.get(i).init_points_on_part(last_x_num,last_hash);
            points.addAll(Parts.get(i).points);
        }

        return points;
    }

    private List<List<Point>> init_first_circle_of_points(){
        Body_Part first_part = Parts.get(0);

        List<List<Point>> first_circle = new ArrayList<>();
        List<Double> angle_separation = first_part.angle_separator.get_separation();


        for(int i = 0; i < 1; i++){
            List<Point> buf_list = new ArrayList<>();

            for(int j = 0; j < angle_separation.size() - 1; j++){
                Point cur = new Point(first_part.start,0,0,0,j,1 + j);
                buf_list.add(cur);
            }
            first_circle.add(buf_list);
        }
        return first_circle;
    }

    public List<List<Panel>> init_panels(List<List<Point>> points){
        List<List<Panel>> panels = new ArrayList<>();
        int ox_size = points.size();
        int angle_size = points.get(0).size();

        for(int i = 0; i < ox_size - 1; i++){
            List<Panel> buf_list = new ArrayList<>();

            for(int j = 0; j < angle_size; j++){

                Panel cur = new Panel(points.get(i).get(j),
                        points.get(i).get((j + 1)%angle_size),
                        points.get(i + 1).get((j + 1)%angle_size),
                        points.get(i + 1).get(j));

                buf_list.add(cur);
            }
            panels.add(buf_list);
        }
        return panels;
    }

    public boolean are_parts_connected(double precision){
        return (are_x_matches(precision) && are_y_matches(precision));
    }

    private boolean are_x_matches(double precision){
        boolean flag = true;
        for(int i = 0; i < Parts.size() - 1; i++){
            if(Math.abs(Parts.get(i).end - Parts.get(i+1).start) > precision){
                flag = false;
            }
        }
        return flag;
    }

    private boolean are_y_matches(double precision){
        boolean flag = true;
        for(int i = 0; i < Parts.size() - 1; i++){
            if(Math.abs(Parts.get(i).get_end_radius() - Parts.get(i+1).get_start_radius()) > precision){
                flag = false;
            }
        }
        return flag;
    }
}
