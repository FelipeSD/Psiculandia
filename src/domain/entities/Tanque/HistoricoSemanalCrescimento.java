package domain.entities.Tanque;

import java.util.Date;

public class HistoricoSemanalCrescimento {
    private double pesoMedio;
    private Date dataLancada;

    public HistoricoSemanalCrescimento(double pesoMedio) {
        this.pesoMedio = pesoMedio;
        this.dataLancada = new Date();
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
