package user_interface.controllers;

import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;
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
import user_interface.data_classes.Angular_Radius_Data;
import user_interface.data_classes.Body_Parts_Data;
import user_interface.data_classes.Generatrix_Radius_Data;

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

    private String map_names_to_files(String name){
        if(name.equals("Переднее оживало"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Цилиндрическая"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Косинусоидальная"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Кубический сплайн"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Заднее оживало"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Сглаживающий кубический сплайн"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Окружность"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Эллипс"))
            return "FrontAugiveTab.fxml";

        return "FrontAugiveTab.fxml";
    }

    @FXML
    private void initialize(){
        generatrix_radius_box.setValue("Переднее оживало");
        generatrix_radius_box.setItems(generatrix_radius);

        angular_radius_box.setValue("Окружность");
        angular_radius_box.setItems(angular_radius);


        generatrix_radius_box.setOnAction(actionEvent -> {
            String value = (String)generatrix_radius_box.getValue();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml_files/" + map_names_to_files(value)));
                Parent root1 = fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root1, 900, 600));
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

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
        Body_Parts_Data body_data = Body_Parts_Data.getInstance();

        ChangeAble_Body body = new ChangeAble_Body();
        for(Body_Part part : body_data.parts){
            body.add_part(part);
        }

        body.init_Grid();
        body.get_Grid().to_File("resources/grid.txt");
    }

    @FXML
    void draw_generatrix(ActionEvent event) {

    }

    @FXML
    void delete_body_part(){

    }

    @FXML
    void add_body_part(ActionEvent event) {


        Generatrix_Radius_Data gen_data = Generatrix_Radius_Data.getInstance();
        Angular_Radius_Data ang_data = Angular_Radius_Data.getInstance();
        Body_Parts_Data body_data = Body_Parts_Data.getInstance();

        Body_Part part = new Body_Part(gen_data.radius,gen_data.separator,ang_data.radius,ang_data.separator);

        System.out.println("It just works");
        body_data.parts.add(part);

    }



}
