package user_interface.generatrix_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.arclenght.Cartesian_Arclenght_Calculator;
import math.math_primitives.Radius;
import math.separator.*;
import radiis.generatrix_radius.Back_Augive_Radius;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.main_controllers.Message_Creator;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class BackAugiveController {

    @FXML
    private RadioButton arc_separation_radio;

    @FXML
    private RadioButton ox_separation_radio;

    @FXML
    private RadioButton arithm_separation_radio;

    @FXML
    private RadioButton geom_separation_radio;



    @FXML
    private Pane graph_pane;

    @FXML
    private TextField diameter_field;

    @FXML
    private TextField lenght_field;

    @FXML
    private TextField common_ratio_field;

    @FXML
    private TextField separation_step_field;


    Separator init_separator(Radius radius){

        Separator separator;
        double step = Float.parseFloat(separation_step_field.getText());

        double start = radius.start;
        double end = radius.end;
        double length = end - start;

        if(ox_separation_radio.isSelected()){
            int num_of_seps = Math.round((float)(length/step));
            separator = new Even_Separator(radius,num_of_seps);
        }
        else if(arc_separation_radio.isSelected()){
            Cartesian_Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(radius);
            double arc = calc.calculate_arclenght_precisely(start,end,0.00001);
            int num_of_seps = Math.round((float)(arc/step));

            separator = new Arclength_Separator(radius,calc,num_of_seps);
        }
        else if(arithm_separation_radio.isSelected()){

            Cartesian_Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(radius);
            double arc = calc.calculate_arclenght_precisely(start,end,0.00001);
            double free_term = 2*arc/step;
            int num_of_seps = Math.round((float)((Math.sqrt(1 + 4 * free_term) - 1)/2 ));

            separator = new Arithmetic_Separator(radius,calc,num_of_seps);
        }
        else{
            common_ratio_field.setEditable(true);
            double common_ratio = Float.parseFloat(common_ratio_field.getText());
            double p = 1/common_ratio;

            Cartesian_Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(radius);
            double arc = calc.calculate_arclenght_precisely(start,end,0.00001);

            int num_of_seps = 0;

            if(step/(1-p) <= arc){
                Message_Creator.show_alert("Суммарная длина разбиения меньше длины дуги!");
            }
            else{
                double free_term = (1 - p) * arc/step;
                num_of_seps = Math.round((float)(Math.log(1 - free_term)/Math.log(p)));
                System.out.println(num_of_seps);
            }
            separator = new Geometric_Separator(radius,calc,num_of_seps, common_ratio);

        }


        return separator;
    }

    Radius init_radius(Body_Data body_data){
        double start = 0;
        double length = Float.parseFloat(lenght_field.getText());

        int parts_quantity = body_data.parts.size();
        if(parts_quantity != 0){

            start = body_data.parts.get(parts_quantity - 1).get_end();
        }

        double end = start + length;
        double diameter = Float.parseFloat(diameter_field.getText());

        return new Back_Augive_Radius(start,end,diameter);
    }

    @FXML
    void set_generatrix(ActionEvent event) {

        Generatrix_Radius_Data radius_data = Generatrix_Radius_Data.getInstance();
        Body_Data body_data = Body_Data.getInstance();

        radius_data.radius = init_radius(body_data);
        radius_data.separator = init_separator(radius_data.radius);

        show_graph(radius_data);
    }


    public void show_graph(Generatrix_Radius_Data data){


        Line_Graph_Drawer drawer = new Line_Graph_Drawer(480,360);
        drawer.draw_radius(data.radius, data.separator, "_resources/back_augive_graph");
        Image img = new Image(new File("./_resources/back_augive_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }

    public void enable_common_ratio(ActionEvent event) {
        common_ratio_field.setDisable(!geom_separation_radio.isSelected());
    }
}
