package math.math_primitives;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Point {

    public double x,y,z;
    public int line_num, angle_num, hash;


    public Point(double x, double y, double z, int line_num, int angle_num, int hash) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.line_num = line_num;
        this.angle_num = angle_num;
        this.hash = hash;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle_num = -1;
        this.line_num = -1;
        this.hash = -1;
    }

    public String pointToFile(){

        NumberFormat formatter = new DecimalFormat("#0.0000");
        return this.hash + " " + formatter.format(this.x) + " " + formatter.format(this.z) + " " + formatter.format(this.y) + "\n";
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", line_num=" + line_num +
                ", angle_num=" + angle_num +
                ", hash=" + hash +
                '}';
    }


}
