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
        init_matrix(panels);
        init_left_side(panels, V_inf);
    }

    List<List<Double>> unit_circulation_matrix;
    List<Double> left_hand_side;
    List<List<Panel>> panels;
    Operator o = Operator.getInstance();

    public void init_matrix(List<List<Panel>> panels){

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
}
