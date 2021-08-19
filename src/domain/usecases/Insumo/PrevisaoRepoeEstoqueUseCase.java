package domain.usecases.Insumo;

import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.Tanque;
import domain.usecases.Peixe.PeixeDAO;
import domain.usecases.Tanque.TanqueDAO;
import domain.utils.EntityNotFoundException;

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

//        String especie = tanque.getEspecieCriada();
//        Optional<Peixe> peixeInMemory = peixeDAO.findByEspecie(especie);
//        Peixe peixeEncontrado = peixeInMemory.get();

        Peixe peixeEncontrado = tanque.getEspecieCriada();
        double qtdPeixe = tanque.getQtdAlevinos();
        Insumo insumoPeixe = peixeEncontrado.getRacaoConsumida();
        double qtdeRacaoAdministrada = peixeEncontrado.getQtdRacaoDiaria()*qtdPeixe;

        for(Insumo insumoEstoque : estoque.listarInsumos()){
            if(insumoEstoque.getNome().equals(insumoPeixe.getNome())){
                double qtdeInsumoEstoque = insumoEstoque.getQtde();
                double tempoEntregaFornecedor = insumoEstoque.getFornecedor().getTempoEntrega();

                double diaIdealCompra = (qtdeInsumoEstoque/qtdeRacaoAdministrada) - tempoEntregaFornecedor;
                System.out.println("Levando em consideração o tempo de entrega do fornecedor, é recomendado o abastecimento do estoque em " + diaIdealCompra + " dia(s).");
                return diaIdealCompra;
            }
        }

        return 0;
    }
}

