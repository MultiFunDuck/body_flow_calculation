package high_level_math;

import math_primitives.Operator;
import math_primitives.Panel;
import math_primitives.Point;
import math_primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Velocity_Calculator {

    public Velocity_Calculator(List<List<Panel>> panels, Vector V_inf){
        this.mdv_solver = new MDV_Solver(panels,V_inf);
        this.panels = panels;
        this.V_inf = V_inf;
    }


    MDV_Solver mdv_solver;
    List<List<Panel>> panels;
    Vector V_inf;
    Operator o = Operator.getInstance();



    public Vector calculate_flow_velocity(Panel mid, Panel front, Panel back, Panel left, Panel right){

        Vector circ_velocity_part = calculate_circular_velocity_part(mid);

        Vector tau_part  = calculate_tau_velocity_part(mid,front,back,left,right);


        Vector flow_velocity = o.sum(V_inf,tau_part);
        flow_velocity = o.sum(flow_velocity,circ_velocity_part);


        return flow_velocity;

    }

    public Vector calculate_tau_velocity_part(Panel mid, Panel front, Panel back, Panel left, Panel right){

        Vector tau1 = new Vector(0,0,0);
        Vector tau2 = new Vector(0,0,0);

        try {
            if(back == null){
                tau1 = new Vector(o.diff(front.middle,mid.middle));
                double length = tau1.length();
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1, (front.gamma - mid.gamma)/length);
            }
            else if(front == null){
                tau1 = new Vector(o.diff(mid.middle,back.middle));
                double length = tau1.length();
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1,(mid.gamma - back.gamma)/length);
            }
            else{
                tau1 = new Vector(o.diff(front.middle,back.middle));
                double length = tau1.length();
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1,(front.gamma - back.gamma) / (2*length));
            }
        } catch (Exception e) {
            System.out.println("Exception while normalizing tau1 vector. Tau1.length() = 0");
            e.printStackTrace();
        }


        try {
            tau2 = new Vector(o.diff(right.middle, left.middle));
            tau2 = tau2.get_normalized_vector();
            tau2 = o.mul(tau2,(right.gamma - left.gamma) / 2);
        } catch (Exception e) {
            System.out.println("Exception while normalizing tau2 vector. Tau2.length() = 0");
            e.printStackTrace();
        }

        return o.div(o.sum(tau2,tau1),-1);
    }

    public Vector calculate_circular_velocity_part(Panel from_which){

        Vector circ_velocity = new Vector(0,0,0);
        Point from_which_point = from_which.middle;

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                circ_velocity = o.sum(
                        circ_velocity,
                        o.mul(mdv_solver.unit_circulation(
                                        from_which_point,panels.get(i).get(j)),
                                        panels.get(i).get(j).gamma)
                );


            }
        }
        return circ_velocity;
    }




    public double calculate_normal_velocity(Panel panel){
        Vector flow_velocity = panel.flow_velocity;
        Vector normal = panel.normal;

        return o.scalar_mul(normal,flow_velocity);
    }

    public double calculate_velocity_module(Panel panel){
        Vector flow_velocity = panel.flow_velocity;

        return Math.sqrt(o.scalar_mul(flow_velocity,flow_velocity));
    }
}
