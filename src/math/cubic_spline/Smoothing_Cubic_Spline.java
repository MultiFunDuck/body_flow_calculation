package math.cubic_spline;

import math.math_primitives.Flat_Point;

import java.util.List;

public class Smoothing_Cubic_Spline extends Cubic_Spline{

    double lambda;

    public Smoothing_Cubic_Spline(List<Flat_Point> flat_points, double left_slope, double right_slope, double lambda){
        super(
                new Smoothed_Spline_Operator(flat_points,lambda).smoothed_points(),
                left_slope,
                right_slope);

        this.lambda = lambda;
    }


}
