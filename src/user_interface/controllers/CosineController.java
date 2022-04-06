package user_interface.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.arclenght.Cartesian_Arclenght_Calculator;
import math.separator.Arclength_Separator;
import math.separator.Even_Separator;
import radiis.generatrix_radius.Cosine_Radius;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class CosineController {

    @FXML
    private RadioButton arc_separation_radio;

    @FXML
    private TextField end_diameter_field;

    @FXML
    private Pane graph_pane;

    @FXML
    private TextField lenght_field;

    @FXML
    private RadioButton ox_separation_radio;

    @FXML
    private TextField separation_step_field;

    @FXML
    private ToggleGroup separation_type;


    @FXML
    private TextField start_diameter_field;

    @FXML
    void set_generatrix(ActionEvent event) {
        Generatrix_Radius_Data radius_data = Generatrix_Radius_Data.getInstance();
        Body_Data body_data = Body_Data.getInstance();

        double start = 0;
        double length = Float.parseFloat(lenght_field.getText());

        int parts_quantity = body_data.parts.size();
        if(parts_quantity != 0){

            start = body_data.parts.get(parts_quantity - 1).get_end();
        }

        double end = start + length;
        double start_diameter = Float.parseFloat(start_diameter_field.getText());
        double end_diameter = Float.parseFloat(end_diameter_field.getText());


        radius_data.radius = new Cosine_Radius(start,end,start_diameter,end_diameter);




        double step = Float.parseFloat(separation_step_field.getText());

        if(ox_separation_radio.isSelected()){
            int num_of_seps = Math.round((float)(length/step));
            radius_data.separator = new Even_Separator(radius_data.radius,num_of_seps);
        }
        else{
            Cartesian_Arclenght_Calculator calc = new Cartesian_Arclenght_Calculator(radius_data.radius);
            double arc = calc.calculate_arclenght_precisely(start,end,0.001);
            int num_of_seps = Math.round((float)(arc/step));

            radius_data.separator = new Arclength_Separator(radius_data.radius,calc,num_of_seps);
        }



        show_graph(radius_data);
    }

    public void show_graph(Generatrix_Radius_Data data){


        Line_Graph_Drawer drawer = new Line_Graph_Drawer(480,360);
        drawer.draw_radius(data.radius, data.separator, "resources/cosine_graph");
        Image img = new Image(new File("./resources/cosine_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }

}
