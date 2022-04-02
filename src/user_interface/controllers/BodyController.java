package user_interface.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BodyController {

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    ObservableList<String> generatrix_radius = FXCollections.observableArrayList(
            "Переднее оживало",
            "Цилиндрическая",
            "Косинусоидальная",
            "Заднее оживало",
            "Кубический сплайн",
            "Сглаживающий кубический сплайн");

    ObservableList<String> angular_radius = FXCollections.observableArrayList(
            "Окружность","Эллипс");

    @FXML
    private ChoiceBox generatrix_radius_box;

    @FXML
    private ChoiceBox angular_radius_box;

    @FXML
    private void initialize(){
        generatrix_radius_box.setValue("Переднее оживало");
        generatrix_radius_box.setItems(generatrix_radius);

        angular_radius_box.setValue("Окружность");
        angular_radius_box.setItems(angular_radius);
    }

    @FXML
    public Button init_body_button;

    @FXML
    public Button body_part_add_button;

    @FXML
    public Button body_part_delete_button;

    @FXML
    public Button draw_generatrix_button;

    @FXML
    private Pane pane_for_graph;



    @FXML
    void init_body(ActionEvent event) {

    }

    @FXML
    void draw_generatrix(ActionEvent event) {

    }

    @FXML
    void delete_body_part(){

    }

    @FXML
    void open_body_tab(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_files/FrontAugiveTab.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1, 900, 600));
            stage1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
