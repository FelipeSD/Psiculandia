package domain.entities.Venda;

import domain.entities.Cliente.Cliente;
import domain.entities.Peixe.Peixe;

import java.util.Date;

public class Venda {
    private int id;
    private Date data;
    private Peixe peixeVendido;
    private double qtde;
    private double valor;
    private Cliente cliente;

    public Venda(int id, Date data, Peixe peixeVendido, double qtde, double valor, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Venda(Peixe peixeVendido, double qtde, double valor) {
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.data = new Date();
    }

    public Venda(Peixe peixeVendido, double qtde, double valor, Cliente cliente) {
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.data = new Date();
        this.cliente = cliente;
    }

    public Venda(Date data, Peixe peixeVendido, double qtde, double valor) {
        this.data = data;
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
    }

    public Venda() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Peixe getPeixeVendido() {
        return peixeVendido;
    }

    public void setPeixeVendido(Peixe peixeVendido) {
        this.peixeVendido = peixeVendido;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "peixeVendido='" + peixeVendido + '\'' +
                ", qtde=" + qtde +
                ", valor=" + valor +
                '}';
    }
}
