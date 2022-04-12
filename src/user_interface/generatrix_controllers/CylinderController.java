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
import radiis.generatrix_radius.Cylinder_Radius;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class CylinderController {


    @FXML
    private TextField diameter_field;

    @FXML
    private Pane graph_pane;

    @FXML
    private TextField lenght_field;

    @FXML
    private TextField separation_step_field;



    Separator init_separator(Radius radius){

        double step = Float.parseFloat(separation_step_field.getText());
        int num_of_seps = Math.round((float)(radius.length/step));

        return new Even_Separator(radius,num_of_seps);
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


        return new Cylinder_Radius(start,end,diameter);
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
        drawer.draw_radius(data.radius, data.separator, "_resources/cylinder_graph");
        Image img = new Image(new File("./_resources/cylinder_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }

}
