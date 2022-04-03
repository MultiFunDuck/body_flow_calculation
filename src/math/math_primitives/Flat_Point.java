package math.math_primitives;

public class Flat_Point{


    public double x;
    public double y;


    public Flat_Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Flat_Point(Flat_Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return "Flat_Point{" +
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

    public boolean isEqual(Flat_Point p){
        return (Math.abs(this.x - p.x) < 0.001 && Math.abs(this.y - p.y) < 0.001);
    }

    public boolean isXTheSame(Flat_Point p){
        return (Math.abs(this.x - p.x) < 0.001);
    }


}
