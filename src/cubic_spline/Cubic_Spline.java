package cubic_spline;


import math_primitives.Flat_Point;

import java.util.ArrayList;
import java.util.List;

public class Cubic_Spline {

    public List<Cubic_Function> functions;
    List<Flat_Point> flat_points;
    double left_slope;
    double right_slope;
    public double start;
    public double end;


    public Cubic_Spline(List<Flat_Point> flat_points, double left_slope, double right_slope){
        this.flat_points = flat_points;
        this.left_slope = left_slope;
        this.right_slope = right_slope;
        this.start = flat_points.get(0).x;
        this.end = flat_points.get(flat_points.size()-1).x;
        build_spline();
    }

    private void build_spline(){

        double left_curv_0 = 0;
        double left_curv_1 = 1;
        double right_slope_0 = right_slope(left_curv_0);
        double right_slope_1 = right_slope(left_curv_1);
        double left_curvature = left_curv_0 + (right_slope - right_slope_0) * (left_curv_1 - left_curv_0) /
                                                                             (right_slope_1 - right_slope_0);



        functions = new ArrayList<Cubic_Function>();
        double first_diff = flat_points.get(1).x - flat_points.get(0).x;
        double D = this.flat_points.get(0).y;
        double C = left_slope;
        double B = left_curvature;
        double A =
                this.flat_points.get(1).y / (first_diff * first_diff * first_diff)
                        - B / (first_diff)
                        - C / (first_diff * first_diff)
                        - D / (first_diff * first_diff * first_diff);


        Cubic_Function first_cubic = new Cubic_Function(
                this.flat_points.get(0).x,
                this.flat_points.get(1).x,
                A,B,C,D);

        functions.add(first_cubic);



        for(int i = 1; i < this.flat_points.size() - 1; i++){
            Flat_Point prev_point = flat_points.get(i-1);
            Flat_Point cur_point = flat_points.get(i);
            Flat_Point next_point = flat_points.get(i+1);

            double h_cur = cur_point.x - prev_point.x;
            double h_next = next_point.x - cur_point.x;


            double D_next = cur_point.y;
            double C_next = 3*A * h_cur * h_cur  +  2*B * h_cur  +  C;
            double B_next = 3*A * h_cur   +  B;
            double A_next =
                    next_point.y / (h_next * h_next * h_next) -
                            B_next / (h_next) -
                            C_next / (h_next * h_next) -
                            D_next / (h_next * h_next * h_next);

            functions.add(new Cubic_Function(cur_point.x, next_point.x, A_next, B_next, C_next, D_next));

            A = A_next;
            B = B_next;
            C = C_next;
            D = D_next;
        }
    }

    private double right_slope(double left_curvature){

        double first_diff = flat_points.get(1).x - flat_points.get(0).x;
        double D = this.flat_points.get(0).y;
        double C = left_slope;
        double B = left_curvature;
        double A =
                this.flat_points.get(1).y / (first_diff * first_diff * first_diff)
                - B / (first_diff)
                - C / (first_diff * first_diff)
                - D / (first_diff * first_diff * first_diff);



        for(int i = 1; i < this.flat_points.size() - 1; i++){
            Flat_Point prev_point = flat_points.get(i-1);
            Flat_Point cur_point = flat_points.get(i);
            Flat_Point next_point = flat_points.get(i+1);

            double h_cur = cur_point.x - prev_point.x;
            double h_next = next_point.x - cur_point.x;


            double D_next = cur_point.y;
            double C_next = 3*A * h_cur * h_cur  +  2*B * h_cur  +  C;
            double B_next = 3*A * h_cur   +  B;
            double A_next =
                    next_point.y / (h_next * h_next * h_next) -
                    B_next / (h_next) -
                    C_next / (h_next * h_next) -
                    D_next / (h_next * h_next * h_next);

            A = A_next;
            B = B_next;
            C = C_next;
            D = D_next;
        }

        int last_num = flat_points.size() - 1;
        double x_diff = flat_points.get(last_num).x - flat_points.get(last_num - 1).x;

        return 3*A * x_diff * x_diff  +  2*B * x_diff  +  C;
    }

    public double value(double x) throws Exception{
        if(functions.get(0).start > x ||  x > functions.get(functions.size()-1).end){
            throw new Exception("Trying to get Cubic_Spline_radius for point x = " + x + "which is out of Spline bounds");
        }

        for(int i = 0; i < functions.size(); i++){
            if(functions.get(i).start <= x && x <= functions.get(i).end){
                return functions.get(i).value(x);
            }
        }

        return 0;
    }



}
