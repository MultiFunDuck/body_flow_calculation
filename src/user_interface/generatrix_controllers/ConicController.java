package user_interface.generatrix_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.math_primitives.Radius;
import math.separator.Even_Separator;
import math.separator.Separator;
import radiis.generatrix_radius.Conic_Radius;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class ConicController {

    @FXML
    private Pane graph_pane;

    @FXML
    private TextField start_diameter_field;

    @FXML
    private TextField half_opening_angle_field;

    @FXML
    private TextField lenght_field;


    @FXML
    private TextField separation_step_field;


    Separator init_separator(Radius radius){

        Separator separator;
        double step = Float.parseFloat(separation_step_field.getText());

        double start = radius.start;
        double end = radius.end;
        double length = end - start;

        int num_of_seps = Math.round((float)(length/step));
        separator = new Even_Separator(radius,num_of_seps);


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
        double start_diameter = Float.parseFloat(start_diameter_field.getText());
        double half_opening_angle = Float.parseFloat(half_opening_angle_field.getText());

        return new Conic_Radius(start,end,start_diameter, half_opening_angle);
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
        drawer.draw_radius(data.radius, data.separator, "resources/back_augive_graph");
        Image img = new Image(new File("./resources/back_augive_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }



}
