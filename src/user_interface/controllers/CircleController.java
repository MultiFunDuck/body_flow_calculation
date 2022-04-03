package user_interface.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import math.arclenght.Polar_Arclenght_Calculator;
import math.separator.Arclength_Separator;
import math.separator.Even_Separator;
import radiis.angular_radius.Circle_Angular_Radius;
import user_interface.data_classes.Angular_Radius_Data;

public class CircleController {

    @FXML
    private RadioButton arc_separation_radio;

    @FXML
    private RadioButton ox_separation_radio;

    @FXML
    private TextField separation_num_field;

    @FXML
    private Button set_form_button;

    @FXML
    void set_form(ActionEvent event) {

        Angular_Radius_Data radius_data = Angular_Radius_Data.getInstance();

        radius_data.radius = new Circle_Angular_Radius();


        int num_of_seps = Integer.parseInt(separation_num_field.getText());
        if(ox_separation_radio.isSelected()){
            radius_data.separator = new Even_Separator(radius_data.radius,num_of_seps);
        }
        else{
            Polar_Arclenght_Calculator calc = new Polar_Arclenght_Calculator(radius_data.radius);
            radius_data.separator = new Arclength_Separator(radius_data.radius,calc,num_of_seps);
        }

    }

}
