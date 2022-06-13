package user_interface.data_classes;

public class Calc_Props_Data {

    private static Calc_Props_Data instance;

    private Calc_Props_Data(){}

    public static Calc_Props_Data getInstance(){

        if(instance == null){
            instance = new Calc_Props_Data();
        }
        return instance;

    }

    public double reynolds_num, flow_velocity, inner_density, inner_pressure, attack_angle, curvature_radius;
    public int main_parts_num;
}
