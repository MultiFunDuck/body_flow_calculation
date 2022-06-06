package user_interface.visualization;

import math.math_primitives.Operator;
import math.math_primitives.Panel;
import math.math_primitives.Vector;
import user_interface.data_classes.Body_Data;

import java.io.FileWriter;
import java.io.IOException;

public class Data_Exporter {


    public void export_generatrix_data(int angle_num, String value_name){

        Body_Data body_data = Body_Data.getInstance();

        int length_size = body_data.body.grid.panels.size();
        int angle_size = body_data.body.grid.panels.get(0).size(); System.out.println(length_size);


        try(FileWriter fileWriter = new FileWriter("./_results/" + value_name + angle_num + ".txt", false))
        {

            for(int i = 0; i < length_size; i++){

                fileWriter.write(Double.toString(
                        get_panel_value_by_name(
                                body_data.body.grid.panels.get(i).get(angle_num%angle_size)
                                , value_name)
                    )
                );
                fileWriter.write("\n");

            }


            fileWriter.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }


    public void export_cross_section_data(String value_name){

        Body_Data body_data = Body_Data.getInstance();

        int length_size = body_data.body.grid.cross_sections.size();


        try(FileWriter fileWriter = new FileWriter("./_results/" + value_name + ".txt", false))
        {

            for(int i = 0; i < length_size; i++){

                fileWriter.write(Double.toString(
                        get_cross_section_value_by_name(
                                body_data.body.grid.cross_sections.get(i)
                                , value_name)
                        )
                );
                fileWriter.write("\n");

            }


            fileWriter.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

    private double get_panel_value_by_name(Panel panel, String value_name){

        Operator o = Operator.getInstance();
        Vector V0 = o.diff(panel.flow_velocity, panel.tau_velocity);
        Vector Vplus = o.sum(V0,panel.tau_velocity);
        Vector Vminus = o.diff(V0,panel.tau_velocity);
        Vector dV = o.diff(Vplus, Vminus);
        double value = 0;

        switch (value_name){

            case ("Gamma"):
                value = panel.gamma;
                break;
            case ("V+.x"):
                value = Vplus.x;
                break;
            case ("V+.y"):
                value = Vplus.y;
                break;
            case ("V+.z"):
                value = Vplus.z;
                break;
            case ("V+"):
                value = Vplus.length();
                break;



            case ("V-.x"):
                value = Vminus.x;
                break;
            case ("V-.y"):
                value = Vminus.y;
                break;
            case ("V-.z"):
                value = Vminus.z;
                break;
            case ("V-"):
                value = Vminus.length();
                break;



            case ("V0.x"):
                value = V0.x;
                break;
            case ("V0.y"):
                value = V0.y;
                break;
            case ("V0.z"):
                value = V0.z;
                break;
            case ("V0"):
                value = V0.length();
                break;



            case ("dV.x"):
                value = dV.x;
                break;
            case ("dV.y"):
                value = dV.y;
                break;
            case ("dV.z"):
                value = dV.z;
                break;
            case ("dV"):
                value = dV.length();
                break;



            case ("V+.n"):
                value = panel.normal_velocity;
                break;
            case ("V-.n"):
                value = o.scalar_mul(panel.normal,Vminus);
                break;
            case ("V0.n"):
                value = o.scalar_mul(panel.normal,V0);
                break;
            case ("dV.n"):
                value = o.scalar_mul(panel.normal,dV);
                break;



            case ("sum(Gamma_w).x"):
                value = panel.circ_velocity.x;
                break;
            case ("sum(Gamma_w).y"):
                value = panel.circ_velocity.y;
                break;
            case ("sum(Gamma_w).z"):
                value = panel.circ_velocity.z;
                break;
            case ("sum(Gamma_w)"):
                value = panel.circ_velocity.length();
                break;



            case ("Cp"):
                value = panel.dimless_pressure;
                break;
            case ("Cp-"):
                value = 1 - (1-panel.dimless_pressure) * Vminus.length()*Vminus.length() / (Vplus.length()*Vplus.length());
                break;
            case ("Cp+"):
                value = panel.dimless_pressure;
                break;
            case ("P"):
                value = panel.pressure;
                break;


        }


        return value;
    }

    private double get_cross_section_value_by_name(Panel cross_section, String value_name){

        double value = 0;

        switch (value_name){

            case ("V0.x_central"):
                value = cross_section.flow_velocity.x;
                break;
            case ("V0.y_central"):
                value = cross_section.flow_velocity.y;
                break;
            case ("V0.z_central"):
                value = cross_section.flow_velocity.z;
                break;
            case ("V0_central"):
                value = cross_section.flow_velocity.length();
                break;


        }


        return value;
    }
}
