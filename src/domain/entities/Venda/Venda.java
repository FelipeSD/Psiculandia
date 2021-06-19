package domain.entities.Venda;

public class Venda {
    private int id;
    private String data;
    private String peixeVendido;
    private double qtde;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
}
