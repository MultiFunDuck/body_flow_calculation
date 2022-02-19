package Grid_Builder;


import high_level_math.MDV_Solver;
import math_primitives.Panel;
import math_primitives.Point;
import math_primitives.Vector;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    List<List<Point>> points;
    List<List<Panel>> panels;

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
            fileWriter.write(this.panels.size()*this.panels.get(0).size() + " 4 1 Gamma\n");
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


    public void write_down_data(Vector V_inf){
        MDV_Solver solver = new MDV_Solver(panels, V_inf);
        write_gamma_to_panels(solver.calculate_gammas());

    }

    public void write_gamma_to_panels(double[] gammas){


        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < panels.get(0).size(); j++){
                panels.get(i).get(j).gamma = gammas[i * panels.get(0).size() + j];
            }
        }
    }
}
