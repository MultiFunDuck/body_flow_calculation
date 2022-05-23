package calculation.tail_optimization;

import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Calc_Props_Data;

public class Tail_Parameters_Calculator {

    public double calc_horner_pressure(){
        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_props_data = Calc_Props_Data.getInstance();
        int body_parts_num = calc_props_data.main_parts_num;
        int all_parts_num = body_data.parts.size();

        double total_length = 0;
        for(int i = body_parts_num; i < all_parts_num; i++){
            total_length += body_data.parts.get(i).get_length();
        }

        System.out.println(total_length);
        double diameter = body_data.parts.get(body_parts_num).get_start_radius();
        double Reynolds = calc_props_data.reynolds_num;
        double c_f = 0.455 / Math.pow(Math.log10(Reynolds), 2.58);

        return 0.015 * Math.sqrt(diameter * c_f * total_length);
    }

    public double calc_bottom_pressure(){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_props_data = Calc_Props_Data.getInstance();

        int body_parts_num = calc_props_data.main_parts_num;
        int main_body_ox_sep_sum = 0;
        for(int i = 0; i < body_parts_num; i++){
            main_body_ox_sep_sum += body_data.parts.get(i).get_ox_separation().size();
        }

        int circle_sep_num = body_data.parts.get(0).get_angle_separation().size();


        int last_body_circle_num = main_body_ox_sep_sum - 3;
        int first_tail_circle_num = main_body_ox_sep_sum - 2;

        System.out.println(body_data.body.grid.panels.get(last_body_circle_num).get(0).get_1st_p());
        System.out.println(body_data.body.grid.panels.get(last_body_circle_num).get(0).get_4th_p());

        System.out.println(body_data.body.grid.panels.get(first_tail_circle_num).get(0).get_1st_p());
        System.out.println(body_data.body.grid.panels.get(first_tail_circle_num).get(0).get_4th_p());
        return 0;

    }

}
