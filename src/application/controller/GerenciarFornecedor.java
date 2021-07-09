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
import java.util.Optional;
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
        associarValoresComColunas();
        desabilitarFormulario();

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

    public Fornecedor getSelectedItem(){
        return tableViewFornecedor.getSelectionModel().getSelectedItem();
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
        Fornecedor fornecedor = this.getSelectedItem();
        txtNome.setText(fornecedor.getNome());
        txtCNPJ.setText(fornecedor.getCnpj());
        txtEndereco.setText(fornecedor.getEnedereco());
        txtTempoEntrega.setText(String.valueOf(fornecedor.getTempoEntrega()));
    }

    public void excluir(ActionEvent actionEvent) {
        Fornecedor fornecedor = this.getSelectedItem();

        if(fornecedor == null){
            showAlert(
                    "Não foi possível excluir",
                    "Selecione um fornecedor na tabela para excluir.",
                    Alert.AlertType.ERROR,
                    null
            );
        }else{
            showAlert(
                    "Excluir",
                    "Deseja realmente excluir: " + fornecedor,
                    Alert.AlertType.CONFIRMATION,
                    new AlertCallback() {
                        @Override
                        public void onConfirm() {

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
//        boolean newUser = findUserUseCase.findOne(user.getInstitutionalId()).isEmpty();
//
//        if (newUser) {
//            createUserUseCase.insert(user);
//        } else {
//            updateUserUseCase.update(user);
//        }

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

    private void carregarDadosEExibir(){
        List<Fornecedor> fornecedorList = findFornecedorUseCase.findAll();
        fornecedores.clear();
        fornecedores.addAll(fornecedorList);
    }
}
