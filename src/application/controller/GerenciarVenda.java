package application.controller;

import domain.entities.Cliente.Cliente;
import domain.entities.Venda.Venda;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

public class GerenciarVenda {
    public Button btnAdicionar;
    public Button btnEditar;
    public Button btnExcluir;
    public TableView<Venda> tableViewVenda;
    public TableColumn<Venda, String> cData;
    public TableColumn<Cliente, String> cCliente;
    public TableColumn<Venda, Double> cValor;
    public FlowPane formularioPane;
    public ComboBox<Cliente> cbCliente;
    public TextField txtData;
    public TextField txtValor;
    public Button btnSalvar;
    public Button btnCancelar;

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
