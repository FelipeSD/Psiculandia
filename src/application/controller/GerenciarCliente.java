package application.controller;

import domain.entities.Cliente.Cliente;
import domain.entities.Fornecedor.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.Optional;

import static application.main.Main.*;

public class GerenciarCliente {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Cliente> tableViewCliente;
    public TableColumn cNome;
    public TableColumn cCNPJ;
    public FlowPane formularioPane;
    public TextField txtNome;
    public TextField txtCNPJ;
    public TextField txtEndereco;
    public TextField txtTelefone;
    public Button btnSalvar;
    public Button btnCancelar;

    private ObservableList<Cliente> clientes;

    private Cliente clienteSelecionado;

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
            showAlert(
                    "Não foi possível editar",
                    "Selecione um cliente na tabela para editar.",
                    Alert.AlertType.ERROR,
                    null
            );
        }else{
            habilitarFormulario();
            preencherDadosCliente();
        }
    }

    private void preencherDadosCliente() {
        txtNome.setText(clienteSelecionado.getNome());
        txtCNPJ.setText(clienteSelecionado.getCnpj());
//        txtEndereco.setText(clienteSelecionado.getEndereco());
//        txtTempoEntrega.setText(String.valueOf(clienteSelecionado.getTempoEntrega()));
    }

    public void excluir(ActionEvent actionEvent) {
        if(clienteSelecionado == null){
            showAlert(
                    "Não foi possível excluir",
                    "Selecione um cliente na tabela para excluir.",
                    Alert.AlertType.ERROR,
                    null
            );
        }else{
            showAlert(
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
        // coletar informações cliente do formulario

        return clienteSelecionado;
    }

    private void bindTableViewToItemsList() {
        clientes = FXCollections.observableArrayList();
        tableViewCliente.setItems(clientes);
    }

    public void associarValoresComColunas(){
        cNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        cCNPJ.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cnpj"));
//        cEndereco.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
//        cTempoEntrega.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("tempoEntrega"));
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
