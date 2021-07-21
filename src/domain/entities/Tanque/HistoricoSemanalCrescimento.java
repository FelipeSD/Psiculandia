package domain.entities.Tanque;

import java.util.Date;

public class HistoricoSemanalCrescimento {
    private int id;
    private double pesoMedio;
    private Tanque tanque;
    private Date dataLancada;

    public HistoricoSemanalCrescimento(double pesoMedio) {
        this.pesoMedio = pesoMedio;
        this.dataLancada = new Date();
    }

    public HistoricoSemanalCrescimento(int id, double pesoMedio, Tanque tanque, Date dataLancada) {
        this.id = id;
        this.pesoMedio = pesoMedio;
        this.tanque = tanque;
        this.dataLancada = dataLancada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public void setDataLancada(Date dataLancada) {
        this.dataLancada = dataLancada;
    }

    public double getPesoMedio() {
        return pesoMedio;
    }

    public void setPesoMedio(double pesoMedio) {
        this.pesoMedio = pesoMedio;
    }

    public Date getDataLancada() {
        return dataLancada;
    }
}
