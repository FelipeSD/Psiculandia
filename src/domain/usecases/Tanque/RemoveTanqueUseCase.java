package domain.usecases.Tanque;

import domain.entities.Tanque.Tanque;
import domain.utils.EntityNotFoundException;

public class RemoveTanqueUseCase {
    private final TanqueDAO tanqueDAO;

    public RemoveTanqueUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public boolean remove(Integer id){
        if(id == null || tanqueDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Tanque não encontrado.");

        return tanqueDAO.deleteByKey(id);
    }

    public boolean remove(Tanque tanque){
        if(tanque == null || tanqueDAO.findOne(tanque.getId()).isEmpty())
            throw new EntityNotFoundException("Tanque não encontrado.");
        return tanqueDAO.delete(tanque);
    }
}
