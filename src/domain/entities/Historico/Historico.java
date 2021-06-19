package domain.entities.Historico;

import domain.entities.Insumo.Insumo;
import domain.entities.Tanque.Tanque;
import domain.entities.Venda.Venda;

public class Historico {
    private String dataAtualizacao;
    private Tanque[] historicoTanque;
    private Venda[] historicoVenda;
    private Insumo[] historicoInsumo;
}
