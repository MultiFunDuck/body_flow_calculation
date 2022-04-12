package calculation.grid_builder;


import calculation.high_level_math.Pressure_Calculator;
import calculation.high_level_math.MDV_Solver;
import calculation.high_level_math.Velocity_Calculator;
import math.math_primitives.Operator;
import math.math_primitives.Panel;
import math.math_primitives.Point;
import math.math_primitives.Vector;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    public List<List<Point>> points;
    public List<List<Panel>> panels;

    public Grid(List<List<Point>> points, List<List<Panel>> panels) {
        this.points = points;
        this.panels = panels;
    }

    public List<List<Point>> find_points_in_interval(double start, double end){
        List<List<Point>> sub_points = new ArrayList<>();

        for(int i = 0; i < points.size(); i++){

            if(start < points.get(i).get(0).x && points.get(i).get(0).x <=end){

                List<Point> buf_list = new ArrayList<>();
                for(int j = 0; j < points.get(0).size(); j++){

                    buf_list.add(points.get(i).get(j));

                }

                sub_points.add(buf_list);
            }


        }
        return sub_points;
    }

    public void to_File(String file_name){
        try(FileWriter fileWriter = new FileWriter(file_name, false))
        {

            fileWriter.write(this.points.size()*this.points.get(0).size() + " 3 0\n");
            for(int i = 0; i < this.points.size(); i++){
                for(int j = 0; j < this.points.get(0).size(); j++){
                    fileWriter.write(this.points.get(i).get(j).pointToFile());
                }
            }

            fileWriter.write("\n");
            fileWriter.write(this.panels.size()*this.panels.get(0).size() + " 4 0\n");
            for(int i = 0; i < this.panels.size(); i++){
                for(int j = 0; j < this.panels.get(0).size(); j++){
                    fileWriter.write(this.panels.get(i).get(j).panelToFile());
                }
            }

            fileWriter.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }



    public void to_File_with_data(String file_name){
        try(FileWriter fileWriter = new FileWriter(file_name, false))
        {

            fileWriter.write(this.points.size()*this.points.get(0).size() + " 3 0\n");
            for(int i = 0; i < this.points.size(); i++){
                for(int j = 0; j < this.points.get(0).size(); j++){
                    fileWriter.write(this.points.get(i).get(j).pointToFile());
                }
            }

            fileWriter.write("\n");
            fileWriter.write(this.panels.size()*this.panels.get(0).size() + " 4 29 Gamma V.n " +
                    "V.x V.y V.z |V| " +
                    "Tau_V.x Tau_V.y Tau_V.z |Tau_V| " +
                    "Gamma_V.x Gamma_V.y Gamma_V.z |Gamma_V| " +
                    "V0.x V0.y V0.z |V0| " +
                    "V+.x V+.y V+.z |V+| " +
                    "V-.x V-.y V-.z |V-| " +
                    "Cp Cp+ Cp- P\n");

            for(int i = 0; i < this.panels.size(); i++){
                for(int j = 0; j < this.panels.get(0).size(); j++){
                    fileWriter.write(this.panels.get(i).get(j).panelWithDataToFile());
                }
            }


            fileWriter.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    public void write_down_data(Vector V_inf, double inner_pressure, double inner_density){
        MDV_Solver solver = new MDV_Solver(panels, V_inf);
        Velocity_Calculator velocity_calculator = new Velocity_Calculator(panels,V_inf);
        Pressure_Calculator pressure_calculator = new Pressure_Calculator();

        write_gamma_to_panels(solver.calculate_gammas());

        write_tau_velocity_to_panels(velocity_calculator);
        write_circular_velocity_to_panels(velocity_calculator);
        write_flow_velocity_to_panels(V_inf);


        write_normal_velocity_to_panels(velocity_calculator);


        write_velocities_module_to_panels(velocity_calculator);


        write_pressure_to_panels(pressure_calculator,
                inner_pressure,
                inner_density,
                V_inf);


        write_dimless_pressure_to_panels(pressure_calculator, V_inf);



    }




    public void write_gamma_to_panels(double[] gammas){


        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j).gamma = gammas[i * panels.get(0).size() + j];
            }
        }
    }

    public void write_tau_velocity_to_panels(Velocity_Calculator velocity_calculator){

        int x_length = panels.size();
        int phi_length = panels.get(0).size();

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){

                Panel front,back,left,right;
                Panel mid = panels.get(i).get(j);

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

                panels.get(i).get(j).tau_velocity = velocity_calculator.calculate_tau_velocity_part(mid,front,back,left,right);


            }
        }
    }

    public void write_circular_velocity_to_panels(Velocity_Calculator velocity_calculator){
        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j)
                        .circ_velocity = velocity_calculator
                        .calculate_circular_velocity_part(
                                panels.get(i).get(j)
                        );
            }
        }
    }

    public void write_flow_velocity_to_panels(Vector V_inf){
        Operator o = Operator.getInstance();

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j)
                        .flow_velocity = o.sum(V_inf, o.sum(panels.get(i).get(j).circ_velocity , panels.get(i).get(j).tau_velocity));
            }
        }

    }

    public void write_normal_velocity_to_panels(Velocity_Calculator velocity_calculator){
        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j)
                        .normal_velocity = velocity_calculator
                                                    .calculate_normal_velocity(
                                                        panels.get(i).get(j)
                                                    );
            }
        }
    }

    public void write_velocities_module_to_panels(Velocity_Calculator velocity_calculator){
        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j)
                        .velocity_module = velocity_calculator
                                                    .calculate_velocity_module(
                                                         panels.get(i).get(j)
                                                    );
            }
        }
    }

    public void write_pressure_to_panels(Pressure_Calculator pressure_calculator,
                                         double inner_pressure,
                                         double inner_density,
                                         Vector V_inf){

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j).pressure =
                        pressure_calculator.
                                calculate_pressure(panels.get(i).get(j),
                                        inner_pressure,
                                        inner_density,
                                        V_inf);
            }
        }

    }


    public void write_dimless_pressure_to_panels(Pressure_Calculator pressure_calculator, Vector V_inf){
        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j).dimless_pressure =
                        pressure_calculator.
                                calculate_dimless_pressure(panels.get(i).get(j),V_inf);
            }
        }
    }





}




