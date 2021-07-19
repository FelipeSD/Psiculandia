package application.controller;

import domain.entities.Cliente.Cliente;
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

public class GerenciarCliente implements Initializable {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Cliente> tableViewCliente;
    public TableColumn<Cliente, String> cNome;
    public TableColumn<Cliente, String> cCNPJ;
    public FlowPane formularioPane;
    public TextField txtNome;
    public TextField txtCNPJ;
    public TextField txtEndereco;
    public TextField txtTelefone;
    public Button btnSalvar;
    public Button btnCancelar;

    private ObservableList<Cliente> clientes;

    private Cliente clienteSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarValoresComColunas();
        desabilitarFormulario();
        carregarDadosEExibir();
        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("obs + oldSelection + newSelection = " + obs + oldSelection + newSelection);
            clienteSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Cliente> clienteList = findClienteUseCase.findAll();
        clientes.clear();
        clientes.addAll(clienteList);
    }

    public Cliente getSelectedItem(){
        return tableViewCliente.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }
        }

        tableViewCliente.getSelectionModel().clearSelection();
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
        Cliente cliente = this.getSelectedItem();

        if(cliente == null){
            ShowAlert.emit(
                    "Não foi possível editar",
                    "Selecione um cliente na tabela para editar.",
                    Alert.AlertType.ERROR
            );
        }else{
            habilitarFormulario();
            preencherDadosCliente();
        }
    }

    private void preencherDadosCliente() {
        txtNome.setText(clienteSelecionado.getNome());
        txtCNPJ.setText(clienteSelecionado.getCnpj());
        txtEndereco.setText(clienteSelecionado.getEmail());
        txtTelefone.setText(String.valueOf(clienteSelecionado.getTelefone()));
    }

    public void excluir(ActionEvent actionEvent) {
        if(clienteSelecionado == null){
            ShowAlert.emit(
                    "Não foi possível excluir",
                    "Selecione um cliente na tabela para excluir.",
                    Alert.AlertType.ERROR
            );
        }else{
            ShowAlert.callback(
                    "Excluir",
                    "Deseja realmente excluir: " + clienteSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removeClienteUseCase.remove(clienteSelecionado);
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewCliente.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        clienteSelecionado = obterClienteFormulario();

        if (clienteSelecionado.getId() == 0) {
            createClienteUseCase.insert(clienteSelecionado);
        } else {
            updateClienteUseCase.update(clienteSelecionado);
        }

        carregarDadosEExibir();

        clienteSelecionado = null;
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private Cliente obterClienteFormulario(){
        if(clienteSelecionado == null)
            clienteSelecionado = new Cliente();

        clienteSelecionado.setNome(txtNome.getText());
        clienteSelecionado.setCnpj(txtCNPJ.getText());
        clienteSelecionado.setEmail(txtEndereco.getText());
        clienteSelecionado.setTelefone(txtTelefone.getText());

        return clienteSelecionado;
    }

    private void bindTableViewToItemsList() {
        clientes = FXCollections.observableArrayList();
        tableViewCliente.setItems(clientes);
    }

    public void associarValoresComColunas(){
        cNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        cCNPJ.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cnpj"));
    }
}
