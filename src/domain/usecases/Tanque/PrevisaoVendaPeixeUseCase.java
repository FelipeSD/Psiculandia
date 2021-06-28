package domain.usecases.Tanque;

import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.HistoricoSemanalCrescimento;
import domain.entities.Tanque.Tanque;
import domain.usecases.Peixe.PeixeDAO;
import domain.utils.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class PrevisaoVendaPeixeUseCase {
    private final TanqueDAO tanqueDAO;
    private final PeixeDAO peixeDAO;

    public PrevisaoVendaPeixeUseCase(TanqueDAO tanqueDAO, PeixeDAO peixeDAO) {
        this.tanqueDAO = tanqueDAO;
        this.peixeDAO = peixeDAO;
    }

    public double preveVenda(Tanque tanque){
        int id = tanque.getId();
        if(tanqueDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Tanque não encontrado.");
        }

        String especie = tanque.getEspecieCriada();
        Optional<Peixe> peixeInMemory = peixeDAO.findByEspecie(especie);
        Peixe peixeEncontrado = peixeInMemory.get();

        int quantidadePeixe = tanque.getQtdAlevinos();
        double pesoIdealVenda = peixeEncontrado.getPesoIdealVenda();

        double totalPesoMedio = 0;
        double pesoAtual = 0;

        ArrayList<HistoricoSemanalCrescimento> listCrescimentoSemanal = tanque.getHistoricoSemanal();
        for(HistoricoSemanalCrescimento historico : listCrescimentoSemanal){
            totalPesoMedio += historico.getPesoMedio();
            pesoAtual = historico.getPesoMedio();

        }
        double pesoMedioSemanal = totalPesoMedio/listCrescimentoSemanal.size();

        double pesoIdealTanque = pesoIdealVenda*quantidadePeixe;

        double pesoFaltante = pesoIdealTanque-pesoAtual;

        double semanasFaltantes = pesoFaltante/pesoMedioSemanal;

        System.out.println("Os peixes do tanque poderão ser vendidos daqui a " + semanasFaltantes +" semanas.");

        return semanasFaltantes;
    }


}
