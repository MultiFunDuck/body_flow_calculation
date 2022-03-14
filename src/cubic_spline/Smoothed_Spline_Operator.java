package cubic_spline;

import Jama.Matrix;
import math_primitives.Flat_Point;

import java.util.ArrayList;
import java.util.List;

public class Smoothed_Spline_Operator {

    List<Flat_Point> flat_points;
    double lambda;

    public Smoothed_Spline_Operator(List<Flat_Point> flat_points, double lambda){
        this.flat_points = flat_points;
        this.lambda = lambda;
    }


    private double[] get_steps_array(){
        int length = flat_points.size()-1;
        double[] steps = new double[length];
        for(int i = 0; i < length; i++){
            steps[i] = flat_points.get(i+1).x - flat_points.get(i).x;
        }
        return steps;
    }

    private Matrix construct_D_matrix(){
        int num_of_points = flat_points.size();
        double[] steps = get_steps_array();

        Matrix matrix = new Matrix(num_of_points - 2, num_of_points);

        for(int i = 0; i < num_of_points - 2; i++){

            double r_cur = 3/steps[0];
            double r_next = 3/steps[1];

            double f_cur = -(r_cur + r_next);

            matrix.set(i,i,r_cur);
            matrix.set(i,i+1,f_cur);
            matrix.set(i,i+2,r_next);
        }
        return matrix;
    }

    private Matrix construct_B_matrix(){
        int num_of_points = flat_points.size();
        double[] steps = get_steps_array();

        Matrix matrix = new Matrix(num_of_points - 2, num_of_points - 2);
        for(int i = 0; i < num_of_points - 3; i++){
            double p_cur = 2*(steps[i+1] + steps[i]);
            matrix.set(i,i,p_cur);
            matrix.set(i,i+1, steps[i+1]);
            matrix.set(i+1, i, steps[i+1]);
        }
        double p_last = 2*(steps[num_of_points - 2] + steps[num_of_points - 3]);
        matrix.set(num_of_points - 3, num_of_points - 3, p_last);

        return matrix;
    }

    private Matrix construct_Deviation_matrix(){
        int length = flat_points.size();
        Matrix deviation = new Matrix(length,length,0);
        for(int i = 1; i < length-1; i++){
            deviation.set(i,i,1);
        }
        deviation.set(0,0,0);
        deviation.set(length-1,length-1,0);

        return deviation;
    }

    private Matrix construct_Unit_matrix(int length){
        Matrix unit = new Matrix(length,length,0);
        for(int i = 0; i < length; i++){
            unit.set(i,i,1);
        }

        return unit;
    }

    private Matrix get_y_as_column(){
        int length = flat_points.size();
        Matrix y_column = new Matrix(length,1);
        for(int i = 0; i < length; i++){
            y_column.set(i,0,flat_points.get(i).y);
        }

        return y_column;
    }

    private Matrix get_b_as_column(){

        Matrix D = construct_D_matrix();
        Matrix B = construct_B_matrix();
        Matrix S = construct_Deviation_matrix();
        Matrix y_column = get_y_as_column();
        double coef = 2*(1-lambda)/(3*lambda);

        Matrix resolving = B
                .plus(D
                        .times(S
                                .times(D
                                        .transpose()
                                        .times(coef))));



        return resolving.inverse().times(D).times(y_column);
    }

    private Matrix get_d_as_column(){
        Matrix y_column = get_y_as_column();
        Matrix D = construct_D_matrix();
        Matrix B = construct_B_matrix();
        Matrix S = construct_Deviation_matrix();
        Matrix E = construct_Unit_matrix(flat_points.size());
        double coef = 2*(1-lambda)/(3*lambda);

        Matrix resolving = E.plus(S
                .times(D.transpose()
                        .times(B.inverse()
                                .times(D)
                                .times(coef))));

        return resolving.solve(y_column);
    }

    public List<Flat_Point> smoothed_points(){
        List<Flat_Point> smoothed_points = new ArrayList<>();
        int length = flat_points.size();
        Matrix smoothed_d = get_d_as_column();

        for(int i = 0; i < length; i++){
            smoothed_points.add(new Flat_Point(flat_points.get(i).x, smoothed_d.get(i,0)));
        }

        return smoothed_points;
    }
}
