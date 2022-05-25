package calculation.tail_optimization;

import math.math_primitives.Panel;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Calc_Props_Data;

public class Tail_Parameters_Calculator {

    public double calc_horner_pressure(){
        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_props_data = Calc_Props_Data.getInstance();
        int body_parts_num = calc_props_data.main_parts_num;

        double total_length = 0;
        for(int i = 0; i < body_parts_num; i++){
            total_length += body_data.parts.get(i).get_length();
        }

        double diameter = 2 * body_data.parts.get(body_parts_num).get_start_radius();
        double Reynolds = calc_props_data.reynolds_num;
        double c_f = 0.455 / Math.pow(Math.log10(Reynolds), 2.58);

        return 0.015 * Math.sqrt(diameter /(c_f * total_length));
    }

    public double calc_bottom_pressure(){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_props_data = Calc_Props_Data.getInstance();


        int body_parts_num = calc_props_data.main_parts_num;
        int main_body_ox_sep_sum = 0;
        for(int i = 0; i < body_parts_num; i++){
            main_body_ox_sep_sum += body_data.parts.get(i).get_ox_separation().size();
        }

        int circle_sep_num = body_data.parts.get(0).get_angle_separation().size() - 1;


        int last_body_circle_num = main_body_ox_sep_sum - 3;
        int first_tail_circle_num = main_body_ox_sep_sum - 2;


        double bottom_pressure = 0;

        for(int i = 0; i < circle_sep_num; i++){

            bottom_pressure += body_data.body.grid.panels.get(last_body_circle_num).get(i).dimless_pressure +
                               body_data.body.grid.panels.get(first_tail_circle_num).get(i).dimless_pressure;

        }

        bottom_pressure /= (2 * circle_sep_num);


        return bottom_pressure;

    }

    public double calc_current_tail_length(){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_props_data = Calc_Props_Data.getInstance();


        int body_parts_num = calc_props_data.main_parts_num;
        int all_parts_num = body_data.parts.size();
        double tail_length = 0;

        for(int i = body_parts_num; i < all_parts_num; i++){
            tail_length += body_data.parts.get(i).get_length();
        }

        return tail_length;

    }

    public double calc_expected_tail_length(){

        return 0;

    }



    public double calc_pressure_drag(){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();


        int body_parts_num = calc_data.main_parts_num;
        int main_body_ox_sep_sum = 0;
        for(int i = 0; i < body_parts_num; i++){
            main_body_ox_sep_sum += body_data.parts.get(i).get_ox_separation().size();
        }

        int circle_sep_num = body_data.parts.get(0).get_angle_separation().size() - 1;


        int last_body_circle_num = main_body_ox_sep_sum - 3;


        double pressure_drag = 0;

        for(int i = 0; i <= last_body_circle_num; i++){

            for(int j = 0; j < circle_sep_num; j++){

                Panel cur = body_data.body.grid.panels.get(i).get(j);
                double x_dir = -cur.normal.x;
                pressure_drag += x_dir * cur.dimless_pressure * cur.width() * cur.length();

            }

        }

        return pressure_drag;

    }

    public double calc_bottom_pressure_drag(){

        return  -calc_bottom_pressure();

    }

    public double calc_friction_drag(){


        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();


        int body_parts_num = calc_data.main_parts_num;
        int main_body_ox_sep_sum = 0;
        for(int i = 0; i < body_parts_num; i++){
            main_body_ox_sep_sum += body_data.parts.get(i).get_ox_separation().size();
        }

        int circle_sep_num = body_data.parts.get(0).get_angle_separation().size() - 1;


        int last_body_circle_num = main_body_ox_sep_sum - 3;


        double friction_drag = 0;
        double Reynolds = calc_data.reynolds_num;
        double c_f = 0.455 / Math.pow(Math.log10(Reynolds), 2.58);

        for(int i = 0; i <= last_body_circle_num; i++){

            for(int j = 0; j < circle_sep_num; j++){

                Panel cur = body_data.body.grid.panels.get(i).get(j);
                friction_drag += cur.width() * cur.length();

            }

        }

        return c_f * friction_drag;

    }

    public double calc_current_tail_diameter(){

        Body_Data body_data = Body_Data.getInstance();
        int all_parts_num = body_data.parts.size();

        return 2 * body_data.parts.get(all_parts_num - 1).get_end_radius();

    }


}
