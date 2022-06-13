package user_interface.main_controllers;

import javafx.scene.control.Alert;

public final class Message_Creator {

    public static void show_alert(String text){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public static void show_message(String text){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();

    }

}
