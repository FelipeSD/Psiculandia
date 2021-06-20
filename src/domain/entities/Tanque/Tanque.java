package domain.entities.Tanque;

public class Tanque {
    private int id; // número de identificação
    private String especieCriada;
    private int qtdAlevinos;
    private double precoManutencao;
    private double pesoMedioPeixe;
    private String dataInicio;
    private String dataFim;
    private boolean checkAlimentado;
    private double[] historicoSemanal;

    public Tanque() {
    }

    public Tanque(String especieCriada) {
        this.especieCriada = especieCriada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecieCriada() {
        return especieCriada;
    }

    public void setEspecieCriada(String especieCriada) {
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
        this.precoManutencao = precoManutencao;
    }

    public double getPesoMedioPeixe() {
        return pesoMedioPeixe;
    }

    public void setPesoMedioPeixe(double pesoMedioPeixe) {
        this.pesoMedioPeixe = pesoMedioPeixe;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isCheckAlimentado() {
        return checkAlimentado;
    }

    public void setCheckAlimentado(boolean checkAlimentado) {
        this.checkAlimentado = checkAlimentado;
    }

    public double[] getHistoricoSemanal() {
        return historicoSemanal;
    }

    public void setHistoricoSemanal(double[] historicoSemanal) {
        this.historicoSemanal = historicoSemanal;
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
