package application.controller;

import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.utils.ShowAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static application.main.Main.*;

public class GerenciarPeixe implements Initializable {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Peixe> tableViewPeixe;
    public TableColumn<Peixe, String> cEspecie;
    public TableColumn<Peixe, Insumo> cRacaoConsumida;
    public FlowPane formularioPane;
    public TextField txtEspecie;
    public TextField txtPesoVenda;
    public TextField txtValorMercado;
    public ComboBox<Insumo> cbRacaoConsumida;
    public TextField txtQuantidadeRacaoDiaria;
    public Button btnSalvar;
    public Button btnCancelar;

    private ObservableList<Peixe> peixes;

    private Peixe peixeSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarValoresComColunas();
        preencherComboFornecedor();
        desabilitarFormulario();
        carregarDadosEExibir();
        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewPeixe.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            peixeSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Peixe> peixeList = findPeixeUseCase.findAll();
        peixes.clear();
        peixes.addAll(peixeList);
    }

    public Peixe getSelectedItem(){
        return tableViewPeixe.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }else if(node instanceof ComboBox){
                ((ComboBox<Fornecedor>)node).setValue(null);
//                ((ComboBox<Fornecedor>)node).getSelectionModel().clearSelection();
            }
        }

        tableViewPeixe.getSelectionModel().clearSelection();
    }

    public void habilitarFormulario(){
        for(Node node : formularioPane.getChildren()){
            node.setDisable(false);
        }
    }

    public void desabilitarFormulario(){
        for(Node node : formularioPane.getChildren()){
            node.setDisable(true);
        }
    }

    public void desabilitarBotoes(){
        btnAdicionar.setDisable(true);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }

    public void habilitarBotoes(){
        btnAdicionar.setDisable(false);
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    public void adicionar(ActionEvent actionEvent) {
        desabilitarBotoes();
        habilitarFormulario();
        limparCampos();
    }

    public void editar(ActionEvent actionEvent) {
        Peixe insumo = this.getSelectedItem();

        if(insumo == null){
            ShowAlert.emit(
                    "Não foi possível editar",
                    "Selecione um peixe na tabela para editar.",
                    Alert.AlertType.ERROR
            );
        }else{
            habilitarFormulario();
            preencherDadosPeixe();
        }
    }

    private void preencherDadosPeixe() {
        txtEspecie.setText(peixeSelecionado.getNome());
        txtPesoVenda.setText(String.valueOf(peixeSelecionado.getPesoIdealVenda()));
        txtQuantidadeRacaoDiaria.setText(String.valueOf(peixeSelecionado.getQtdRacaoDiaria()));
        txtValorMercado.setText(String.valueOf(peixeSelecionado.getValorMercado()));
        cbRacaoConsumida.setValue(peixeSelecionado.getRacaoConsumida());
    }

    public void excluir(ActionEvent actionEvent) {
        if(peixeSelecionado == null){
            ShowAlert.emit(
                    "Não foi possível excluir",
                    "Selecione um peixe na tabela para excluir.",
                    Alert.AlertType.ERROR
            );
        }else{
            ShowAlert.callback(
                    "Excluir",
                    "Deseja realmente excluir: " + peixeSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removePeixeUseCase.remove(peixeSelecionado);
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewPeixe.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        peixeSelecionado = obterPeixeFormulario();

        if (peixeSelecionado.getId() == 0) {
            createPeixeUseCase.insert(peixeSelecionado);
        } else {
            updatePeixeUseCase.update(peixeSelecionado);
        }

        carregarDadosEExibir();

        peixeSelecionado = null;
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private void preencherComboFornecedor(){
        List<Insumo> insumoList = findInsumoUseCase.findAll();
        cbRacaoConsumida.getItems().clear();
        cbRacaoConsumida.getItems().addAll(insumoList);
    }

    private Peixe obterPeixeFormulario(){
        if(peixeSelecionado == null)
            peixeSelecionado = new Peixe();

        peixeSelecionado.setNome(txtEspecie.getText());
        peixeSelecionado.setRacaoConsumida(cbRacaoConsumida.getValue());
        peixeSelecionado.setPesoIdealVenda(Double.parseDouble(txtPesoVenda.getText()));
        peixeSelecionado.setValorMercado(Double.parseDouble(txtValorMercado.getText()));
        peixeSelecionado.setQtdRacaoDiaria(Double.parseDouble(txtQuantidadeRacaoDiaria.getText()));

        return peixeSelecionado;
    }

    private void bindTableViewToItemsList() {
        peixes = FXCollections.observableArrayList();
        tableViewPeixe.setItems(peixes);
    }

    public void associarValoresComColunas(){
        cEspecie.setCellValueFactory(new PropertyValueFactory<Peixe, String>("nome"));
        cRacaoConsumida.setCellValueFactory(new PropertyValueFactory<Peixe, Insumo>("racaoConsumida"));
    }
}