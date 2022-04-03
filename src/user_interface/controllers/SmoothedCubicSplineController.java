package user_interface.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import math.arclenght.Cartesian_Arclenght_Calculator;
import math.math_primitives.Flat_Point;
import math.separator.Arclength_Separator;
import math.separator.Even_Separator;
import radiis.generatrix_radius.Smoothed_Cubic_Spline_Radius;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.data_classes.PointViewData;
import user_interface.data_classes.Points_List_Data;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmoothedCubicSplineController {


    @FXML
    private RadioButton ox_separation_radio;

    @FXML
    private RadioButton arc_separation_radio;




    @FXML
    private Pane graph_pane;



    @FXML
    private TextField left_slope_field;

    @FXML
    private TextField right_slope_field;

    @FXML
    private TextField separation_step_field;

    @FXML
    private TextField lambda_field;




    @FXML
    private TextField delete_point_num_field;

    @FXML
    private TextField x_coord_field;

    @FXML
    private TextField y_coord_field;



    ObservableList<PointViewData> table_data = FXCollections.observableArrayList();

    @FXML
    private TableView<PointViewData> points_table;

    @FXML
    private TableColumn<PointViewData, Integer> num_column;

    @FXML
    private TableColumn<PointViewData, Double> x_coord_column;

    @FXML
    private TableColumn<PointViewData, Double> y_coord_column;


    @FXML
    private void initialize(){
        points_table.setItems(table_data);

        num_column.setCellValueFactory(
                p -> new SimpleIntegerProperty(p.getValue().getNum()).asObject()
        );

        x_coord_column.setCellValueFactory(
                p -> new SimpleDoubleProperty(p.getValue().getX_coord()).asObject()
        );

        y_coord_column.setCellValueFactory(
                p -> new SimpleDoubleProperty(p.getValue().getY_coord()).asObject()
        );
    }


    @FXML
    void add_point(ActionEvent event) {

        Points_List_Data data = Points_List_Data.getInstance();

        double x_coord = Float.parseFloat(x_coord_field.getText());
        double y_coord = Float.parseFloat(y_coord_field.getText());
        Flat_Point new_point = new Flat_Point(x_coord,y_coord);

        if(!data.is_there_the_same(new_point)){
            data.points.add(new_point);
            data.points.sort((p1,p2) -> {
                if(p1.x == p2.x){
                    return 0;
                }
                else{
                    return (p1.x - p2.x) > 0 ? 1 : -1;
                }
            });


            table_data.clear();
            for(int i = 0; i < data.points.size(); i++){
                table_data.add(new PointViewData(i + 1, data.points.get(i).x, data.points.get(i).y));
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Точка с координатой Х = "+ x_coord +" уже есть");
            alert.showAndWait();
        }

    }

    @FXML
    void delete_point(ActionEvent event) {

        Points_List_Data data = Points_List_Data.getInstance();
        int delete_point_num = Integer.parseInt(delete_point_num_field.getText());

        if(0 < delete_point_num && delete_point_num <= data.points.size()){
            data.points.remove(delete_point_num - 1);

            table_data.clear();
            for(int i = 0; i < data.points.size(); i++){
                table_data.add(new PointViewData(i + 1, data.points.get(i).x, data.points.get(i).y));
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нет точки с номером "+ delete_point_num);
            alert.showAndWait();
        }
    }



    @FXML
    void set_generatrix(ActionEvent event) {
        Generatrix_Radius_Data radius_data = Generatrix_Radius_Data.getInstance();

        Points_List_Data data = Points_List_Data.getInstance();


        double left_slope = (Math.PI/180) * Float.parseFloat(left_slope_field.getText());
        double right_slope = (Math.PI/180) * Float.parseFloat(right_slope_field.getText());
        double lambda = Float.parseFloat(lambda_field.getText());


        List<Flat_Point> buf_list = new ArrayList<>();
        for(Flat_Point p : data.points){
            buf_list.add(new Flat_Point(p));
        }
        radius_data.radius = new Smoothed_Cubic_Spline_Radius(buf_list,left_slope,right_slope,lambda);




        double step = Float.parseFloat(separation_step_field.getText());
        double start = radius_data.radius.start;
        double end = radius_data.radius.end;
        double length = end - start;


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


    void show_graph(Generatrix_Radius_Data data) {
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(480,360);
        drawer.draw_radius(data.radius, data.separator, "resources/smoothed_spline_graph");
        Image img = new Image(new File("./resources/smoothed_spline_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));
    }
}
