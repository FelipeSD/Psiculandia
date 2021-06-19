package domain.entities.Peixe;

public class Peixe {
    private int id;
    private String nome;
    private double pesoIdealVenda;
    private String racaoConsumida;
    private double qtdRacaoDiaria;
    private double valorMercado;

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

    public String getRacaoConsumida() {
        return racaoConsumida;
    }

    public void setRacaoConsumida(String racaoConsumida) {
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
}
