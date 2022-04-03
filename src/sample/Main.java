package sample;

import _examples.Bodies_Example;

import _examples.Body_Part_Example;
import _examples.Calculation_Example;
import _examples.Radiis_Example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        File folder = new File("resources");
        if (!folder.exists()) {
            folder.mkdir();
        }

        Parent root = FXMLLoader.load(getClass().getResource("../user_interface/fxml_files/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

    }




    public void init_examples(){


        Radiis_Example example = new Radiis_Example("_radii's_example");
        example.run_example();

        Body_Part_Example example1 = new Body_Part_Example("_body_parts_example");
        example1.run_example();

        Bodies_Example example2 = new Bodies_Example("_bodies_example");
        example2.run_example();

        Calculation_Example example3 = new Calculation_Example("_calculation_example");
        example3.run_example();
    }

}

