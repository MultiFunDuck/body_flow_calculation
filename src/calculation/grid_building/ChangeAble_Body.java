package calculation.grid_building;

import math.math_primitives.Operator;
import math.math_primitives.Point;

import java.util.List;

public class ChangeAble_Body extends Body{

    Operator o = new Operator();

    public void curve_tail(int num_of_segment, double attack_angle, double curvature_radius){
        curve_segment(this.Parts.get(num_of_segment),attack_angle, curvature_radius);
        line_up_others(num_of_segment,attack_angle);

        this.grid.panels = this.init_panels(this.grid.points);
    }



    private void curve_segment(Body_Part segment, double attack_angle, double curvature_radius){


        List<List<Point>> segment_points = segment.points;

        for(int i = 0; i < segment_points.size(); i++){

            for(int j = 0; j < segment_points.get(0).size(); j++){

                curve_segment_point(segment_points.get(i).get(j),segment,attack_angle, curvature_radius);
            }
        }

    }

    private void curve_segment_point(Point cur, Body_Part curving_part, double attack_angle, double curvature_radius){

        double end_tail_diam = 2*curving_part.get_end_radius();
        double start = curving_part.start;

        if(end_tail_diam == 0){
            end_tail_diam = 0.001;
        }



        double R = curvature_radius;
        double a = attack_angle;
        double arcsh_arg = end_tail_diam * Math.tan(a) / ((2*R) * (Math.sin(a) + Math.tan(a)*(1 - Math.cos(a))));
        double P = end_tail_diam / (2*(Math.log(arcsh_arg + Math.sqrt(arcsh_arg*arcsh_arg + 1))));

        double x = cur.x;
        double z = cur.z;

        if(x - start < R*a){
            cur.x = start + R*Math.exp( -z/P) * Math.sin( (x - start) / R );
            cur.z = z + R*Math.exp( -z/P) * (1 - Math.cos( (x - start) / R ));
        }
        else{

            cur.x = start + R*Math.sin(a) + (x - start - R*a)*Math.cos(a) - z*Math.sin(a);
            cur.z = (1 - Math.cos(a))*R + (x - start - R*a)*Math.sin(a) + z*Math.cos(a);

        }

    }


    private void line_up_others(int which_after, double angle){
        for(int i = which_after + 1; i < Parts.size(); i++){
            Body_Part cur = Parts.get(i);
            Body_Part previous = Parts.get(i-1);
            line_up_segment(cur, previous, angle);


        }
    }

    private void line_up_segment(Body_Part segment, Body_Part which_with, double angle){



        Point connection_point = o.get_mid_point(which_with.points.get(which_with.points.size() - 1));
        Point segment_start_mid = o.get_mid_point(segment.points.get(0));


        double x_diff = segment_start_mid.x - which_with.end;



        double x_shift = connection_point.x - segment_start_mid.x + x_diff * Math.cos(angle);
        double y_shift = 0;
        double z_shift = connection_point.z - segment_start_mid.z + x_diff * Math.sin(angle);


        shift_segment(segment,x_shift, y_shift, z_shift);
        rotate_segment(segment, angle);

        //Used to check if normal vectors match in lining up segments
        /*
        List<List<Point>> segment_points = segment.points;
        List<Point> last = segment_points.get(0);
        Point p1 = last.get(0);
        Point p2 = last.get(1);
        Point p3 = last.get(3);
        Point p4 = last.get(4);
        System.out.println(new Panel(p1,p2,p3,p4).normal.toString());*/
    }

    private void rotate_segment(Body_Part segment, double angle){

        Point segment_start_mid = o.get_mid_point(segment.points.get(0));
        List<List<Point>> points = segment.points;

        double x_shift = segment_start_mid.x;
        double z_shift = segment_start_mid.z;

        for(int i = 0; i < points.size(); i++){

            for(int j = 0; j < points.get(0).size(); j++){

                double x = points.get(i).get(j).x;
                double z = points.get(i).get(j).z;

                points.get(i).get(j).x = x_shift + (x - x_shift) * Math.cos(angle) - (z - z_shift) * Math.sin(angle);
                points.get(i).get(j).z = z_shift + (z - z_shift) * Math.cos(angle) + (x - x_shift) * Math.sin(angle);

            }

        }
    }

    private void shift_segment(Body_Part segment, double x, double y, double z){

        List<List<Point>> points = segment.points;

        for(int i = 0; i < points.size(); i++){

            for(int j = 0; j < points.get(0).size(); j++){

                points.get(i).get(j).x += x;
                points.get(i).get(j).y += y;
                points.get(i).get(j).z += z;

            }

        }

    }


}
