package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainUIController {
    public Button btnGerenciarTanque;
    public Button btnGerenciarFornecedor;
    public AnchorPane paneContainer;
    public Button btnGerenciarEstoque;
    public Button btnGerenciarVenda;
    public Button btnGerenciarCliente;
    public Button btnGerenciarPeixe;
    public Button btnRelatorios;

    public void abrirFXML(ActionEvent mouseEvent) throws IOException {
        Button clickedButton = (Button) mouseEvent.getSource();
        String fxmlName = clickedButton.getId();
        Parent newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../resources/" +fxmlName+".fxml")));
        paneContainer.getChildren().setAll(newLoadedPane);
    }
}
