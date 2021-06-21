package domain.usecases.Peixe;

import domain.entities.Peixe.Peixe;
import domain.utils.EntityNotFoundException;

public class RemovePeixeUseCase {
    private final PeixeDAO peixeDAO;

    public RemovePeixeUseCase(PeixeDAO peixeDAO) {
        this.peixeDAO = peixeDAO;
    }

    public boolean remove(Integer id){
        if(id == null || peixeDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Peixe não encontrado.");

        return peixeDAO.deleteByKey(id);
    }

    public boolean remove(Peixe peixe){
        if(peixe == null || peixeDAO.findOne(peixe.getId()).isEmpty())
            throw new EntityNotFoundException("Peixe não encontrado.");
        return peixeDAO.delete(peixe);
    }
}
