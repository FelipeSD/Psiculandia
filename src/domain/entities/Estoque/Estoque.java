package domain.entities.Estoque;

import domain.entities.Insumo.Insumo;

public class Estoque {
    private int id;
    private String dataAtualizacao;
    private Insumo[] insumos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Insumo[] listarInsumos(){
        return null;
    }
}
