package user_interface.main_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import user_interface.data_classes.Body_Data;
import user_interface.visualization.Data_Exporter;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class ResultsController {





    @FXML
    private TextField angle_num_field;

    @FXML
    private ChoiceBox<String> data_choicebox;



    @FXML
    private Pane graph_pane;



    @FXML
    private void initialize(){
        ObservableList<String> data_choices = FXCollections.observableArrayList(
                "Gamma",
                "V0.x_central",
                "V0.y_central",
                "V0.z_central",
                "V0_central",
                "V+.x",
                "V+.y",
                "V+.z",
                "V+",
                "V-.x",
                "V-.y",
                "V-.z",
                "V-",
                "V0.x",
                "V0.y",
                "V0.z",
                "V0",
                "dV.x",
                "dV.y",
                "dV.z",
                "dV",
                "V+.n",
                "V-.n",
                "V0.n",
                "dV.n",
                "sum(Gamma_w).x",
                "sum(Gamma_w).y",
                "sum(Gamma_w).z",
                "sum(Gamma_w)",
                "Cp",
                "Cp+",
                "Cp-",
                "P");

        data_choicebox.setItems(data_choices);
        data_choicebox.setValue("Выберите данные");
    }

    @FXML
    void draw_graph(ActionEvent event) {

        String value_name = data_choicebox.getValue();
        int angle_num = Integer.parseInt(angle_num_field.getText());


        Body_Data body_data = Body_Data.getInstance();
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(820,580);


        if(value_name.contains("central")){
            drawer.draw_central_results_graph(value_name,
                    "./_graphs_of_results/" + value_name + angle_num + "_graph",
                    body_data.body);
        }
        else{
            drawer.draw_results_graph(value_name,
                    "./_graphs_of_results/" + value_name + angle_num + "_graph",
                    body_data.body,
                    angle_num);
        }



        Image img = new Image(new File("./_graphs_of_results/" + value_name + angle_num + "_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }

    @FXML
    void export_data(ActionEvent event) {


        String value_name = data_choicebox.getValue();
        int angle_num = Integer.parseInt(angle_num_field.getText());
        Data_Exporter exporter = new Data_Exporter();

        if(value_name.contains("central")){
            exporter.export_cross_section_data(value_name);
        }
        else{
            exporter.export_generatrix_data(angle_num, value_name);
        }



    }

}
