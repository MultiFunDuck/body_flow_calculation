package user_interface.main_controllers;

import calculation.tail_optimization.Tail_Parameters_Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TailController {

    @FXML
    private TextField bottom_pressure_drag_field;

    @FXML
    private TextField calculated_pressure_field;

    @FXML
    private TextField current_tail_diameter_field;

    @FXML
    private TextField current_tail_length;

    @FXML
    private TextField expected_tail_diameter_field;

    @FXML
    private TextField expected_tail_length;


    @FXML
    private TextField friction_drag_field;

    @FXML
    private TextField horner_pressure_field;

    @FXML
    private TextField pressure_drag_field;

    @FXML
    private TextField tail_diameter;

    @FXML
    private TextField tail_length;

    @FXML
    private TextField total_drag_field;

    @FXML
    void find_expected_values(ActionEvent event) {

    }

    @FXML
    void make_new_calculation(ActionEvent event) {



    }

    @FXML
    void set_tail_params(ActionEvent event) {

    }

    @FXML
    void show_results(ActionEvent event) {
        NumberFormat formatter = new DecimalFormat("#0.00000000");
        Tail_Parameters_Calculator calculator = new Tail_Parameters_Calculator();
        horner_pressure_field.setText(formatter.format(calculator.calc_horner_pressure()));
        calculated_pressure_field.setText(formatter.format(calculator.calc_bottom_pressure()));

    }




}

