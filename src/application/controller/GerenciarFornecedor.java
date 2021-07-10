package application.controller;

import domain.entities.Fornecedor.Fornecedor;
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
import java.util.Optional;
import java.util.ResourceBundle;

import static application.main.Main.*;

public class GerenciarFornecedor implements Initializable {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;

    public TableView<Fornecedor> tableViewFornecedor;
    public TableColumn<Fornecedor, String> cNome;
    public TableColumn<Fornecedor, String> cCNPJ;
    public TableColumn<Fornecedor, String> cEndereco;
    public TableColumn<Fornecedor, Integer> cTempoEntrega;

    public TextField txtNome;
    public TextField txtCNPJ;
    public TextField txtEndereco;
    public TextField txtTempoEntrega;

    public Button btnSalvar;
    public Button btnCancelar;

    public FlowPane formularioPane;

    private ObservableList<Fornecedor> fornecedores;

    private Fornecedor fornecedorSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarValoresComColunas();
        desabilitarFormulario();
        carregarDadosEExibir();
        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewFornecedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("obs + oldSelection + newSelection = " + obs + oldSelection + newSelection);
            fornecedorSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Fornecedor> fornecedorList = findFornecedorUseCase.findAll();
        fornecedores.clear();
        fornecedores.addAll(fornecedorList);
    }

    public Fornecedor getSelectedItem(){
        return tableViewFornecedor.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }
        }

        tableViewFornecedor.getSelectionModel().clearSelection();
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
        Fornecedor fornecedor = this.getSelectedItem();

        if(fornecedor == null){
            showAlert(
                    "Não foi possível editar",
                    "Selecione um fornecedor na tabela para editar.",
                    Alert.AlertType.ERROR,
                    null
            );
        }else{
            habilitarFormulario();
            preencherDadosFornecedor();
        }
    }

    private void preencherDadosFornecedor() {
        txtNome.setText(fornecedorSelecionado.getNome());
        txtCNPJ.setText(fornecedorSelecionado.getCnpj());
        txtEndereco.setText(fornecedorSelecionado.getEndereco());
        txtTempoEntrega.setText(String.valueOf(fornecedorSelecionado.getTempoEntrega()));
    }

    public void excluir(ActionEvent actionEvent) {
        if(fornecedorSelecionado == null){
            showAlert(
                    "Não foi possível excluir",
                    "Selecione um fornecedor na tabela para excluir.",
                    Alert.AlertType.ERROR,
                    null
            );
        }else{
            showAlert(
                    "Excluir",
                    "Deseja realmente excluir: " + fornecedorSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removeFornecedorUseCase.remove(fornecedorSelecionado);
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewFornecedor.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        fornecedorSelecionado = obterFornecedorFormulario();

        if (fornecedorSelecionado.getId() == 0) {
            createFornecedorUseCase.insert(fornecedorSelecionado);
        } else {
            updateFornecedorUseCase.update(fornecedorSelecionado);
        }

        carregarDadosEExibir();

        fornecedorSelecionado = null;
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private Fornecedor obterFornecedorFormulario(){
        if(fornecedorSelecionado == null)
            fornecedorSelecionado = new Fornecedor();

        fornecedorSelecionado.setNome(txtNome.getText());
        fornecedorSelecionado.setCnpj(txtCNPJ.getText());
        fornecedorSelecionado.setEndereco(txtEndereco.getText());
        fornecedorSelecionado.setTempoEntrega(Integer.parseInt(txtTempoEntrega.getText()));

        return fornecedorSelecionado;
    }

    private void bindTableViewToItemsList() {
        fornecedores = FXCollections.observableArrayList();
        tableViewFornecedor.setItems(fornecedores);
    }

    public void associarValoresComColunas(){
        cNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        cCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cnpj"));
        cEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
        cTempoEntrega.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("tempoEntrega"));
    }

    private void showAlert(
            String title,
            String message,
            Alert.AlertType type,
            AlertCallback callback
    ){
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
}
