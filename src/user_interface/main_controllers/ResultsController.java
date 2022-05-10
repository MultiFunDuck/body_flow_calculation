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
                "V.x",
                "V.y",
                "V.z",
                "V",
                "tau_V.x",
                "tau_V.y",
                "tau_V.z",
                "tau_V",
                "circ_V.x",
                "circ_V.y",
                "circ_V.z",
                "circ_V",
                "V0.x",
                "V0.y",
                "V0.z",
                "V0",
                "V+.x",
                "V+.y",
                "V+.z",
                "V+",
                "V-.x",
                "V-.y",
                "V-.z",
                "V-",
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
        drawer.draw_results_graph(value_name,
                "./_graphs_of_results/" + value_name + angle_num + "_graph",
                body_data.body,
                angle_num);

        Image img = new Image(new File("./_graphs_of_results/" + value_name + angle_num + "_graph.PNG").toURI().toString());
        graph_pane.getChildren().add(new ImageView(img));

    }

}
