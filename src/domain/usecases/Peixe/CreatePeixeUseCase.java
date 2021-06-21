package domain.usecases.Peixe;

import domain.entities.Peixe.Peixe;

public class CreatePeixeUseCase {
    private final PeixeDAO peixeDAO;

    public CreatePeixeUseCase(PeixeDAO peixeDAO) {
        this.peixeDAO = peixeDAO;
    }

    public boolean insert(Peixe peixe){
        peixeDAO.create(peixe);
        return true;
    }
}
