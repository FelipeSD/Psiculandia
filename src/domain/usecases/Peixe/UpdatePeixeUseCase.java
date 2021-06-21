package domain.usecases.Peixe;

import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.Tanque;
import domain.utils.EntityNotFoundException;

public class UpdatePeixeUseCase {
    private final PeixeDAO peixeDAO;

    public UpdatePeixeUseCase(PeixeDAO peixeDAO) {
        this.peixeDAO = peixeDAO;
    }

    public boolean update(Peixe peixe){
        int id = peixe.getId();
        if(peixeDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Peixe não encontrado.");
        }

        peixeDAO.update(peixe);
        return true;
    }
}
