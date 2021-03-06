package user_interface.main_controllers;

import _examples.Body_Part_Example;
import calculation.grid_building.Body_Part;
import calculation.grid_building.ChangeAble_Body;
import calculation.grid_building.Grid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import java.util.Formatter;

public class FlowController {

    @FXML
    private ToggleGroup SolveMethod;

    @FXML
    private RadioButton bi_grad_radio;

    @FXML
    private RadioButton gauss_radio;

    @FXML
    private Pane graph_pane;

    @FXML
    private TextField attack_angle_field;

    @FXML
    private TextField inner_density_field;

    @FXML
    private TextField flow_velocity_field;

    @FXML
    private TextField inner_pressure_field;

    @FXML
    private TextField main_body_parts_num_field;

    @FXML
    private TextField reynolds_num_field;

    @FXML
    private TextField curvature_radius_field;


    @FXML
    void set_calc_props(ActionEvent event) {

        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();
        Body_Data body_data = Body_Data.getInstance();


        calc_data.attack_angle = Math.PI*Float.parseFloat(attack_angle_field.getText()) / 180;
        calc_data.flow_velocity = Float.parseFloat(flow_velocity_field.getText());
        calc_data.inner_density = Float.parseFloat(inner_density_field.getText());
        calc_data.inner_pressure = Float.parseFloat(inner_pressure_field.getText());
        calc_data.main_parts_num = Integer.parseInt(main_body_parts_num_field.getText());
        calc_data.reynolds_num = Float.parseFloat(reynolds_num_field.getText());
        calc_data.curvature_radius = Float.parseFloat(curvature_radius_field.getText());

        draw_generatrix(calc_data.main_parts_num);


        ChangeAble_Body body = body_data.body;
        Body_Part tail = body.get_parts().get(calc_data.main_parts_num);

        body.init_Grid();
        if(calc_data.attack_angle != 0 && (tail.get_length() >= calc_data.curvature_radius*calc_data.attack_angle)){

            body.curve_tail(calc_data.main_parts_num, calc_data.attack_angle, calc_data.curvature_radius);

        }
        else if (tail.get_length() < calc_data.curvature_radius*calc_data.attack_angle){

            NumberFormat formatter = new DecimalFormat("#0.00000000");

            Message_Creator.show_alert("???????????????????????? ?????????????? ???????????????? ???? ???????? = " +
                    formatter.format(calc_data.curvature_radius*calc_data.attack_angle) +
                    "\n" +
                    "?????????? ?????????????????????? ?????????????? = " + formatter.format(tail.get_length()) +
                    "\n" +
                    "???????????????????? ?????????????????? ????????????????????????????!");

        }

        Grid grid = body.get_Grid();
        grid.to_File("_results_on_grid/grid_for_calculation.mv");
    }


    void draw_generatrix(int num_of_parts) {
        Body_Data body_data = Body_Data.getInstance();
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(450,300);
        drawer.draw_full_generatrix(body_data.parts.subList(0,num_of_parts), "./_resources/generatrix_graph");
        Image img = new Image(new File("./_resources/generatrix_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));
    }

    @FXML
    void make_calc(ActionEvent event) {

        Body_Data body_data = Body_Data.getInstance();
        Calc_Props_Data calc_data = Calc_Props_Data.getInstance();



        Vector V_inf = new Vector(
                calc_data.flow_velocity * Math.cos(calc_data.attack_angle),
                0,
                calc_data.flow_velocity * Math.sin(calc_data.attack_angle));


        ChangeAble_Body body = body_data.body;
        Grid grid = body.get_Grid();

        grid.write_down_data(V_inf, calc_data.inner_pressure, calc_data.inner_density);
        grid.to_File_with_data("./_results_on_grid/grid_with_data.mv");

        Message_Creator.show_message("???????????? ??????????????! \n ???????????????????? ????. ?? _results_on_grid/grid_with_results.mv");

    }



}
