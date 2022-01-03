package math_primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Panel {

    Operator o = Operator.getInstance();
    public List<Point> points;
    public Point middle;
    public Vector normal;
    public int hash;


    public double normal_velocity, pressure, dimless_pressure, gamma;
    Vector flow_velocity;

    public Panel(List<Point> points) {
        this.points = points;
        this.hash = points.get(0).hash;
        normal = o.weight_normal_vector(points);
    }

    public Panel(Point p1, Point p2, Point p3, Point p4){
        this.points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        normal = o.weight_normal_vector(points);
        this.hash = p1.hash;
    }



    @Override
    public String toString() {
        return "Panel{" +
                "middle=" + middle +
                ", hash=" + hash +
                ", gamma=" + gamma +
                '}';
    }

    public String panelToFile(){
        return this.hash + " " + this.points.get(0).hash + " " + this.points.get(1).hash + " " + this.points.get(2).hash + " " + this.points.get(3).hash + "\n";
    }


    public Point get_1st_p(){
        return this.points.get(0);
    }
    public Point get_2nd_p(){
        return this.points.get(1);
    }
    public Point get_3rd_p(){
        return this.points.get(2);
    }
    public Point get_4th_p(){
        return this.points.get(3);
    }

}
