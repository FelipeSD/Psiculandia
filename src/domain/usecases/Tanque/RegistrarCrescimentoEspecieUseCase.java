package domain.usecases.Tanque;

import domain.entities.Tanque.HistoricoSemanalCrescimento;
import domain.entities.Tanque.Tanque;
import domain.utils.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static domain.utils.CheckDate.getDateDiff;

public class RegistrarCrescimentoEspecieUseCase {
    private final TanqueDAO tanqueDAO;

    public RegistrarCrescimentoEspecieUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public boolean registrar(Tanque tanque, double pesoMedio){
       if(tanqueDAO.findOne(tanque.getId()).isEmpty())
            throw new EntityNotFoundException("Tanque não foi encontrado.");

       if(checarPeriodoSemanal(tanque)){
           HistoricoSemanalCrescimento novoHistorico = new HistoricoSemanalCrescimento(pesoMedio);
           tanque.setHistoricoSemanal(novoHistorico);

           tanqueDAO.update(tanque);
           return true;
       }

        return false;
    }

    private boolean checarPeriodoSemanal(Tanque tanque){
        ArrayList<HistoricoSemanalCrescimento> list = tanque.getHistoricoSemanal();

        if(list.size() == 0) return true;

        HistoricoSemanalCrescimento ultimoHistorico = list.get(list.size() - 1);

        Date ultimaDataLancada = ultimoHistorico.getDataLancada();
        Date dataHoje =  new Date();

//        long daysBetween = getDateDiff(ultimaDataLancada, dataHoje, TimeUnit.DAYS);
//        if(daysBetween < 7){
//            long diasParaNovoRegistro = 7 - daysBetween;
//            System.out.println("Retorne daqui a " + diasParaNovoRegistro + " dias para registrar um novo histórico");
//            return false;
//        }

        // PARA TESTE INSTANTÂNEO - (REMOVER)
        long daysBetween = getDateDiff(ultimaDataLancada, dataHoje, TimeUnit.MILLISECONDS);
        if(daysBetween < 900){
            long diasParaNovoRegistro = 7 - daysBetween;
            System.out.println("Retorne daqui a " + diasParaNovoRegistro + " dias para registrar um novo histórico");
            return false;
        }

        return true;
    }

}
