package user_interface.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class FlowAndCalcController {
    @FXML
    private TextField ReynoldsNumber;

    @FXML
    private TextField FlowVelocity;

    @FXML
    private TextField Density;

    @FXML
    private TextField numOfPlaneSeparations;

    @FXML
    private TextField InnerPressure;

    @FXML
    private RadioButton GaussMethodRadioButton;

    @FXML
    private RadioButton BiGradientRadioButton;

    @FXML
    private Button InitFlowAndCalcButton;

    @FXML
    private Button MakeCalculationButton;

    @FXML
    void InitFlowAndCalcData (ActionEvent event){

    }

    @FXML
    void MakeCalculation (ActionEvent event){
    }
}
