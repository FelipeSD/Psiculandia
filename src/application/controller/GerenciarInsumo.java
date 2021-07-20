package application.controller;

import domain.entities.Insumo.Insumo;
import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
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
import static application.main.Main.updateInsumoUseCase;

public class GerenciarInsumo implements Initializable {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Insumo> tableViewInsumo;
    public TableColumn<Insumo, String> cNome;
    public TableColumn<Insumo, Fornecedor> cFornecedor;
    public TableColumn<Insumo, Double> cValor;
    public TableColumn<Insumo, Double> cQuantidade;
    public FlowPane formularioPane;
    public TextField txtNome;
    public TextField txtQuantidade;
    public TextField txtValor;
    public TextField txtTipo;
    public ComboBox<Fornecedor> cbFornecedor;
    public Button btnSalvar;
    public Button btnCancelar;

    private ObservableList<Insumo> insumos;

    private Insumo insumoSelecionado;

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
        tableViewInsumo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            insumoSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Insumo> insumoList = findInsumoUseCase.findAll();
        insumos.clear();
        insumos.addAll(insumoList);
    }

    public Insumo getSelectedItem(){
        return tableViewInsumo.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }else if(node instanceof ComboBox){
                ((ComboBox<Fornecedor>)node).setValue(null);
            }
        }

        tableViewInsumo.getSelectionModel().clearSelection();
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
        Insumo insumo = this.getSelectedItem();

        if(insumo == null){
            ShowAlert.emit(
                    "Não foi possível editar",
                    "Selecione um insumo na tabela para editar.",
                    Alert.AlertType.ERROR
            );
        }else{
            habilitarFormulario();
            preencherDadosInsumo();
        }
    }

    private void preencherDadosInsumo() {
        txtNome.setText(insumoSelecionado.getNome());
        txtQuantidade.setText(String.valueOf(insumoSelecionado.getQtde()));
        txtValor.setText(String.valueOf(insumoSelecionado.getValor()));
        txtTipo.setText(insumoSelecionado.getTipo());
        cbFornecedor.setValue(insumoSelecionado.getFornecedor());
    }

    public void excluir(ActionEvent actionEvent) {
        if(insumoSelecionado == null){
            ShowAlert.emit(
                    "Não foi possível excluir",
                    "Selecione um insumo na tabela para excluir.",
                    Alert.AlertType.ERROR
            );
        }else{
            ShowAlert.callback(
                    "Excluir",
                    "Deseja realmente excluir: " + insumoSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removeInsumoUseCase.remove(insumoSelecionado);
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewInsumo.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        insumoSelecionado = obterInsumoFormulario();

        if (insumoSelecionado.getId() == 0) {
            createInsumoUseCase.insert(insumoSelecionado);
        } else {
            updateInsumoUseCase.update(insumoSelecionado);
        }

        carregarDadosEExibir();

        insumoSelecionado = null;
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
        List<Fornecedor> fornecedorList = findFornecedorUseCase.findAll();
        cbFornecedor.getItems().clear();
        cbFornecedor.getItems().addAll(fornecedorList);
    }

    private Insumo obterInsumoFormulario(){
        if(insumoSelecionado == null)
            insumoSelecionado = new Insumo();

        insumoSelecionado.setNome(txtNome.getText());
        insumoSelecionado.setFornecedor(cbFornecedor.getValue());
        insumoSelecionado.setValor(Double.parseDouble(txtValor.getText()));
        insumoSelecionado.setQtde(Double.parseDouble(txtQuantidade.getText()));
        insumoSelecionado.setTipo(txtTipo.getText());

        return insumoSelecionado;
    }

    private void bindTableViewToItemsList() {
        insumos = FXCollections.observableArrayList();
        tableViewInsumo.setItems(insumos);
    }

    public void associarValoresComColunas(){
        cNome.setCellValueFactory(new PropertyValueFactory<Insumo, String>("nome"));
        cFornecedor.setCellValueFactory(new PropertyValueFactory<Insumo, Fornecedor>("fornecedor"));
        cValor.setCellValueFactory(new PropertyValueFactory<Insumo, Double>("valor"));
        cQuantidade.setCellValueFactory(new PropertyValueFactory<Insumo, Double>("qtde"));
    }
}
