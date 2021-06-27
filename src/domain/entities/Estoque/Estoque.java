package domain.entities.Estoque;

import domain.entities.Insumo.Insumo;

import java.util.ArrayList;

public class Estoque {
    private int id;
    private String dataAtualizacao;
    private ArrayList<Insumo> insumos = new ArrayList<Insumo>();

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

    public ArrayList<Insumo> listarInsumos(){
        return insumos;
    }
}
