package domain.usecases.Tanque;

import application.repository.InMemoryPeixeDAO;
import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.Tanque;
import domain.usecases.Estoque.EstoqueDAO;
import domain.usecases.Peixe.PeixeDAO;
import domain.utils.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class RegistrarAdministracaoDiariaRacaoUseCase {
    private final TanqueDAO tanqueDAO;
    private final PeixeDAO peixeDAO;

    public RegistrarAdministracaoDiariaRacaoUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
        this.peixeDAO = new InMemoryPeixeDAO();
    }

    public boolean administrarRacao(Tanque tanque, Estoque estoque){
        if(tanqueDAO.findOne(tanque.getId()).isEmpty())
            throw new EntityNotFoundException("Tanque não foi encontrado.");

        if(tanque.isCheckAlimentado()){
            System.out.println("Ração já administrada nessa data.");
            return false;
        }

        String especie = tanque.getEspecieCriada();
        int quantidadePeixe = tanque.getQtdAlevinos();

        Optional<Peixe> peixeInMemory = peixeDAO.findByEspecie(especie);
        Peixe peixeEncontrado = peixeInMemory.get();
        String racao = peixeEncontrado.getRacaoConsumida();
        double qtdeRacao = peixeEncontrado.getQtdRacaoDiaria();

        ArrayList<Insumo> listaInsumos = estoque.listarInsumos();
        for(Insumo insumoEstoque : listaInsumos){
            if(insumoEstoque.getNome().equals(racao)){
                double valorInsumo = insumoEstoque.getValor();
                double qtde = quantidadePeixe*qtdeRacao;
                double resultado = insumoEstoque.getQtde() - qtde;

                double valorTotal = valorInsumo * qtde;
                tanque.setPrecoManutencao(valorTotal);

                insumoEstoque.setQtde(resultado);
                tanque.setCheckAlimentado(true);
                break;
            }
        }

        return false;
    }
}
