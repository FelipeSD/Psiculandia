package application.controller;

import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.HistoricoSemanalCrescimento;
import domain.entities.Tanque.Tanque;
import domain.utils.DateHelp;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static application.main.Main.*;
import static application.main.Main.updateFornecedorUseCase;

public class GerenciarTanque implements Initializable {

    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Tanque> tableViewTanque;
    public TableColumn<Tanque, Peixe> cEspecie;
    public TableColumn<Tanque, Date> cDataInicio;
    public TableColumn<Tanque, Integer> cQuantidadePeixes;
    public FlowPane formularioPane;
    public ComboBox<Peixe> cbEspecie;
    public TextField txtQuantidadePeixes;
    public TextField txtPrecoManutencao;
    public TextField txtDataInicio;
    public TextField txtPesoMedioInicio;
    public TextField txtPesoMedioAtual;
    public TextField txtQuantidadeRacaoDiaria;
    public Button btnSalvar;
    public Button btnCancelar;

    public TableColumn<HistoricoSemanalCrescimento, Date> cDataLancada;
    public TableColumn<HistoricoSemanalCrescimento, Double> cPesoMedio;
    public TableView<HistoricoSemanalCrescimento> tableViewHistoricoSemanal;
    public Button btnSalvarHistorico;
    public Button btnCancelarHistorico;

    private ObservableList<Tanque> tanques;

    private Tanque tanqueSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarValoresComColunas();
        preencherComboPeixe();
        desabilitarFormulario();
        carregarDadosEExibir();
        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewTanque.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            tanqueSelecionado = newSelection;
        });
    }

    private void carregarDadosEExibir(){
        List<Tanque> tanqueList = findTanqueUseCase.findAll();
        tanques.clear();
        tanques.addAll(tanqueList);
    }

    private void preencherComboPeixe(){
        List<Peixe> peixeList = findPeixeUseCase.findAll();
        cbEspecie.getItems().clear();
        cbEspecie.getItems().addAll(peixeList);
    }

    public Tanque getSelectedItem(){
        return tableViewTanque.getSelectionModel().getSelectedItem();
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }
        }

        tableViewTanque.getSelectionModel().clearSelection();
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
        Tanque tanque = this.getSelectedItem();

        if(tanque == null){
            ShowAlert.emit(
                    "Não foi possível editar",
                    "Selecione um tanque na tabela para editar.",
                    Alert.AlertType.ERROR
            );
        }else{
            habilitarFormulario();
            preencherDadosTanque();
        }
    }

    // PRECISA DOS NOMES CORRETOS DOS ITEMS DO VIEW
    private void preencherDadosTanque() {
        String dataInicio = DateHelp.dateToString(tanqueSelecionado.getDataInicio());
        txtQuantidadePeixes.setText(String.valueOf(tanqueSelecionado.getQtdAlevinos()));
        txtPrecoManutencao.setText(String.valueOf(tanqueSelecionado.getPrecoManutencao()));
        txtDataInicio.setText(dataInicio);
        txtPesoMedioInicio.setText(String.valueOf(tanqueSelecionado.getPesoMedioInicial()));
    }

    public void excluir(ActionEvent actionEvent) {
        if(tanqueSelecionado == null){
            ShowAlert.emit(
                    "Não foi possível excluir",
                    "Selecione um tanque na tabela para excluir.",
                    Alert.AlertType.ERROR
            );
        }else{
            ShowAlert.callback(
                    "Excluir",
                    "Deseja realmente excluir: " + tanqueSelecionado,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {
                            removeTanqueUseCase.remove(tanqueSelecionado); // ERRO?
                            carregarDadosEExibir();
                        }

                        @Override
                        public void onCancel() {
                            tableViewTanque.getSelectionModel().clearSelection();
                        }
                    }
            );
        }
    }

    public void salvar(ActionEvent actionEvent) {
        tanqueSelecionado = obterTanqueFormulario();

        if (tanqueSelecionado.getId() == 0) {
            createTanqueUseCase.insert(tanqueSelecionado);
        } else {
            updateTanqueUseCase.update(tanqueSelecionado);
        }

        carregarDadosEExibir();

        tanqueSelecionado = null;
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private Tanque obterTanqueFormulario(){
        if(tanqueSelecionado == null)
            tanqueSelecionado = new Tanque();

        Date dataInicio = null;
        try {
            dataInicio = DateHelp.stringToDate(txtDataInicio.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tanqueSelecionado.setEspecieCriada(cbEspecie.getValue());
        tanqueSelecionado.setDataInicio(dataInicio);
        tanqueSelecionado.setPesoMedioInicial(Double.parseDouble(txtPesoMedioInicio.getText()));
        tanqueSelecionado.setQtdAlevinos(Integer.parseInt(txtQuantidadePeixes.getText()));
        tanqueSelecionado.setPrecoManutencao(Double.parseDouble(txtPrecoManutencao.getText()));

        return tanqueSelecionado;
    }

    private void bindTableViewToItemsList() {
        tanques = FXCollections.observableArrayList();
        tableViewTanque.setItems(tanques);
    }

    // PRECISA DOS NOMES CORRETOS DOS ITENS DO VIEW
    public void associarValoresComColunas(){
        cEspecie.setCellValueFactory(new PropertyValueFactory<Tanque, Peixe>("especieCriada"));
        cDataInicio.setCellValueFactory(new PropertyValueFactory<Tanque, Date>("dataInicio"));
        cQuantidadePeixes.setCellValueFactory(new PropertyValueFactory<Tanque, Integer>("qtdAlevinos"));
    }

    public void salvarHistorico(ActionEvent actionEvent) {
    }

    public void cancelarHistorico(ActionEvent actionEvent) {
    }
}
