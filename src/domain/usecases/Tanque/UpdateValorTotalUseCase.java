package domain.usecases.Tanque;

import domain.entities.Tanque.Tanque;
import domain.utils.EntityNotFoundException;

public class UpdateValorTotalUseCase {
    private final TanqueDAO tanqueDAO;

    public UpdateValorTotalUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public double update(double valorExtra, Tanque tanque){
        int id = tanque.getId();
        if(tanqueDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Tanque não encontrado.");
        }

        tanque.setPrecoManutencao(valorExtra);

        return valorExtra;
    }
}
