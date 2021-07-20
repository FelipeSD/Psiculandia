package domain.entities.Venda;

import domain.entities.Cliente.Cliente;

import java.util.Date;

public class Venda {
    private int id;
    private Date data;
    private String peixeVendido;
    private double qtde;
    private double valor;
    private Cliente cliente;

    public Venda(int id, Date data, String peixeVendido, double qtde, double valor, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Venda(String peixeVendido, double qtde, double valor) {
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.data = new Date();
    }

    public Venda(String peixeVendido, double qtde, double valor, Cliente cliente) {
        this.peixeVendido = peixeVendido;
        this.qtde = qtde;
        this.valor = valor;
        this.data = new Date();
        this.cliente = cliente;
    }

    public Venda(Date data, String peixeVendido, double qtde, double valor) {
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

    public String getPeixeVendido() {
        return peixeVendido;
    }

    public void setPeixeVendido(String peixeVendido) {
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
