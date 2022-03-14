package cubic_spline;

public class Cubic_Function {

    public double start;
    public double end;
    public double A,B,C,D;

    public double value(double x){
        if(start <= x && x <= end){
            double x_s = x - start;
            return A * x_s * x_s * x_s  +  B * x_s * x_s  +  C * x_s  +  D;
        }
        else{
            return 0;
        }
    }

    public double first_derivative(double x){
        if(start <= x && x <= end){
            double x_s = x - start;
            return 3 * A * x_s * x_s  +  2 * B * x_s  +  C;
        }
        else{
            return 0;
        }
    }

    public double second_derivative(double x){
        if(start <= x && x <= end){
            double x_s = x - start;
            return 6 * A * x_s  +  2 * B;
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