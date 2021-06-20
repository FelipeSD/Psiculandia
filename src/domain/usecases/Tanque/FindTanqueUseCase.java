package domain.usecases.Tanque;

import domain.entities.Tanque.Tanque;

import java.util.List;
import java.util.Optional;

public class FindTanqueUseCase {
    private final TanqueDAO tanqueDAO;

    public FindTanqueUseCase(TanqueDAO tanqueDAO) {
        this.tanqueDAO = tanqueDAO;
    }

    public Optional<Tanque> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return tanqueDAO.findOne(id);
    }

    public List<Tanque> findAll() {
        return tanqueDAO.findAll();
    }
}
