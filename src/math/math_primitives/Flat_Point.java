package math.math_primitives;

public class Flat_Point {


    public double x;
    public double y;

    public boolean isEqual(Flat_Point p){
        return (this.x == p.x)&&(this.y == p.y);
    }

    public Flat_Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "math.cubic_spline.math_primitives.Flat_Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
