package cubic_spline;

public class Cubic_Function {

    double start;
    double end;
    double A,B,C,D;

    public double value(double x){
        if(start <= x && x <= end){
            double x_s = x - start;
            return A * x_s * x_s * x_s  +  B * x_s * x_s  +  C * x_s  +  D;
        }
        else{
            return 0;
        }
    }

    public Cubic_Function(double start, double end, double a, double b, double c, double d) {
        this.start = start;
        this.end = end;
        A = a;
        B = b;
        C = c;
        D = d;
    }

}