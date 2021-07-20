package domain.entities.Peixe;

import domain.entities.Insumo.Insumo;

public class Peixe {
    private int id;
    private String nome;
    private double pesoIdealVenda;
    private Insumo racaoConsumida;
    private double qtdRacaoDiaria;
    private double valorMercado;

    public Peixe(String nome, double pesoIdealVenda, Insumo racaoConsumida, double qtdRacaoDiaria, double valorMercado) {
        this.nome = nome;
        this.pesoIdealVenda = pesoIdealVenda;
        this.racaoConsumida = racaoConsumida;
        this.qtdRacaoDiaria = qtdRacaoDiaria;
        this.valorMercado = valorMercado;
    }

    public Peixe(int id, String nome, double pesoIdealVenda, Insumo racaoConsumida, double qtdRacaoDiaria, double valorMercado) {
        this.id = id;
        this.nome = nome;
        this.pesoIdealVenda = pesoIdealVenda;
        this.racaoConsumida = racaoConsumida;
        this.qtdRacaoDiaria = qtdRacaoDiaria;
        this.valorMercado = valorMercado;
    }

    public Peixe() {

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

    public double getPesoIdealVenda() {
        return pesoIdealVenda;
    }

    public void setPesoIdealVenda(double pesoIdealVenda) {
        this.pesoIdealVenda = pesoIdealVenda;
    }

    public Insumo getRacaoConsumida() {
        return racaoConsumida;
    }

    public void setRacaoConsumida(Insumo racaoConsumida) {
        this.racaoConsumida = racaoConsumida;
    }

    public double getQtdRacaoDiaria() {
        return qtdRacaoDiaria;
    }

    public void setQtdRacaoDiaria(double qtdRacaoDiaria) {
        this.qtdRacaoDiaria = qtdRacaoDiaria;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    @Override
    public String toString() {
        return "Peixe{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
