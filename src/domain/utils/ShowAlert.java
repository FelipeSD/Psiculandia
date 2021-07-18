package domain.utils;

import application.controller.AlertCallback;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ShowAlert {
    private String title;
    private String message;
    private Alert.AlertType type;
    private AlertCallback callback;

    public ShowAlert(String title, String message, Alert.AlertType type, AlertCallback callback) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if(callback != null && result.isPresent()){
            if(result.get() == ButtonType.OK) {
                callback.onConfirm();
            }else if (result.get() == ButtonType.CANCEL){
                callback.onCancel();
            }
        }
    }

    public ShowAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
