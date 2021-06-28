package domain.usecases.Insumo;

import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.Tanque;
import domain.usecases.Peixe.PeixeDAO;
import domain.usecases.Tanque.TanqueDAO;
import domain.utils.EntityNotFoundException;

import java.util.Optional;

public class PrevisaoRepoeEstoqueUseCase {
    private final PeixeDAO peixeDAO;
    private final TanqueDAO tanqueDAO;

    public PrevisaoRepoeEstoqueUseCase(PeixeDAO peixeDAO, TanqueDAO tanqueDAO){
        this.peixeDAO = peixeDAO;
        this.tanqueDAO = tanqueDAO;
    }

    public double preveReposicao(Tanque tanque, Estoque estoque){ // int porque o uso é diario
        int idTanque = tanque.getId();
        if(tanqueDAO.findOne(idTanque).isEmpty()) {
            throw new EntityNotFoundException("Tanque não encontrado.");
        }

        String especie = tanque.getEspecieCriada();
        Optional<Peixe> peixeInMemory = peixeDAO.findByEspecie(especie);
        Peixe peixeEncontrado = peixeInMemory.get();

        double qtdPeixe = tanque.getQtdAlevinos();
        String insumoPeixe = peixeEncontrado.getRacaoConsumida();
        double qtdeRacaoAdminsitrada = peixeEncontrado.getQtdRacaoDiaria()*qtdPeixe;

        for(Insumo insumoEstoque : estoque.listarInsumos()){
            if(insumoEstoque.getNome().equals(insumoPeixe)){
                double qtdeInsumoEstoque = insumoEstoque.getQtde();
                double tempoEntregaFornecedor = insumoEstoque.getFornecedor().getTempoEntrega();

                // resultado é a quantidade de dias que faltam para solicitar nova compra
                double diaIdealCompra = (qtdeInsumoEstoque/qtdeRacaoAdminsitrada) - tempoEntregaFornecedor;
                return diaIdealCompra;
            }
        }

        return 0;
    }
}

