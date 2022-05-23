package sample;

import _examples.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        File resources = new File("_resources");
        if (!resources.exists()) {
            resources.mkdir();
        }

        File results_on_grid = new File("_results_on_grid");
        if (!results_on_grid.exists()) {
            results_on_grid.mkdir();
        }

        File graphs_of_results = new File("_graphs_of_results");
        if (!graphs_of_results.exists()) {
            graphs_of_results.mkdir();
        }

        File examples = new File("_examples");
        if (!examples.exists()) {
            examples.mkdir();
        }

        //init_examples();
//        Calculation_Example example3 = new Calculation_Example("_examples/_calculation_example");
//        example3.curved_body_calc_example();


        Parent root = FXMLLoader.load(getClass().getResource("../user_interface/main_tabs/sample.fxml"));
        primaryStage.setTitle("Расчёт обтекания тела");
        primaryStage.setScene(new Scene(root, 1024, 680));
        primaryStage.show();

    }




    public void init_examples(){


        Radiis_Example example = new Radiis_Example("_examples/_radii's_example");
        example.run_example();

        Body_Part_Example example1 = new Body_Part_Example("_examples/_body_parts_example");
        example1.run_example();

        Bodies_Example example2 = new Bodies_Example("_examples/_bodies_example");
        example2.run_example();

        Calculation_Example example3 = new Calculation_Example("_examples/_calculation_example");
        example3.run_example();

        Separation_Example example4 = new Separation_Example("_examples/_separation_example");
        example4.run_example();
    }

}

