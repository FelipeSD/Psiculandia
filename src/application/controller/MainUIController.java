package application.controller;

import domain.entities.Usuario.Empregado;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    public Button btnGerenciarTanque;
    public Button btnGerenciarFornecedor;
    public AnchorPane paneContainer;
    public Button btnGerenciarEstoque;
    public Button btnGerenciarVenda;
    public Button btnGerenciarCliente;
    public Button btnGerenciarPeixe;
    public Button btnRelatorios;
    public Empregado EMPREGADO_Sistema;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("EMPREGADO_Sistema = " + EMPREGADO_Sistema);
    }
    
    public void abrirFXML(ActionEvent mouseEvent) throws IOException {
        System.out.println("abrir EMPREGADO_Sistema = " + EMPREGADO_Sistema);
        Button clickedButton = (Button) mouseEvent.getSource();
        String fxmlName = clickedButton.getId();
        Parent newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/" +fxmlName+".fxml")));
        paneContainer.getChildren().setAll(newLoadedPane);
    }

}
