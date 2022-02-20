package high_level_math;

import Jama.Matrix;
import math_primitives.Operator;
import math_primitives.Panel;
import math_primitives.Point;
import math_primitives.Vector;
import java.util.ArrayList;
import java.util.List;

public class MDV_Solver {

    public MDV_Solver(List<List<Panel>> panels, Vector V_inf){
        this.panels = panels;
        this.V_inf = V_inf;
        init_matrix(panels);
        init_left_side(panels, V_inf);
    }

    List<List<Double>> unit_circulation_matrix;
    List<Double> left_hand_side;
    List<List<Panel>> panels;
    Vector V_inf;
    Operator o = Operator.getInstance();

    private void init_matrix(List<List<Panel>> panels){

        unit_circulation_matrix = new ArrayList<>();

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                ArrayList<Double> subArray = new ArrayList<Double>();

                for(int k = 0; k < panels.size(); k++){
                    for(int l = 0; l < panels.get(0).size(); l++){

                        subArray.add(o.scalar_mul(unit_circulation(panels.get(i).get(j).middle,
                                panels.get(k).get(l))
                                ,panels.get(i).get(j).normal));


                    }
                }

                subArray.set((subArray.size()-1),subArray.get(subArray.size()-1) + 1);

                unit_circulation_matrix.add(subArray);

            }
        }
    }

    public Vector unit_circulation(Point from_which, Panel which_around){

        Vector unit_circulation = new Vector(0,0,0);

        unit_circulation = o.sum(unit_circulation,LineVectorIntegral(which_around.get_1st_p(),which_around.get_2nd_p(),from_which));


        unit_circulation = o.sum(unit_circulation,LineVectorIntegral(which_around.get_2nd_p(),which_around.get_3rd_p(),from_which));


        unit_circulation = o.sum(unit_circulation,LineVectorIntegral(which_around.get_3rd_p(),which_around.get_4th_p(),from_which));


        unit_circulation = o.sum(unit_circulation,LineVectorIntegral(which_around.get_4th_p(),which_around.get_1st_p(),from_which));



        return o.div(unit_circulation, (-1));

    }


    public Vector LineVectorIntegral(Point start_of_line, Point end_of_line, Point from_which){
        Vector R12 = new Vector(o.diff(end_of_line,start_of_line));
        Vector R10 = new Vector(o.diff(start_of_line,from_which));
        Vector R20 = new Vector(o.diff(end_of_line,from_which));
        double r12 = R12.length();
        double r10 = R10.length();
        double r20 = R20.length();

        double r12r10 = o.scalar_mul(R12,R10);
        double r12r20 = o.scalar_mul(R12,R20);

        Vector W = new Vector(0,0,0);
        if(r12*r12*r10*r10 - r12r10*r12r10 != 0){
            double coef = (r12r10/r10 - r12r20/r20)/(4*Math.PI*(r12*r12*r10*r10 - r12r10*r12r10));
            W = o.mul(o.vector_mul(R12,R10),coef);
            return W;
        }
        return W;
    }



    public void init_left_side(List<List<Panel>> panels, Vector V_inf){
        left_hand_side = new ArrayList<Double>();

        for(int k = 0; k < panels.size(); k++){
            for(int l = 0; l < panels.get(0).size(); l++){

                left_hand_side.add(-o.scalar_mul(panels.get(k).get(l).normal,V_inf));


            }
        }

    }

    public double[] calculate_gammas(){
        Matrix Resolve_Matrix = new Matrix(o.MatrixListToSimpleArray(unit_circulation_matrix));

        double[] buf = o.ListToSimpleArray(left_hand_side);


        Matrix Left_Side_Column = new Matrix(buf,buf.length);

        return Resolve_Matrix.solve(Left_Side_Column).getColumnPackedCopy();
    }



    public List<List<Vector>> calculate_flow_velocities(){
        int x_length = panels.size();
        int phi_length = panels.get(0).size();

        List<List<Vector>> flow_velocities = new ArrayList<>();

        for(int i = 0; i < x_length; i++){

            List<Vector> buf_list = new ArrayList<>();


            for(int j = 0; j < phi_length; j++){

                Vector circ_velocity_part = calculate_circular_velocity_part(panels.get(i).get(j));



                Vector tau_part = new Vector(0,0,0);
                Panel front,back,left,right;

                if(i == 0){
                    front = panels.get(i+1).get(j);
                    back  = null;
                }
                else if(i == x_length - 1){
                    front = null;
                    back  = panels.get(i-1).get(j);
                }
                else{
                    front = panels.get(i+1).get(j);
                    back  = panels.get(i-1).get(j);
                }

                if(j == 0){
                    left  = panels.get(i).get(phi_length-1);
                    right = panels.get(i).get(j+1);
                }
                else if(j == phi_length - 1){
                    left  = panels.get(i).get(j-1);
                    right = panels.get(i).get(0);
                }
                else{
                    left  = panels.get(i).get(j-1);
                    right = panels.get(i).get(j+1);
                }



                tau_part = calculate_tau_velocity_part(panels.get(i).get(j),front,back,left,right);

                Vector flow_velocity = o.sum(V_inf,tau_part);
                flow_velocity = o.sum(flow_velocity,circ_velocity_part);
                buf_list.add(flow_velocity);
            }
            flow_velocities.add(buf_list);
        }

        return flow_velocities;

    }

    public Vector calculate_tau_velocity_part(Panel mid, Panel front, Panel back, Panel left, Panel right){

        Vector tau1 = new Vector(0,0,0);
        Vector tau2 = new Vector(0,0,0);

        try {
            if(back == null){
                tau1 = new Vector(o.diff(front.middle,mid.middle));
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1, front.gamma - mid.gamma);
            }
            else if(front == null){
                tau1 = new Vector(o.diff(mid.middle,back.middle));
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1,mid.gamma - back.gamma);
            }
            else{
                tau1 = new Vector(o.diff(front.middle,back.middle));
                tau1 = tau1.get_normalized_vector();
                tau1 = o.mul(tau1,(front.gamma - back.gamma) / 2);
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

        return o.div(o.sum(tau2,tau1),2);
    }

    public Vector calculate_circular_velocity_part(Panel from_which){

        Vector circ_velocity = new Vector(0,0,0);
        Point from_which_point = from_which.middle;

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                circ_velocity = o.sum(circ_velocity,
                                        o.mul(unit_circulation(from_which_point,panels.get(i).get(j)),
                                                    panels.get(i).get(j).gamma));


            }
        }
        return circ_velocity;
    }



    public List<List<Double>> calculate_normal_velocities(){
        List<List<Double>> normal_velocities = new ArrayList<>();

        for(int i = 0; i < panels.size(); i++){

            List<Double> buf_list = new ArrayList<>();
            for(int j = 0; j < panels.get(0).size(); j++){
                Panel cur = panels.get(i).get(j);
                buf_list.add(o.scalar_mul(cur.normal,cur.flow_velocity));
            }
            normal_velocities.add(buf_list);
        }

        return normal_velocities;
    }

    public List<List<Double>> calculate_velocities_module(){
        List<List<Double>> velocities_module = new ArrayList<>();

        for(int i = 0; i < panels.size(); i++){

            List<Double> buf_list = new ArrayList<>();
            for(int j = 0; j < panels.get(0).size(); j++){
                Vector flow_velocity = panels.get(i).get(j).flow_velocity;
                buf_list.add(Math.sqrt(o.scalar_mul(flow_velocity,flow_velocity)));
            }
            velocities_module.add(buf_list);
        }

        return velocities_module;
    }

    public List<List<Double>> calculate_pressure(double inner_pressure, double inner_density){
        List<List<Double>> pressure = new ArrayList<>();

        for(int i = 0; i < panels.size(); i++){

            List<Double> buf_list = new ArrayList<>();
            for(int j = 0; j < panels.get(0).size(); j++){

                Vector flow_velocity = panels.get(i).get(j).flow_velocity;

                buf_list.add(inner_pressure + inner_density/2 * (o.scalar_mul(V_inf,V_inf) - o.scalar_mul(flow_velocity,flow_velocity)));
            }
            pressure.add(buf_list);
        }

        return pressure;
    }

    public List<List<Double>> calculate_dimless_pressure(){
        List<List<Double>> dimless_pressure = new ArrayList<>();

        for(int i = 0; i < panels.size(); i++){

            List<Double> buf_list = new ArrayList<>();
            for(int j = 0; j < panels.get(0).size(); j++){

                Vector flow_velocity = panels.get(i).get(j).flow_velocity;
                buf_list.add(1 - o.scalar_mul(flow_velocity,flow_velocity)/o.scalar_mul(V_inf,V_inf));
            }
            dimless_pressure.add(buf_list);
        }

        return dimless_pressure;
    }
}
