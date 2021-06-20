package domain.usecases.Tanque;

import domain.entities.Tanque.Tanque;
import domain.utils.EntityNotFoundException;

public class UpdateTanqueUseCase {
    private final TanqueDAO tanqueDAO;

    public UpdateTanqueUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public boolean update(Tanque tanque){
        int id = tanque.getId();
        if(tanqueDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Tanque não encontrado.");
        }

        tanqueDAO.update(tanque);
        return true;
    }
}
