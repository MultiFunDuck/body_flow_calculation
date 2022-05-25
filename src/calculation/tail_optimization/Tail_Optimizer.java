package calculation.tail_optimization;

import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Calc_Props_Data;

public class Tail_Optimizer {


    public void resize_tail_length(double tail_length){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();

        int main_parts_num = calc_data.main_parts_num;

        body_data.body.resize_part_length(main_parts_num,tail_length);
    }

    public void resize_tail_radius(double tail_radius){

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();

        int main_parts_num = calc_data.main_parts_num;
        int all_parts_num = body_data.parts.size();



    }


}
