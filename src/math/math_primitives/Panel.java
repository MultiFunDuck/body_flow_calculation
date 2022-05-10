package math.math_primitives;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Panel {

    Operator o = Operator.getInstance();
    public List<Point> points;
    public Point middle;



    public Vector normal;
    public int hash;


    public double normal_velocity, velocity_module, pressure, dimless_pressure, gamma;
    public Vector circ_velocity, tau_velocity, flow_velocity;

    public Panel(List<Point> points) {
        this.points = points;
        this.hash = points.get(0).hash;
        normal = o.weight_normal_vector(points);
        middle = o.get_mid_point(points);
    }

    public Panel(Point p1, Point p2, Point p3, Point p4){
        this.points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        middle = o.get_mid_point(points);
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
        return this.hash
                + " " + this.points.get(0).hash
                + " " + this.points.get(1).hash
                + " " + this.points.get(2).hash
                + " " + this.points.get(3).hash
                + "\n";
    }

    public String panelWithDataToFile(){
        Operator o = Operator.getInstance();
        Vector V0 = o.diff(flow_velocity, tau_velocity);
        Vector Vplus = o.sum(V0,tau_velocity);
        Vector Vminus = o.diff(V0,tau_velocity);

        NumberFormat formatter = new DecimalFormat("#0.00000000");
        return this.hash
                + " " + this.points.get(0).hash
                + " " + this.points.get(1).hash
                + " " + this.points.get(2).hash
                + " " + this.points.get(3).hash
                + " " + formatter.format(gamma)
                + " " + formatter.format(normal_velocity)


                + " " + formatter.format(flow_velocity.x)
                + " " + formatter.format(flow_velocity.z)
                + " " + formatter.format(flow_velocity.y)
                + " " + formatter.format(velocity_module)

                + " " + formatter.format(tau_velocity.x)
                + " " + formatter.format(tau_velocity.z)
                + " " + formatter.format(tau_velocity.y)
                + " " + formatter.format(tau_velocity.length())

                + " " + formatter.format(circ_velocity.x)
                + " " + formatter.format(circ_velocity.z)
                + " " + formatter.format(circ_velocity.y)
                + " " + formatter.format(circ_velocity.length())


                + " " + formatter.format(V0.x)
                + " " + formatter.format(V0.z)
                + " " + formatter.format(V0.y)
                + " " + formatter.format(V0.length())


                + " " + formatter.format(Vplus.x)
                + " " + formatter.format(Vplus.z)
                + " " + formatter.format(Vplus.y)
                + " " + formatter.format(Vplus.length())


                + " " + formatter.format(Vminus.x)
                + " " + formatter.format(Vminus.z)
                + " " + formatter.format(Vminus.y)
                + " " + formatter.format(Vminus.length())


                + " " + formatter.format(this.dimless_pressure)
                + " " + formatter.format(this.dimless_pressure)
                + " " + formatter.format(1 - (1-dimless_pressure) * Vminus.length()*Vminus.length() / (Vplus.length()*Vplus.length()))
                + " " + formatter.format(this.pressure)
                + "\n";
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

    public double width(){
        double back = new Vector(o.diff(get_1st_p(),get_2nd_p())).length();
        double front = new Vector(o.diff(get_4th_p(),get_3rd_p())).length();
        return (front + back)/2;
    }

    public double length(){
        double left = new Vector(o.diff(get_1st_p(),get_4th_p())).length();
        double right = new Vector(o.diff(get_2nd_p(),get_3rd_p())).length();
        return (right + left)/2;
    }


}
