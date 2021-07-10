package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GerenciarTanque implements Initializable {

    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView tableViewTanque;
    public TableColumn cEspecie;
    public TableColumn cDataInicio;
    public TableColumn cQuantidadePeixes;
    public FlowPane formularioPane;
    public ComboBox cbEspecie;
    public TextField txtQuantidadePeixes;
    public TextField txtPrecoManutencao;
    public TextField txtDataInicio;
    public TextField txtPesoMedioInicio;
    public TextField txtPesoMedioAtual;
    public TextField txtQuantidadeRacaoDiaria;
    public Button btnSalvar;
    public Button btnCancelar;
    public TableColumn cDataLancada;
    public TableColumn cPesoMedio;
    public TableView tableViewHistoricoSemanal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void adicionar(ActionEvent actionEvent) {
    }

    public void editar(ActionEvent actionEvent) {
    }

    public void excluir(ActionEvent actionEvent) {
    }

    public void salvar(ActionEvent actionEvent) {
    }

    public void cancelar(ActionEvent actionEvent) {
    }
}
