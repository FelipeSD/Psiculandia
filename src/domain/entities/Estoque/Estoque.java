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
        return this.insumos;
    }

    public void adicionarInsumo(Insumo insumo){
        this.insumos.add(insumo);
    }

    public boolean atualizarInsumo(Insumo insumo){
        for(Insumo insumoEstoque : this.insumos){
            if(insumoEstoque.getId() == insumo.getId()){
                int index = this.insumos.indexOf(insumoEstoque);
                this.insumos.set(index, insumo);
                return true;
            }
        }

        return false;
    }

    public void removerInsumo(Insumo insumo) {
        this.insumos.removeIf(insumoEstoque -> insumoEstoque.getId() == insumo.getId());
    }
}
