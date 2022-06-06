package calculation.flow_calculation;

import math.math_primitives.Operator;
import math.math_primitives.Panel;
import math.math_primitives.Point;
import math.math_primitives.Vector;

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




    public Vector calculate_tau_velocity_part(Panel mid, Panel front, Panel back, Panel left, Panel right){

        Vector tau1 = null;
        try {
            tau1 = new Vector(o.diff(o.div(o.sum(mid.get_3rd_p(),mid.get_4th_p()),2),mid.middle)).get_normalized_vector();
        } catch (Exception e) {
            System.out.println("Exception while normalizing tau1 vector. Tau1.length() = 0");
            e.printStackTrace();
        }
        Vector tau2 = new Vector(0,0,0);

        try {
            if(back == null){

                double length = (mid.length() + front.length())/2;
                tau1 = o.mul(tau1, (front.gamma - mid.gamma)/length);
            }
            else if(front == null){

                double length = (back.length() + mid.length())/2;
                tau1 = o.mul(tau1,(mid.gamma - back.gamma)/length);
            }
            else{

                double length = (back.length()/2 + front.length()/2) + mid.length();
                tau1 = o.mul(tau1,(front.gamma - back.gamma) / length);
            }
        } catch (Exception e) {
            System.out.println("Exception while normalizing tau1 vector. Tau1.length() = 0");
            e.printStackTrace();
        }


        try {
            tau2 = new Vector(o.diff(right.middle, left.middle)).get_normalized_vector();
            double length = (left.length()/2 + right.length()/2) + mid.length();
            tau2 = o.mul(tau2,(right.gamma - left.gamma) / length);
        } catch (Exception e) {
            System.out.println("Exception while normalizing tau2 vector. Tau2.length() = 0");
            e.printStackTrace();
        }

        return o.div(o.sum(tau2,tau1),-2);
    }

    public Vector calculate_circular_velocity_part(Point from_which_point){

        Vector circ_velocity = new Vector(0,0,0);




        for(int i = 0; i < panels.size() - 1; i++){
            for(int j = 0; j < panels.get(0).size(); j++){

                Panel to_which_panel = panels.get(i).get(j);


                circ_velocity = o.sum(circ_velocity,
                        o.mul(mdv_solver.unit_circulation(from_which_point,to_which_panel), to_which_panel.gamma));

            }
        }

        int last = panels.size() - 1;
        Panel last_one_example = panels.get(last).get(0);

        for(int j = 0; j < panels.get(0).size() - 1; j++){

            Panel to_which = panels.get(last).get(j);

            circ_velocity = o.sum(circ_velocity,
                    o.mul(mdv_solver.unit_circulation(from_which_point,to_which), to_which.gamma));

        }

        if(!last_one_example.get_3rd_p().isEqual(last_one_example.get_4th_p())){

            for(int j = 0; j < panels.get(0).size() - 1; j++){

                Panel to_which = panels.get(last).get(j);

                circ_velocity = o.sum(circ_velocity,
                        o.mul(mdv_solver.inf_unit_circulation(from_which_point,to_which), to_which.gamma));

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
