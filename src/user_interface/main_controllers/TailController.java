package user_interface.main_controllers;

import calculation.grid_building.ChangeAble_Body;
import calculation.grid_building.Grid;
import calculation.tail_optimization.Tail_Optimizer;
import calculation.tail_optimization.Tail_Parameters_Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.math_primitives.Vector;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Calc_Props_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;
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
    private TextField tail_diameter_field;

    @FXML
    private TextField tail_length_field;

    @FXML
    private TextField total_drag_field;

    @FXML
    private Pane graph_pane;

    @FXML
    void find_expected_values(ActionEvent event) {

    }

    @FXML
    void make_new_calculation(ActionEvent event) {

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();



        Vector V_inf = new Vector(
                calc_data.flow_velocity * Math.cos(calc_data.attack_angle),
                0,
                calc_data.flow_velocity * Math.sin(calc_data.attack_angle));


        ChangeAble_Body body = body_data.body;
        body.init_Grid();
        Grid grid = body.get_Grid();


        grid.write_down_data(V_inf, calc_data.inner_pressure, calc_data.inner_density);
        grid.to_File_with_data("./_results_on_grid/grid_with_data.mv");

    }

    @FXML
    void set_tail_params(ActionEvent event) {

        double tail_length = Float.parseFloat(tail_length_field.getText());
        double tail_diameter = Float.parseFloat(tail_diameter_field.getText());


        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();
        Tail_Optimizer optimizer = new Tail_Optimizer();

        optimizer.resize_tail_length(tail_length);
        optimizer.resize_tail_diameter(tail_diameter);


        Line_Graph_Drawer drawer = new Line_Graph_Drawer(500,350);
        drawer.draw_full_generatrix(body_data.parts.subList(calc_data.main_parts_num,body_data.parts.size()), "./_resources/tail_graph");
        Image img = new Image(new File("./_resources/tail_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));
    }

    @FXML
    void show_results(ActionEvent event) {
        NumberFormat formatter = new DecimalFormat("#0.00000");
        Tail_Parameters_Calculator calculator = new Tail_Parameters_Calculator();
        horner_pressure_field.setText(formatter.format(calculator.calc_horner_pressure()));
        calculated_pressure_field.setText(formatter.format(calculator.calc_bottom_pressure()));
        current_tail_length.setText(formatter.format(calculator.calc_current_tail_length()));
        expected_tail_length.setText(formatter.format(calculator.calc_expected_tail_length()));



        pressure_drag_field.setText(formatter.format(calculator.calc_pressure_drag()));
        friction_drag_field.setText(formatter.format(calculator.calc_friction_drag()));
        bottom_pressure_drag_field.setText(formatter.format(calculator.calc_bottom_pressure_drag()));
        double total_drag = calculator.calc_pressure_drag() + calculator.calc_friction_drag() + calculator.calc_bottom_pressure_drag();
        total_drag_field.setText(formatter.format(total_drag));
        expected_tail_diameter_field.setText(formatter.format(Math.sqrt(2*total_drag)));
        current_tail_diameter_field.setText(formatter.format(calculator.calc_current_tail_diameter()));

    }




}

