package generatrix_radius;

import cubic_spline.Cubic_Spline;
import cubic_spline.Smoothing_Cubic_Spline;
import math_primitives.Flat_Point;
import math_primitives.Radius;

import java.util.List;

public class Smoothed_Cubic_Spline_Radius extends Radius {

    Cubic_Spline smoothed_cubic_spline;


    public Smoothed_Cubic_Spline_Radius(List<Flat_Point> flat_points, double left_slope, double right_slope, double lambda){
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