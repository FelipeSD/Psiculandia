package application.controller;

import domain.entities.Usuario.Empregado;
import domain.utils.EntityNotFoundException;
import domain.utils.InvalidPasswordException;
import domain.utils.ShowAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static application.main.Main.loginEmpregadoUseCase;

public class LoginController {
    public TextField txtUsername;
    public PasswordField txtSenha;
    public Button btnEntrar;
    public AnchorPane anchorPane;

    public Object abrirFxml(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = loader.load(Objects.requireNonNull(getClass().getResource("/resources/" + fxml + ".fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return loader.getController();
    }

    public void logar(ActionEvent actionEvent) throws IOException {
        try {
            Empregado empregadoLogado = loginEmpregadoUseCase.logar(txtUsername.getText(), txtSenha.getText());
            if(empregadoLogado != null){
                MainUIController controller = (MainUIController) abrirFxml("MainUI");
                System.out.println("empregadoLogado = " + empregadoLogado);
                if(controller != null) controller.EMPREGADO_Sistema = empregadoLogado;
            }
        } catch (EntityNotFoundException | InvalidPasswordException e) {
            new ShowAlert(
                    "Login falhou",
                    e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }

    public void abrirCadastro(ActionEvent actionEvent) throws IOException {
        abrirFxml("Cadastro");
    }
}
