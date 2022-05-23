package user_interface.main_controllers;

import calculation.grid_builder.Body_Part;
import calculation.grid_builder.ChangeAble_Body;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import math.math_primitives.Radius;
import user_interface.data_classes.Angular_Radius_Data;
import user_interface.data_classes.Body_Data;
import user_interface.data_classes.Generatrix_Radius_Data;
import user_interface.data_classes.PartViewData;
import user_interface.visualization.Line_Graph_Drawer;

import java.io.File;

public class BodyController {

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    ObservableList<PartViewData> table_data = FXCollections.observableArrayList();

    ObservableList<String> generatrix_radius = FXCollections.observableArrayList(
            "Выберите образующую",
            "Переднее оживало",
            "Цилиндрическая",
            "Заднее оживало",
            "Косинусоидальная",
            "Коническая",
            "Кубический сплайн",
            "Сглаживающий кубический сплайн");

    ObservableList<String> angular_radius = FXCollections.observableArrayList(
            "Выберите форму","Окружность","Эллипс");

    @FXML
    private ChoiceBox generatrix_radius_box;

    @FXML
    private ChoiceBox angular_radius_box;


    @FXML
    private void initialize(){
        generatrix_radius_box.setItems(generatrix_radius);
        angular_radius_box.setItems(angular_radius);

        generatrix_radius_box.setValue("Выберите образующую");
        angular_radius_box.setValue("Выберите форму");

        parts_table.setItems(table_data);

        num_column.setCellValueFactory(
                p -> new SimpleIntegerProperty(p.getValue().getNum()).asObject()
        );

        generatrix_column.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getGeneratrix())
        );

        form_column.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getForm())
        );
    }




    @FXML
    private Pane pane_for_graph;



    @FXML
    void init_body(ActionEvent event) {


        Body_Data body_data = Body_Data.getInstance();

        if(body_data.parts.size() != 0){
            ChangeAble_Body body = new ChangeAble_Body();
            for(Body_Part part : body_data.parts){
                body.add_part(part);
            }

            body.init_Grid();
            body.get_Grid().to_File("_results_on_grid/grid.mv");
            body_data.body = body;



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Сообщение");
            alert.setHeaderText(null);
            alert.setContentText("Заданное тело см. в _results_on_grid/grid.mv");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Количество частей нулевое!");
            alert.showAndWait();
        }


    }


    @FXML
    void draw_generatrix(ActionEvent event) {
        Body_Data body_data = Body_Data.getInstance();
        Line_Graph_Drawer drawer = new Line_Graph_Drawer(625,375);
        drawer.draw_full_generatix(body_data.parts, "_resources/generatrix_graph");
        Image img = new Image(new File("./_resources/generatrix_graph.PNG").toURI().toString());
        pane_for_graph.getChildren().add(new ImageView(img));
    }


    @FXML
    void delete_body_part(){
        Body_Data body_data = Body_Data.getInstance();

        if(body_data.parts.size() != 0){
            body_data.parts.remove(body_data.parts.size() - 1);
            table_data.remove(table_data.size() - 1);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Количество частей нулевое! Нечего удалять!");
            alert.showAndWait();
        }

    }


    @FXML
    void add_body_part(ActionEvent event) {

        Generatrix_Radius_Data gen_data = Generatrix_Radius_Data.getInstance();
        Angular_Radius_Data ang_data = Angular_Radius_Data.getInstance();
        Body_Data body_data = Body_Data.getInstance();


        Radius ox_radius =  gen_data.radius;
        Radius ang_radius =  ang_data.radius;

        if(ox_radius != null && ang_radius != null){
            Body_Part part = new Body_Part(gen_data.radius,gen_data.separator,ang_data.radius,ang_data.separator);

            System.out.println("It just works");
            body_data.parts.add(part);

            table_data.add(new PartViewData(body_data.parts.size(),(String)generatrix_radius_box.getValue(), (String)angular_radius_box.getValue()));
        }

        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Необходимо задать образующую и форму!");
            alert.showAndWait();
        }


    }


    @FXML
    void choose_generatrix(){
        String gen_value = (String)generatrix_radius_box.getValue();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../generatrix_tabs/" + map_names_to_files(gen_value)));
            Parent root1 = fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1, 900, 600));
            stage1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void choose_form(){
        String gen_value = (String)angular_radius_box.getValue();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../form_tabs/" + map_names_to_files(gen_value)));
            Parent root1 = fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1, 900, 600));
            stage1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String map_names_to_files(String name){
        if(name.equals("Переднее оживало"))
            return "FrontAugiveTab.fxml";
        if(name.equals("Цилиндрическая"))
            return "CylinderTab.fxml";
        if(name.equals("Коническая"))
            return "ConicTab.fxml";
        if(name.equals("Косинусоидальная"))
            return "CosineTab.fxml";
        if(name.equals("Кубический сплайн"))
            return "CubicSplineTab.fxml";
        if(name.equals("Заднее оживало"))
            return "BackAugiveTab.fxml";
        if(name.equals("Сглаживающий кубический сплайн"))
            return "SmoothedCubicSplineTab.fxml";
        if(name.equals("Окружность"))
            return "CircleTab.fxml";
        if(name.equals("Эллипс"))
            return "EllipseTab.fxml";

        return "FrontAugiveTab.fxml";
    }


    @FXML
    private TableView<PartViewData> parts_table;

    @FXML
    private TableColumn<PartViewData, Integer> num_column;

    @FXML
    private TableColumn<PartViewData, String> form_column;

    @FXML
    private TableColumn<PartViewData, String> generatrix_column;



}
