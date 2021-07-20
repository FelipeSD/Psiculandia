package application.controller;

import domain.entities.Cliente.Cliente;
import domain.entities.Cliente.Cliente;
import domain.entities.Venda.Venda;
import domain.entities.Venda.Venda;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static application.main.Main.*;

public class GerenciarVenda implements Initializable {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Venda> tableViewVenda;
    public TableColumn<Venda, Date> cData;
    public TableColumn<Venda, Cliente> cCliente;
    public TableColumn<Venda, Double> cValor;
    public FlowPane formularioPane;
    public ComboBox<Cliente> cbCliente;
    public TextField txtData;
    public TextField txtValor;
    public Button btnSalvar;
    public Button btnCancelar;

    private ObservableList<Venda> vendas;

    private Venda vendaSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarValoresComColunas();
        preencherComboCliente();
        desabilitarFormulario();
        carregarDadosEExibir();
        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewVenda.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendaSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Venda> vendaList = findVendaUseCase.findAll();
        vendas.clear();
        vendas.addAll(vendaList);
    }

    public Venda getSelectedItem(){
        return tableViewVenda.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }else if(node instanceof ComboBox){
                ((ComboBox<Cliente>)node).setValue(null);
            }
        }

        tableViewVenda.getSelectionModel().clearSelection();
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
        Venda venda = this.getSelectedItem();

        if(venda == null){
            ShowAlert.emit(
                    "Não foi possível editar",
                    "Selecione um venda na tabela para editar.",
                    Alert.AlertType.ERROR
            );
        }else{
            habilitarFormulario();
            preencherDadosVenda();
        }
    }

    private void preencherDadosVenda() {
        txtData.setText(String.valueOf(vendaSelecionado.getData()));
        txtValor.setText(String.valueOf(vendaSelecionado.getValor()));
        cbCliente.setValue(vendaSelecionado.getCliente());
    }

    public void excluir(ActionEvent actionEvent) {
        if(vendaSelecionado == null){
            ShowAlert.emit(
                    "Não foi possível excluir",
                    "Selecione um venda na tabela para excluir.",
                    Alert.AlertType.ERROR
            );
        }else{
            ShowAlert.callback(
                    "Excluir",
                    "Deseja realmente excluir: " + vendaSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removeVendaUseCase.remove(vendaSelecionado);
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewVenda.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        vendaSelecionado = obterVendaFormulario();

        if (vendaSelecionado.getId() == 0) {
            createVendaUseCase.insert(vendaSelecionado);
        } else {
            updateVendaUseCase.update(vendaSelecionado);
        }

        carregarDadosEExibir();

        vendaSelecionado = null;
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private void preencherComboCliente(){
        List<Cliente> clienteList = findClienteUseCase.findAll();
        cbCliente.getItems().clear();
        cbCliente.getItems().addAll(clienteList);
    }

    private Venda obterVendaFormulario(){
        if(vendaSelecionado == null)
            vendaSelecionado = new Venda();

        try {
            String dataString = txtData.getText();
            Date dataVenda = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
            vendaSelecionado.setCliente(cbCliente.getValue());
            vendaSelecionado.setValor(Double.parseDouble(txtValor.getText()));
            vendaSelecionado.setData(dataVenda);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vendaSelecionado;
    }

    private void bindTableViewToItemsList() {
        vendas = FXCollections.observableArrayList();
        tableViewVenda.setItems(vendas);
    }

    public void associarValoresComColunas(){
        cCliente.setCellValueFactory(new PropertyValueFactory<Venda, Cliente>("cliente"));
        cValor.setCellValueFactory(new PropertyValueFactory<Venda, Double>("valor"));
        cData.setCellValueFactory(new PropertyValueFactory<Venda, Date>("data"));
    }
}
