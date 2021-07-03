package application.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainUIController {
    public Button btnGerenciarTanque;
    public Button btnGerenciarFornecedor;
    public AnchorPane paneContainer;

    public void abrirFXML(MouseEvent mouseEvent) throws IOException {
        Button clickedButton = (Button) mouseEvent.getSource();
        String fxmlName = clickedButton.getId();
        Parent newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../resources/" +fxmlName+".fxml")));
        paneContainer.getChildren().setAll(newLoadedPane);
    }
}
