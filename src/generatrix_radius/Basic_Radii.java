package generatrix_radius;

public class Basic_Radii {

    public double FrontAugiveRadius(double start_of_augive, double end_of_augive, double body_diameter, double x){
        double x1 = start_of_augive;
        double x2 = end_of_augive;
        double d = body_diameter;
        double l = x2-x1;
        double R = (d/4 - l*l/d + Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - ((x-x1) - l)*((x-x1) - l)));

        return R;
    }

    public double CylinderRadius(double body_diameter){
        return body_diameter/2;
    }

    public double ConicRadius(double start_of_cone, double x, double body_diameter, double half_of_opening_angle){
        double x1 = start_of_cone;
        double R = (body_diameter/2 - (x-x1)*Math.tan(Math.PI*half_of_opening_angle/180));
        return R;
    }

    public double CosineRadius(double start_of_cosine, double end_of_cosine, double start_diameter, double end_diameter,  double x){


        double length = end_of_cosine - start_of_cosine;
        double shift = start_of_cosine;
        double R = ((start_diameter - end_diameter)*Math.cos(Math.PI*(x - (shift)) / length) +
                      (start_diameter + end_diameter))  /  4;
        return R;
    }

    public double BackAugiveRadius(double start_of_augive, double end_of_augive, double body_diameter, double x){
        double x1 = start_of_augive;
        double x2 = end_of_augive;
        double d = body_diameter;
        double l = x2-x1;
        double R = (d/4 - l*l/d + Math.sqrt((d/4 + l*l/d)*(d/4 + l*l/d) - (x-x1)*((x-x1))));

        return R;
    }

}
