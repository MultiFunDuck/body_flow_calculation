package user_interface.form_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.arclenght.Polar_Arclenght_Calculator;
import math.separator.Arclength_Separator;
import math.separator.Even_Separator;
import radiis.angular_radius.Elliptic_Angular_Radius;
import user_interface.data_classes.Angular_Radius_Data;
import user_interface.data_classes.Body_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class EllipseController {

    @FXML
    private Pane graph_pane;

    @FXML
    private Button set_generatrix_button;

    @FXML
    private ToggleGroup separation_type;

    @FXML
    private RadioButton arc_separation_radio;

    @FXML
    private RadioButton ox_separation_radio;

    @FXML
    private TextField separation_num_field;

    @FXML
    private TextField small_axis_field;

    @FXML
    private TextField big_axis_field;

    @FXML
    void set_generatrix(ActionEvent event) {
        Angular_Radius_Data radius_data = Angular_Radius_Data.getInstance();
        Body_Data body_data = Body_Data.getInstance();

        double b = Float.parseFloat(big_axis_field.getText());
        double a = Float.parseFloat(small_axis_field.getText());

        radius_data.radius = new Elliptic_Angular_Radius(a,b);




        int num_of_seps = Integer.parseInt(separation_num_field.getText());

        if(ox_separation_radio.isSelected()){

            radius_data.separator = new Even_Separator(radius_data.radius,num_of_seps);
        }
        else{
            Polar_Arclenght_Calculator calc = new Polar_Arclenght_Calculator(radius_data.radius);
            radius_data.separator = new Arclength_Separator(radius_data.radius,calc,num_of_seps);
        }

        show_graph(radius_data);
    }



    public void show_graph(Angular_Radius_Data data){

        Line_Graph_Drawer drawer = new Line_Graph_Drawer(480,360);
        drawer.draw_polar_radius(data.radius, data.separator, "_resources/ellipse_graph");
        Image img = new Image(new File("./_resources/ellipse_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }
}
