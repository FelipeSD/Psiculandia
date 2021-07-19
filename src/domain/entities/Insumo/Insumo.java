package domain.entities.Insumo;

import domain.entities.Fornecedor.Fornecedor;

public class Insumo {
    private int id;
    private String nome;
    private String tipo;
    private double qtde;
    private double valor;
    private String dataAquisicao;
    private Fornecedor fornecedor;

    public Insumo(String nome, double qtde, double valor) {
        this.nome = nome;
        this.qtde = qtde;
        this.valor = valor;
    }

    public Insumo(String nome, double qtde, double valor, Fornecedor fornecedor) {
        this.nome = nome;
        this.qtde = qtde;
        this.valor = valor;
        this.fornecedor = fornecedor;
    }

    public Insumo(int id, String nome, String tipo, double qtde, double valor, String dataAquisicao, int fornecedor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.qtde = qtde;
        this.valor = valor;
        this.dataAquisicao = dataAquisicao;
//        this.fornecedor = fornecedor;
    }

    public Insumo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    @Override
    public String toString() {
        return "Insumo{" +
                "nome='" + nome + '\'' +
                ", qtde=" + qtde +
                ", valor=" + valor +
//                ", fornecedor=" + fornecedor.getNome() +
                '}';
    }
}
