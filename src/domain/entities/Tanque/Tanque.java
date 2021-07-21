package domain.entities.Tanque;

import domain.entities.Peixe.Peixe;

import java.util.ArrayList;
import java.util.Date;

public class Tanque {
    private int id; // número de identificação
    private Peixe especieCriada;
    private int qtdAlevinos;
    private double precoManutencao;
    private Date dataInicio;
    private Date dataFim;
    private double pesoMedioInicial;
    private boolean checkAlimentado;
    private ArrayList<HistoricoSemanalCrescimento> historicoSemanalCrescimento = new ArrayList<HistoricoSemanalCrescimento>();

    public Tanque() {
    }

     public Tanque(Peixe especieCriada, int qtdAlevinos) {
        this.especieCriada = especieCriada;
        this.qtdAlevinos = qtdAlevinos;
    }

    public Tanque(int id, Peixe especieCriada, int qtdAlevinos, double precoManutencao, Date dataInicio, Date dataFim, double pesoMedioInicial, int checkAlimentado) {
        this.id = id;
        this.especieCriada = especieCriada;
        this.qtdAlevinos = qtdAlevinos;
        this.precoManutencao = precoManutencao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pesoMedioInicial = pesoMedioInicial;
        this.checkAlimentado = checkAlimentado == 1;
    }



    public double getPesoMedioInicial() {
        return pesoMedioInicial;
    }

    public void setPesoMedioInicial(double pesoMedioInicial) {
        this.pesoMedioInicial = pesoMedioInicial;
    }

    public ArrayList<HistoricoSemanalCrescimento> getHistoricoSemanalCrescimento() {
        return historicoSemanalCrescimento;
    }

    public void setHistoricoSemanalCrescimento(ArrayList<HistoricoSemanalCrescimento> historicoSemanalCrescimento) {
        this.historicoSemanalCrescimento = historicoSemanalCrescimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Peixe getEspecieCriada() {
        return especieCriada;
    }

    public void setEspecieCriada(Peixe especieCriada) {
        this.especieCriada = especieCriada;
    }

    public int getQtdAlevinos() {
        return qtdAlevinos;
    }

    public void setQtdAlevinos(int qtdAlevinos) {
        this.qtdAlevinos = qtdAlevinos;
    }

    public double getPrecoManutencao() {
        return precoManutencao;
    }

    public void setPrecoManutencao(double precoManutencao) {
        this.precoManutencao += precoManutencao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isCheckAlimentado() {
        return checkAlimentado;
    }

    public void setCheckAlimentado(boolean checkAlimentado) {
        this.checkAlimentado = checkAlimentado;
    }

    public ArrayList<HistoricoSemanalCrescimento> getHistoricoSemanal() {
        return historicoSemanalCrescimento;
    }

    public void setHistoricoSemanal(HistoricoSemanalCrescimento historicoSemanal) {
        this.historicoSemanalCrescimento.add(historicoSemanal);
    }

    @Override
    public String toString() {
        return "Tanque{" +
                "especieCriada='" + especieCriada + '\'' +
                ", qtdAlevinos=" + qtdAlevinos +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                '}';
    }
}
