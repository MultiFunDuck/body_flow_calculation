package user_interface.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class GridController {

    @FXML
    private TextField AngleSplitNum;

    @FXML
    private TextField HeadPartSplitNum;

    @FXML
    private TextField CylindricalPartSplitNum;

    @FXML
    private TextField ConicalPartSplitNum;

    @FXML
    private TextField TransPartSplitNum;

    @FXML
    private TextField TailPartSplitNum;

    @FXML
    private TextField OxStep;

    @FXML
    private TextField MinHeadStepNum;

    @FXML
    private TextField SurfaceStepNum;

    @FXML
    private TextField BottomCutThicknessFactor;

    @FXML
    private Button InitGridDataButton;

    @FXML
    private Button BuildGeometryButton;


    @FXML
    private RadioButton SelectingBodySplitRadioButton,
            SelectingStepRadioButton,
            SelectingSurfaceSplitRadioButton;

    //TODO Добавить сообщения о не заданных данных или отсутствие файлов
    @FXML
    void InitGridData(){

    };

    @FXML
    void BuildGeometry(){
    }
}


