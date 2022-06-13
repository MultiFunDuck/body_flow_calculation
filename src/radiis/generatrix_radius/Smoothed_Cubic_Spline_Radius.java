package radiis.generatrix_radius;

import math.cubic_spline.Cubic_Spline;
import math.cubic_spline.Smoothing_Cubic_Spline;
import math.math_primitives.Flat_Point;
import math.math_primitives.Radius;

import java.util.List;

public class Smoothed_Cubic_Spline_Radius extends Radius {

    Cubic_Spline smoothed_cubic_spline;
    List<Flat_Point> flat_points;
    double left_slope, right_slope, lambda;


    public Smoothed_Cubic_Spline_Radius(List<Flat_Point> flat_points, double left_slope, double right_slope, double lambda){
        this.flat_points = flat_points;
        this.smoothed_cubic_spline = new Smoothing_Cubic_Spline(flat_points,left_slope,right_slope,lambda);
        this.start = smoothed_cubic_spline.start;
        this.end = smoothed_cubic_spline.end;
        this.left_slope = left_slope;
        this.right_slope = right_slope;
        this.lambda = lambda;
    }

    @Override
    public void set_start_diameter(double diameter) {
        this.flat_points.get(0).y = diameter/2;
        this.smoothed_cubic_spline = new Smoothing_Cubic_Spline(flat_points,left_slope,right_slope,lambda);
        this.start = smoothed_cubic_spline.start;
        this.end = smoothed_cubic_spline.end;
    }

    @Override
    public void set_end_diameter(double diameter) {
        this.flat_points.get(flat_points.size() - 1).y = diameter/2;
        this.smoothed_cubic_spline = new Smoothing_Cubic_Spline(flat_points,left_slope,right_slope,lambda);
        this.start = smoothed_cubic_spline.start;
        this.end = smoothed_cubic_spline.end;
    }

    public double get_radius(double x){

        try {
            return this.smoothed_cubic_spline.value(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Radius get_derivative() {

        Radius derivative = new Radius() {

            @Override
            public double get_radius(double x) {
                try {
                    return smoothed_cubic_spline.first_derivative(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }

            @Override
            public void set_start_diameter(double diameter){}

            @Override
            public void set_end_diameter(double diameter) {}

            @Override
            public Radius get_derivative() {
                Radius another_derivative = new Radius() {
                    @Override
                    public double get_radius(double x) {
                        try {
                            return smoothed_cubic_spline.second_derivative(x);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }

                    @Override
                    public Radius get_derivative() {
                        return null;
                    }

                    @Override
                    public void set_end_diameter(double diameter) {}

                    @Override
                    public void set_start_diameter(double diameter) {}
                };

                try {
                    another_derivative.set_start(smoothed_cubic_spline.start);
                    another_derivative.set_end(smoothed_cubic_spline.end);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return another_derivative;
            }
        };

        try {
            derivative.set_start(smoothed_cubic_spline.start);
            derivative.set_end(smoothed_cubic_spline.end);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return derivative;
    }


}
