package domain.usecases.Tanque;

import domain.entities.Tanque.Tanque;

public class CreateTanqueUseCase {
    private final TanqueDAO tanqueDAO;

    public CreateTanqueUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public boolean insert(Tanque tanque){
        tanqueDAO.create(tanque);
        return true;
    }
}
