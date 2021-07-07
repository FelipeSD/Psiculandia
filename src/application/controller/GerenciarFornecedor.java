package application.controller;

import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
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

import static application.main.Main.findFornecedorUseCase;

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
    public TextArea txtProdutos;

    public Button btnSalvar;
    public Button btnCancelar;

    public FlowPane formularioPane;
    public ListView<Insumo> listaProdutos = new ListView<Insumo>();

    private ObservableList<Fornecedor> fornecedores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTableViewToItemsList();
        associarColunasAosValores();

        //desabilitarFormulario();
        listaProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Insumo insumo1 = new Insumo("Teste1", 12, 14.00);
        Insumo insumo2 = new Insumo("Teste2", 12, 14.00);
        listaProdutos.getItems().add(insumo1);
        listaProdutos.getItems().add(insumo1);
        listaProdutos.getItems().add(insumo1);
        listaProdutos.getItems().add(insumo1);
        listaProdutos.getItems().add(insumo2);

        bindOnSelectEvent();
    }

    public void bindOnSelectEvent(){
        tableViewFornecedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("obs + oldSelection + newSelection = " + obs + oldSelection + newSelection);
        });
    }

    public void limparCampos(){
        for(Node node : formularioPane.getChildren()){
            if(node instanceof TextInputControl){
                ((TextInputControl)node).setText("");
            }
        }
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
        fornecedores.add(new Fornecedor("teste"));
        desabilitarBotoes();
        habilitarFormulario();
    }

    public void editar(ActionEvent actionEvent) {
        habilitarFormulario();
    }

    public void excluir(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir");
        alert.setHeaderText("Você está prestes a excluir um fornecedor");
        alert.setContentText("Tem certeza que deseja realmente excluir?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            // EXCLUIR
            System.out.println("Fornecedor excluído com sucesso");
        }
    }

    public void salvar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    public void cancelar(ActionEvent actionEvent) {
        habilitarBotoes();
        desabilitarFormulario();
        limparCampos();
    }

    private void bindTableViewToItemsList() {
        fornecedores = FXCollections.observableArrayList();
        tableViewFornecedor.setItems(fornecedores);
    }

    public void associarColunasAosValores(){
        cNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        cCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cnpj"));
        cEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
        cTempoEntrega.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("tempoEntrega"));
    }

    private void carregarDadosEExibi4r(){
        List<Fornecedor> fornecedorList = findFornecedorUseCase.findAll();
        fornecedores.clear();
        fornecedores.addAll(fornecedorList);
    }
}
