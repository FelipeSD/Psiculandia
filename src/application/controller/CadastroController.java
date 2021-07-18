package application.controller;

import domain.utils.ShowAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static application.main.Main.createEmpregadoUseCase;

public class CadastroController implements Initializable {
    public TextField txtUsername;
    public PasswordField txtSenha;
    public Button btnCadastrar;
    public ToggleGroup rbGroupTipo;
    public RadioButton rbAdministrador;
    public RadioButton rbEmpregado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbAdministrador.setUserData("administrador");
        rbEmpregado.setUserData("empregado");
    }

    public void cadastrar(ActionEvent actionEvent) throws IOException {
        String tipo = rbGroupTipo.getSelectedToggle().getUserData().toString();
        boolean retorno = createEmpregadoUseCase.insert(tipo, txtUsername.getText(), txtSenha.getText());
        if(!retorno){
            ShowAlert.emit(
                    "Cadastro falhou",
                    "Verifique as informações e tente novamente",
                    Alert.AlertType.WARNING
            );
        }else{
            abrirLogin(actionEvent);
        }
    }

    public void abrirLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/Login.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
